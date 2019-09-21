package org.specnaz.junit.platform;

import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.junit.platform.engine.support.descriptor.ClassSource;
import org.specnaz.impl.SpecParser;

public final class SpecnazClassDescriptor extends AbstractTestDescriptor {
    public SpecnazClassDescriptor(SpecnazEngineDescriptor engineTestDescriptor,
            Class<?> classs, SpecParser specParser) {
        super(engineTestDescriptor.getUniqueId().append("class", classs.getCanonicalName()),
                classs.getSimpleName(), ClassSource.from(classs));

        engineTestDescriptor.addChild(this);

        if (specParser != null) {
            new SpecnazRootDescribeDescriptor(this, specParser);
        }
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
