package org.specnaz.impl;

import org.specnaz.core.CoreDslBuilder;
import org.specnaz.utils.TestClosure;

public final class TestsTreeCoreDslBuilder implements CoreDslBuilder {
    private TestsGroupNodeBuilder testsGroupNodeBuilder;

    public TestsTreeCoreDslBuilder(String description) {
        testsGroupNodeBuilder = new TestsGroupNodeBuilder(description);
    }

    @Override
    public void beforeAll(TestClosure closure) {
        testsGroupNodeBuilder.addBeforeAll(closure);
    }

    @Override
    public void beforeEach(TestClosure closure) {
        testsGroupNodeBuilder.addBeforeEach(closure);
    }

    @Override
    public void test(String description, TestClosure testBody) {
        testsGroupNodeBuilder.addTestCase(new SinglePositiveTestCase(description, testBody));
    }

    @Override
    public void testExpectingException(Class<? extends Throwable> exceptionClass,
                                       String description, TestClosure closure) {
        testsGroupNodeBuilder.addTestCase(new SingleExceptionTestCase(exceptionClass, description, closure));
    }

    @Override
    public void afterEach(TestClosure closure) {
        testsGroupNodeBuilder.addAfterEach(closure);
    }

    @Override
    public void afterAll(TestClosure closure) {
        testsGroupNodeBuilder.addAfterAll(closure);
    }

    @Override
    public void subSpecification(String description, Runnable specClosure) {
        TestsGroupNodeBuilder previous = this.testsGroupNodeBuilder;
        TestsGroupNodeBuilder subgroupBuilder = new TestsGroupNodeBuilder(description);
        this.testsGroupNodeBuilder = subgroupBuilder;
        specClosure.run();
        previous.addSubgroup(subgroupBuilder.build());
        this.testsGroupNodeBuilder = previous;
    }

    @Override
    public void testExpectingExceptionWithMessage(Class<? extends Throwable> exceptionClass,String expectedMessage, String description, TestClosure closure) {
        testsGroupNodeBuilder.addTestCase(new SingleExceptionWithMessageTestCase(exceptionClass, expectedMessage, description, closure));
    }

    public TreeNode<TestsGroup> spec() {
        return testsGroupNodeBuilder.build();
    }
}
