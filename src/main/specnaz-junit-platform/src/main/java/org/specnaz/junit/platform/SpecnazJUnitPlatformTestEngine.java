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
import org.specnaz.impl.SpecParser;
import org.specnaz.impl.SpecsRegistryViolation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class SpecnazJUnitPlatformTestEngine implements TestEngine {
    private static final Logger log = LoggerFactory.getLogger(SpecnazJUnitPlatformTestEngine.class);

    private final Map<Class, SpecParser> cache = new HashMap<>();

    @Override
    public String getId() {
        return "specnaz";
    }

    @Override
    public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
        SpecnazEngineDescriptor engineTestDescriptor = new SpecnazEngineDescriptor(uniqueId);

        List<PackageSelector> packageSelectors = discoveryRequest.getSelectorsByType(PackageSelector.class);
        for (PackageSelector packageSelector : packageSelectors) {
            List<Class<?>> allSpecClassesInPackage = ReflectionSupport.findAllClassesInPackage(
                    packageSelector.getPackageName(), IsSpecnazClassPredicate.INSTANCE, className -> true);
            for (Class<?> specClass : allSpecClassesInPackage) {
                discoverClass(engineTestDescriptor, specClass);
            }
        }

        List<ClassSelector> classSelectors = discoveryRequest.getSelectorsByType(ClassSelector.class);
        for (ClassSelector classSelector : classSelectors) {
            Class<?> specClass = classSelector.getJavaClass();
            discoverClass(engineTestDescriptor, specClass);
        }

        return engineTestDescriptor;
    }

    @Override
    public void execute(ExecutionRequest request) {
        TestDescriptor rootTestDescriptor = request.getRootTestDescriptor();
        EngineExecutionListener engineExecutionListener = request.getEngineExecutionListener();

        recursivelyStartDescriptors(rootTestDescriptor, engineExecutionListener);
        recursivelyEndDescriptors(rootTestDescriptor, engineExecutionListener);
    }

    private void discoverClass(SpecnazEngineDescriptor engineTestDescriptor, Class<?> specClass) {
        if (!isSpecnazClass(specClass))
            return;

        SpecParser specParser = getSpecParserForClass(specClass);

        new SpecnazClassDescriptor(
                engineTestDescriptor, specClass, specParser);
    }

    private SpecParser getSpecParserForClass(Class<?> specClass) {
        if (cache.containsKey(specClass))
            return cache.get(specClass);

        SpecParser ret = null;
        Object specInstance = null;
        try {
            specInstance = specClass.newInstance();
        } catch (Exception e) {
            log.error(e, () -> format(
                    "Could not instantiate test class '%s' with no-argument constructor",
                    specClass.getSimpleName()));
        }

        if (specInstance != null) {
            try {
                ret = new SpecParser(specInstance);
            } catch (SpecsRegistryViolation e) {
                log.error(() -> "describes() was never called in the " +
                        "no-argument constructor of " + specClass.getSimpleName());
            }
        }

        cache.put(specClass, ret);
        return ret;
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
