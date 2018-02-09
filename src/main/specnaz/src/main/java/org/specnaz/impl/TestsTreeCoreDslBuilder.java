package org.specnaz.impl;

import org.specnaz.TestSettings;
import org.specnaz.core.CoreDslBuilder;
import org.specnaz.params.ParamsExpected1;
import org.specnaz.params.ParamsExpectedException1;
import org.specnaz.params.TestClosureParams1;
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
    public TestSettings test(String description, TestClosure testBody) {
        return testsGroupNodeBuilder.addPositiveTest(description, testBody, TestCaseType.REGULAR);
    }

    @Override
    public <T extends Throwable> ThrowableExpectations<T> testExpectingException(
            Class<T> expectedException, String description, TestClosure testBody) {
        return testsGroupNodeBuilder.addThrowTest(expectedException, description, testBody, TestCaseType.REGULAR);
    }

    @Override
    public TestSettings focusedTest(String description, TestClosure testBody) {
        return testsGroupNodeBuilder.addPositiveTest(description, testBody, TestCaseType.FOCUSED);
    }

    @Override
    public <T extends Throwable> ThrowableExpectations<T> focusedTestExpectingException(
            Class<T> expectedException, String description, TestClosure testBody) {
        return testsGroupNodeBuilder.addThrowTest(expectedException, description, testBody, TestCaseType.FOCUSED);
    }

    @Override
    public TestSettings ignoredTest(String description, TestClosure testBody) {
        return testsGroupNodeBuilder.addPositiveTest(description, testBody, TestCaseType.IGNORED);
    }

    @Override
    public <T extends Throwable> ThrowableExpectations<T> ignoredTestExpectingException(
            Class<T> expectedException, String description, TestClosure testBody) {
        return testsGroupNodeBuilder.addThrowTest(expectedException, description, testBody, TestCaseType.IGNORED);
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

    @Override
    public <P> ParamsExpected1<P> parametrizedTest1(String description, TestClosureParams1<P> testBody) {
        return testsGroupNodeBuilder.addParametrizedPositiveTest1(description, testBody);
    }

    @Override
    public <T extends Throwable, P> ParamsExpectedException1<T, P> parametrizedTestExpectingException1(
            Class<T> expectedException, String description, TestClosureParams1<P> testBody) {
        return testsGroupNodeBuilder.addParametrizedExceptionTest1(expectedException,
                description, testBody);
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
