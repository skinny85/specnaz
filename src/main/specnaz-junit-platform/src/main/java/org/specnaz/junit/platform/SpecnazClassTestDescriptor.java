package org.specnaz.junit.platform;

import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.junit.platform.engine.support.descriptor.ClassSource;

public final class SpecnazClassTestDescriptor extends AbstractTestDescriptor {
    public SpecnazClassTestDescriptor(UniqueId parentId, Class<?> classs) {
        super(parentId.append("class", classs.getCanonicalName()), classs.getSimpleName(), ClassSource.from(classs));
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
