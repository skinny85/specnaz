package org.specnaz.junit.platform;

import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.support.descriptor.EngineDescriptor;

public class SpecnazEngineTestDescriptor extends EngineDescriptor {
    public SpecnazEngineTestDescriptor(UniqueId uniqueId) {
        super(uniqueId, "Specnaz JUnit Platform");
    }
}
