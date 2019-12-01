package org.specnaz.junit.platform.impl;

import org.junit.platform.engine.support.descriptor.ClassSource;
import org.specnaz.impl.SpecParser;

public abstract class SpecnazClassDescriptor extends SpecnazClassOrGroupDescriptor {
    public static SpecnazClassDescriptor create(SpecnazEngineDescriptor engineDescriptor, Class<?> classs,
            SpecParser specParser, Throwable error) {
        return specParser == null
                ? new InvalidSpecnazClassDescriptor(engineDescriptor, classs, error)
                : new ValidSpecnazClassDescriptor(engineDescriptor, classs, specParser);
    }

    public SpecnazClassDescriptor(SpecnazEngineDescriptor engineDescriptor, Class<?> classs) {
        super(engineDescriptor.getUniqueId().append("class", classs.getCanonicalName()),
                classs.getSimpleName(), ClassSource.from(classs));

        engineDescriptor.attach(this);
    }
}
