package org.specnaz.junit.platform;

import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;
import org.specnaz.impl.SingleTestCase;
import org.specnaz.impl.SpecParser;
import org.specnaz.impl.TestsGroup;
import org.specnaz.impl.TreeNode;

public final class SpecnazRootDescribeDescriptor extends AbstractTestDescriptor {
    protected SpecnazRootDescribeDescriptor(SpecnazClassDescriptor classDescriptor, SpecParser specParser) {
        super(classDescriptor.getUniqueId().append("root-describes", specParser.name()), specParser.name());

        classDescriptor.addChild(this);

        TreeNode<TestsGroup> testsPlan = specParser.testsPlan();
        for (SingleTestCase testCase : testsPlan.value.testCases) {
            this.addChild(
                    new SingleTestCaseDescriptor(this, testCase));
        }
    }

    @Override
    public Type getType() {
        return Type.CONTAINER;
    }
}
