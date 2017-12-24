package org.specnaz.impl;

import org.specnaz.core.CoreDslBuilder;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

public final class TestsTreeCoreDslBuilder implements CoreDslBuilder {
    private TestsGroupNodeBuilder testsGroupNodeBuilder;

    public TestsTreeCoreDslBuilder(String description, TestCaseType testCaseType) {
        testsGroupNodeBuilder = new TestsGroupNodeBuilder(description, testCaseType);
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
    public ThrowableExpectations testExpectingException(Class<? extends Throwable> exceptionClass,
                                                        String description, TestClosure testBody) {
        SingleExceptionTestCase singleExceptionTestCase = new SingleExceptionTestCase(
                TestCaseType.REGULAR, exceptionClass, description, testBody);
        testsGroupNodeBuilder.addTestCase(singleExceptionTestCase);
        return singleExceptionTestCase.throwableExpectations();
    }

    @Override
    public void focusedTest(String description, TestClosure testBody) {
        testsGroupNodeBuilder.addTestCase(new SinglePositiveTestCase(TestCaseType.FOCUSED, description, testBody));
    }

    @Override
    public ThrowableExpectations focusedTestExpectingException(Class<? extends Throwable> exceptionClass,
                                                               String description, TestClosure testBody) {
        SingleExceptionTestCase singleExceptionTestCase = new SingleExceptionTestCase(
                TestCaseType.FOCUSED, exceptionClass, description, testBody);
        testsGroupNodeBuilder.addTestCase(singleExceptionTestCase);
        return singleExceptionTestCase.throwableExpectations();
    }

    @Override
    public void ignoredTest(String description, TestClosure testBody) {
        testsGroupNodeBuilder.addTestCase(new SinglePositiveTestCase(TestCaseType.IGNORED, description, testBody));
    }

    @Override
    public ThrowableExpectations ignoredTestExpectingException(Class<? extends Throwable> expectedException,
                                                               String description, TestClosure testBody) {
        SingleExceptionTestCase singleExceptionTestCase = new SingleExceptionTestCase(
                TestCaseType.IGNORED, expectedException, description, testBody);
        testsGroupNodeBuilder.addTestCase(singleExceptionTestCase);
        return singleExceptionTestCase.throwableExpectations();
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
        handleSubSpecification(description, specClosure, TestCaseType.REGULAR);
    }

    @Override
    public void focusedSubSpecification(String description, Runnable specClosure) {
        handleSubSpecification(description, specClosure, TestCaseType.FOCUSED);
    }

    @Override
    public void ignoredSubSpecification(String description, Runnable specClosure) {
        handleSubSpecification(description, specClosure, TestCaseType.IGNORED);
    }

    private void handleSubSpecification(String description, Runnable specClosure, TestCaseType testCaseType) {
        TestsGroupNodeBuilder previous = this.testsGroupNodeBuilder;
        TestsGroupNodeBuilder subgroupBuilder = previous.subgroupBuilder(description, testCaseType);
        this.testsGroupNodeBuilder = subgroupBuilder;
        specClosure.run();
        previous.addSubgroup(subgroupBuilder.build());
        this.testsGroupNodeBuilder = previous;
    }

    public TreeNode<TestsGroup> spec() {
        return testsGroupNodeBuilder.build();
    }
}
