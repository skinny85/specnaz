package org.specnaz.junit.platform;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.platform.commons.support.ReflectionSupport;
import org.junit.platform.engine.EngineDiscoveryRequest;
import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.ExecutionRequest;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestEngine;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.discovery.ClassSelector;
import org.junit.platform.engine.discovery.PackageSelector;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.SpecParser;
import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TreeNode;

import java.util.List;

import static java.lang.String.format;

public class SpecnazJUnitPlatformTestEngine implements TestEngine {
    private static final Logger log = LoggerFactory.getLogger(SpecnazJUnitPlatformTestEngine.class);

    @Override
    public String getId() {
        return "specnaz";
    }

    @Override
    public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
        SpecnazEngineTestDescriptor engineTestDescriptor = new SpecnazEngineTestDescriptor(uniqueId);

        List<PackageSelector> packageSelectors = discoveryRequest.getSelectorsByType(PackageSelector.class);
        for (PackageSelector packageSelector : packageSelectors) {
            List<Class<?>> allClassesInPackage = ReflectionSupport.findAllClassesInPackage(
                    packageSelector.getPackageName(), IsSpecnazClassPredicate.INSTANCE, className -> true);
            for (Class<?> classs : allClassesInPackage) {
                handleClass(uniqueId, engineTestDescriptor, classs);
            }
        }

        List<ClassSelector> classSelectors = discoveryRequest.getSelectorsByType(ClassSelector.class);
        for (ClassSelector classSelector : classSelectors) {
            Class<?> specClass = classSelector.getJavaClass();
            handleClass(uniqueId, engineTestDescriptor, specClass);
        }

        return engineTestDescriptor;
    }

    @Override
    public void execute(ExecutionRequest request) {
        System.out.println("Execute was called!");
        TestDescriptor rootTestDescriptor = request.getRootTestDescriptor();
        EngineExecutionListener engineExecutionListener = request.getEngineExecutionListener();

        recursivelyStartDescriptors(rootTestDescriptor, engineExecutionListener);
        recursivelyEndDescriptors(rootTestDescriptor, engineExecutionListener);
    }

    private void handleClass(UniqueId uniqueId, SpecnazEngineTestDescriptor engineTestDescriptor, Class<?> specClass) {
        if (!isSpecnazClass(specClass))
            return;

        Object specInstance = null;
        try {
            specInstance = specClass.newInstance();
        } catch (Exception e) {
            log.error(e, () -> format(
                    "Could not instantiate test class '%s' with no-argument constructor",
                    specClass.getSimpleName()));
        }

        if (specInstance != null) {
            SpecParser specParser = null;
            try {
                specParser = new SpecParser(specInstance);
            } catch (SpecsRegistryViolation e) {
                log.error(() -> "SpecnazCoreDsl.specification() was never called in the " +
                        "no-argument constructor of " + specClass.getSimpleName());
            }
            SpecnazClassTestDescriptor classDescriptor = new SpecnazClassTestDescriptor(uniqueId, specClass);
            engineTestDescriptor.addChild(classDescriptor);
            if (specParser != null) {
                SpecnazRootDescribeDescriptor rootDescribeDescriptor =
                        new SpecnazRootDescribeDescriptor(classDescriptor.getUniqueId(), specParser.name());
                classDescriptor.addChild(rootDescribeDescriptor);

                TreeNode<TestsGroup> testsPlan = specParser.testsPlan();
                for (SingleTestCase testCase : testsPlan.value.testCases) {
                    rootDescribeDescriptor.addChild(
                            new SingleTestCaseDescriptor(rootDescribeDescriptor.getUniqueId(), testCase));
                }
            }
        }
    }

    private boolean isSpecnazClass(Class<?> classs) {
        return IsSpecnazClassPredicate.INSTANCE.test(classs);
    }

    private void recursivelyStartDescriptors(TestDescriptor testDescriptor, EngineExecutionListener engineExecutionListener) {
        engineExecutionListener.executionStarted(testDescriptor);
        for (TestDescriptor child : testDescriptor.getChildren()) {
            recursivelyStartDescriptors(child, engineExecutionListener);
        }
    }

    private void recursivelyEndDescriptors(TestDescriptor testDescriptor, EngineExecutionListener engineExecutionListener) {
        for (TestDescriptor child : testDescriptor.getChildren()) {
            recursivelyEndDescriptors(child, engineExecutionListener);
        }
        engineExecutionListener.executionFinished(testDescriptor, TestExecutionResult.successful());
    }
}
