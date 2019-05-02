package org.specnaz.junit.platform;

import org.junit.platform.engine.EngineDiscoveryRequest;
import org.junit.platform.engine.ExecutionRequest;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestEngine;
import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.discovery.ClassSelector;
import org.specnaz.Specnaz;
import org.specnaz.params.SpecnazParams;

import java.util.List;

public class SpecnazJUnitPlatformTestEngine implements TestEngine {
    @Override
    public String getId() {
        return "specnaz";
    }

    @Override
    public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
        throw new UnsupportedOperationException("discover");
    }

    @Override
    public void execute(ExecutionRequest request) {
        throw new UnsupportedOperationException("execute");
    }

    private boolean isSpecnazClass(Class<?> classs) {
        return Specnaz.class.isAssignableFrom(classs) ||
                SpecnazParams.class.isAssignableFrom(classs);
    }
}
