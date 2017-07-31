package org.specnaz.impl;

import org.specnaz.core.CoreDslBuilder;
import org.specnaz.utils.TestClosure;

public final class TestsTreeCoreDslBuilder implements CoreDslBuilder {
    private TestsGroupNodeBuilder testsGroupNodeBuilder;

    public TestsTreeCoreDslBuilder(String description, boolean ignoredTestGroup) {
        testsGroupNodeBuilder = new TestsGroupNodeBuilder(description, ignoredTestGroup);
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
        testsGroupNodeBuilder.addTestCase(new SinglePositiveTestCase(TestCaseType.REGULAR, description, testBody));
    }

    @Override
    public void testExpectingException(Class<? extends Throwable> exceptionClass,
                                       String description, TestClosure testBody) {
        testsGroupNodeBuilder.addTestCase(new SingleExceptionTestCase(TestCaseType.REGULAR, exceptionClass, description, testBody));
    }

    @Override
    public void focusedTest(String description, TestClosure testBody) {
        testsGroupNodeBuilder.addTestCase(new SinglePositiveTestCase(TestCaseType.FOCUSED, description, testBody));
    }

    @Override
    public void focusedTestExpectingException(Class<? extends Throwable> exceptionClass, String description, TestClosure testBody) {
        testsGroupNodeBuilder.addTestCase(new SingleExceptionTestCase(TestCaseType.FOCUSED, exceptionClass, description, testBody));
    }

    @Override
    public void ignoredTest(String description, TestClosure testBody) {
        testsGroupNodeBuilder.addTestCase(new SinglePositiveTestCase(TestCaseType.IGNORED, description, testBody));
    }

    @Override
    public void ignoredTestExpectingException(Class<? extends Throwable> expectedException, String description, TestClosure testBody) {
        testsGroupNodeBuilder.addTestCase(new SingleExceptionTestCase(TestCaseType.IGNORED, expectedException, description, testBody));
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
        handleSubSpecification(description, specClosure, false);
    }

    @Override
    public void ignoredSubSpecification(String description, Runnable specClosure) {
        handleSubSpecification(description, specClosure, true);
    }

    private void handleSubSpecification(String description, Runnable specClosure, boolean ignoredTestGroup) {
        TestsGroupNodeBuilder previous = this.testsGroupNodeBuilder;
        TestsGroupNodeBuilder subgroupBuilder = previous.subgroupBuilder(description, ignoredTestGroup);
        this.testsGroupNodeBuilder = subgroupBuilder;
        specClosure.run();
        previous.addSubgroup(subgroupBuilder.build());
        this.testsGroupNodeBuilder = previous;
    }

    public TreeNode<TestsGroup> spec() {
        return testsGroupNodeBuilder.build();
    }
}
