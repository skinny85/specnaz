package org.specnaz.junit.platform;

import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.support.descriptor.EngineDescriptor;

public final class SpecnazEngineDescriptor extends EngineDescriptor {
    public SpecnazEngineDescriptor(UniqueId uniqueId) {
        super(uniqueId, "Specnaz JUnit Platform");
    }
}
