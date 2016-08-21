package org.specnaz.impl;

import org.specnaz.Specnaz;

import static java.lang.String.format;

public class SpecRunner {
    private final SpecDescriptor specDescriptor;
    private TreeNode<TestsGroup> rootTestsGroupNode;

    public SpecRunner(Specnaz specInstance) {
        this.specDescriptor = SpecsRegistry.specFor(specInstance);
    }

    public String name() {
        return specDescriptor.description;
    }

    public TreeNode<TestsGroup> testsPlan() {
        if (rootTestsGroupNode == null)
            rootTestsGroupNode = formulateTestPlan();
        return rootTestsGroupNode;
    }

    public void run(Notifier notifier) {
        new TestsGroupNodeRunner(rootTestsGroupNode, notifier).run();
    }

    private TreeNode<TestsGroup> formulateTestPlan() {
        TestsTreeSpecBuilder testsTreeSpecBuilder = new TestsTreeSpecBuilder(name());
        specDescriptor.specClosure.accept(testsTreeSpecBuilder);
        return testsTreeSpecBuilder.spec();
    }
}
