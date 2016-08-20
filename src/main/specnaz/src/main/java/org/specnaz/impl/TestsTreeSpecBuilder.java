package org.specnaz.impl;

import org.specnaz.SpecBuilder;
import org.specnaz.utils.TestClosure;

import java.util.Collections;
import java.util.List;

public final class TestsTreeSpecBuilder implements SpecBuilder {
    private TestsGroupNodeBuilder testsGroupNodeBuilder;

    public TestsTreeSpecBuilder(String description) {
        testsGroupNodeBuilder = new TestsGroupNodeBuilder(description);
    }

    @Override
    public void beginsAll(TestClosure closure) {
        testsGroupNodeBuilder.addBeforeAll(closure);
    }

    @Override
    public void beginsEach(TestClosure closure) {
        testsGroupNodeBuilder.addBeforeEach(closure);
    }

    @Override
    public void should(String description, TestClosure testBody) {
        testsGroupNodeBuilder.addTestCase(new SingleTestCase(description, testBody));
    }

    @Override
    public void endsEach(TestClosure closure) {
        testsGroupNodeBuilder.addAfterEach(closure);
    }

    @Override
    public void endsAll(TestClosure closure) {
        testsGroupNodeBuilder.addAfterAll(closure);
    }

    @Override
    public void describes(String description, Runnable specClosure) {
        TestsGroupNodeBuilder previous = this.testsGroupNodeBuilder;
        TestsGroupNodeBuilder subgroupBuilder = new TestsGroupNodeBuilder(description);
        this.testsGroupNodeBuilder = subgroupBuilder;
        specClosure.run();
        previous.addSubgroup(subgroupBuilder.build());
        this.testsGroupNodeBuilder = previous;
    }

    public TreeNode<TestsGroup> spec() {
        return testsGroupNodeBuilder.build();
    }
}
