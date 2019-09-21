package org.specnaz.junit.platform;

import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.specnaz.impl.SingleTestCase;

public class SingleTestCaseDescriptor extends AbstractTestDescriptor {
    public SingleTestCaseDescriptor(SpecnazRootDescribeDescriptor rootDescribeDescriptor, SingleTestCase testCase) {
        super(rootDescribeDescriptor.getUniqueId().append("test", testCase.description), testCase.description);
    }

    @Override
    public Type getType() {
        return Type.TEST;
    }
}
