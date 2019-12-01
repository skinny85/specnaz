package org.specnaz.junit.platform;

import org.junit.platform.commons.support.ReflectionSupport;
import org.junit.platform.engine.EngineDiscoveryRequest;
import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.ExecutionRequest;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestEngine;
import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.discovery.ClassSelector;
import org.junit.platform.engine.discovery.ClasspathRootSelector;
import org.junit.platform.engine.discovery.PackageSelector;
import org.specnaz.impl.SpecParser;
import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.junit.platform.impl.SpecnazClassDescriptor;
import org.specnaz.junit.platform.impl.SpecnazEngineDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.lang.String.format;

public abstract class CommonSpecnazJUnitPlatformTestEngine implements TestEngine {
    private final Map<Class, SpecRecord> cache = new HashMap<>();

    @Override
    public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
        SpecnazEngineDescriptor engineDescriptor = new SpecnazEngineDescriptor(uniqueId);

        List<ClasspathRootSelector> classpathRootSelectors = discoveryRequest.getSelectorsByType(ClasspathRootSelector.class);
        for (ClasspathRootSelector classpathRootSelector : classpathRootSelectors) {
            List<Class<?>> allSpecClassesInClasspathRoot = ReflectionSupport.findAllClassesInClasspathRoot(
                    classpathRootSelector.getClasspathRoot(), classPredicate(), className -> true);
            for (Class<?> specClass : allSpecClassesInClasspathRoot) {
                discoverClass(engineDescriptor, specClass);
            }
        }

        List<PackageSelector> packageSelectors = discoveryRequest.getSelectorsByType(PackageSelector.class);
        for (PackageSelector packageSelector : packageSelectors) {
            List<Class<?>> allSpecClassesInPackage = ReflectionSupport.findAllClassesInPackage(
                    packageSelector.getPackageName(), classPredicate(), className -> true);
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
    public void execute(ExecutionRequest request) {
        TestDescriptor rootDescriptor = request.getRootTestDescriptor();
        EngineExecutionListener engineExecutionListener = request.getEngineExecutionListener();

        if (rootDescriptor instanceof SpecnazEngineDescriptor) {
            ((SpecnazEngineDescriptor) rootDescriptor).execute(engineExecutionListener);
        }
    }

    protected abstract Predicate<Class<?>> classPredicate();

    private void discoverClass(SpecnazEngineDescriptor engineDescriptor, Class<?> specClass) {
        if (!isSpecnazClass(specClass))
            return;

        SpecRecord specRecord = getSpecRecordForClass(specClass);

        SpecnazClassDescriptor.create(engineDescriptor, specClass,
                specRecord.specParser, specRecord.error);
    }

    private SpecRecord getSpecRecordForClass(Class<?> specClass) {
        if (cache.containsKey(specClass))
            return cache.get(specClass);

        Throwable error = null;
        SpecParser specParser = null;
        Object specInstance = null;
        try {
            specInstance = specClass.newInstance();
        } catch (Exception e) {
            error = new Exception(format(
                    "Could not instantiate test class '%s' with no-argument constructor",
                    specClass.getSimpleName()), e);
        }

        if (specInstance != null) {
            try {
                specParser = new SpecParser(specInstance);
            } catch (SpecsRegistryViolation e) {
                error = new Exception("describes() was never called in the " +
                        "no-argument constructor of " + specClass.getSimpleName());
            }
        }

        SpecRecord ret = new SpecRecord(specParser, error);
        cache.put(specClass, ret);
        return ret;
    }

    private boolean isSpecnazClass(Class<?> classs) {
        return classPredicate().test(classs);
    }
}

final class SpecRecord {
    public final SpecParser specParser;
    public final Throwable error;

    public SpecRecord(SpecParser specParser, Throwable error) {
        this.specParser = specParser;
        this.error = error;
    }
}
