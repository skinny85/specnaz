package org.specnaz.junit.platform;

import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.specnaz.impl.SingleTestCase;

public class SingleTestCaseDescriptor extends AbstractTestDescriptor {
    public SingleTestCaseDescriptor(UniqueId parentId, SingleTestCase testCase) {
        super(parentId.append("test", testCase.description), testCase.description);
    }

    @Override
    public Type getType() {
        return Type.TEST;
    }
}
