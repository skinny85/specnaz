package org.specnaz.junit.platform;

import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;

public final class SpecnazRootDescribeDescriptor extends AbstractTestDescriptor {
    protected SpecnazRootDescribeDescriptor(UniqueId parentId, String displayName) {
        super(parentId.append("root-describes", displayName), displayName);
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
