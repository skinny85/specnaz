package org.specnaz.junit.platform;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.platform.commons.support.ReflectionSupport;
import org.junit.platform.engine.EngineDiscoveryRequest;
import org.junit.platform.engine.ExecutionRequest;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.discovery.ClassSelector;
import org.junit.platform.engine.discovery.ClasspathRootSelector;
import org.junit.platform.engine.discovery.PackageSelector;
import org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine;
import org.specnaz.impl.SpecParser;
import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.junit.platform.impl.IsSpecnazClassPredicate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class SpecnazJUnitPlatformTestEngine2 extends
        HierarchicalTestEngine<SpecnazEngineExecutionContext> {
    private static final Logger log = LoggerFactory.getLogger(SpecnazJUnitPlatformTestEngine.class);

    private final Map<Class, SpecParser> cache = new HashMap<>();

    @Override
    public String getId() {
        return "specnaz";
    }

    @Override
    public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
        SpecnazEngineDescriptor2 engineDescriptor = new SpecnazEngineDescriptor2(uniqueId);

        List<ClasspathRootSelector> classpathRootSelectors = discoveryRequest.getSelectorsByType(ClasspathRootSelector.class);
        for (ClasspathRootSelector classpathRootSelector : classpathRootSelectors) {
            List<Class<?>> allSpecClassesInClasspathRoot = ReflectionSupport.findAllClassesInClasspathRoot(
                    classpathRootSelector.getClasspathRoot(), IsSpecnazClassPredicate.INSTANCE, className -> true);
            for (Class<?> specClass : allSpecClassesInClasspathRoot) {
                discoverClass(engineDescriptor, specClass);
            }
        }

        List<PackageSelector> packageSelectors = discoveryRequest.getSelectorsByType(PackageSelector.class);
        for (PackageSelector packageSelector : packageSelectors) {
            List<Class<?>> allSpecClassesInPackage = ReflectionSupport.findAllClassesInPackage(
                    packageSelector.getPackageName(), IsSpecnazClassPredicate.INSTANCE, className -> true);
            for (Class<?> specClass : allSpecClassesInPackage) {
                discoverClass(engineDescriptor, specClass);
            }
        }

        List<ClassSelector> classSelectors = discoveryRequest.getSelectorsByType(ClassSelector.class);
        for (ClassSelector classSelector : classSelectors) {
            Class<?> specClass = classSelector.getJavaClass();
            discoverClass(engineDescriptor, specClass);
        }

        return engineDescriptor;
    }

    @Override
    protected SpecnazEngineExecutionContext createExecutionContext(ExecutionRequest request) {
        return new SpecnazEngineExecutionContext();
    }

    private void discoverClass(SpecnazEngineDescriptor2 engineDescriptor, Class<?> specClass) {
        if (!isSpecnazClass(specClass))
            return;

        SpecParser specParser = getSpecParserForClass(specClass);

        new SpecnazClassDescriptor2(engineDescriptor, specClass, specParser);
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
}
