package org.specnaz.impl;

import org.specnaz.TestSettings;
import org.specnaz.core.CoreDslBuilder;
import org.specnaz.params.ConsumerParams1;
import org.specnaz.params.ParamsExpected1;
import org.specnaz.params.ParamsExpected2;
import org.specnaz.params.ParamsExpected3;
import org.specnaz.params.ParamsExpectedException1;
import org.specnaz.params.ParamsExpectedException2;
import org.specnaz.params.ParamsExpectedException3;
import org.specnaz.params.ParamsExpectedSubgroup;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.params.TestClosureParams2;
import org.specnaz.params.TestClosureParams3;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

public final class TestsTreeCoreDslBuilder implements CoreDslBuilder {
    private TestsGroupNodeAccumulator testsGroupNodeAccumulator;

    public TestsTreeCoreDslBuilder(String description, TestCaseType testCaseType) {
        testsGroupNodeAccumulator = new TestsGroupNodeAccumulator(description, testCaseType);
    }

    @Override
    public void beforeAll(TestClosure closure) {
        testsGroupNodeAccumulator.addBeforeAll(closure);
    }

    @Override
    public void beforeEach(TestClosure closure) {
        testsGroupNodeAccumulator.addBeforeEach(closure);
    }

    @Override
    public TestSettings test(String description, TestClosure testBody) {
        return testsGroupNodeAccumulator.addPositiveTest(description, testBody, TestCaseType.REGULAR);
    }

    @Override
    public <T extends Throwable> ThrowableExpectations<T> testExpectingException(
            Class<T> expectedException, String description, TestClosure testBody) {
        return testsGroupNodeAccumulator.addExceptionTest(expectedException, description, testBody, TestCaseType.REGULAR);
    }

    @Override
    public TestSettings focusedTest(String description, TestClosure testBody) {
        return testsGroupNodeAccumulator.addPositiveTest(description, testBody, TestCaseType.FOCUSED);
    }

    @Override
    public <T extends Throwable> ThrowableExpectations<T> focusedTestExpectingException(
            Class<T> expectedException, String description, TestClosure testBody) {
        return testsGroupNodeAccumulator.addExceptionTest(expectedException, description, testBody, TestCaseType.FOCUSED);
    }

    @Override
    public TestSettings ignoredTest(String description, TestClosure testBody) {
        return testsGroupNodeAccumulator.addPositiveTest(description, testBody, TestCaseType.IGNORED);
    }

    @Override
    public <T extends Throwable> ThrowableExpectations<T> ignoredTestExpectingException(
            Class<T> expectedException, String description, TestClosure testBody) {
        return testsGroupNodeAccumulator.addExceptionTest(expectedException, description, testBody, TestCaseType.IGNORED);
    }

    @Override
    public void afterEach(TestClosure closure) {
        testsGroupNodeAccumulator.addAfterEach(closure);
    }

    @Override
    public void afterAll(TestClosure closure) {
        testsGroupNodeAccumulator.addAfterAll(closure);
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
        return testsGroupNodeAccumulator.addParametrizedPositiveTest1(description, testBody, TestCaseType.REGULAR);
    }

    @Override
    public <P> ParamsExpected1<P> focusedParametrizedTest1(String description, TestClosureParams1<P> testBody) {
        return testsGroupNodeAccumulator.addParametrizedPositiveTest1(description, testBody, TestCaseType.FOCUSED);
    }

    @Override
    public <P> ParamsExpected1<P> ignoredParametrizedTest1(String description, TestClosureParams1<P> testBody) {
        return testsGroupNodeAccumulator.addParametrizedPositiveTest1(description, testBody, TestCaseType.IGNORED);
    }

    @Override
    public <T extends Throwable, P> ParamsExpectedException1<T, P> parametrizedTestExpectingException1(
            Class<T> expectedException, String description, TestClosureParams1<P> testBody) {
        return testsGroupNodeAccumulator.addParametrizedExceptionTest1(expectedException,
                description, testBody, TestCaseType.REGULAR);
    }

    @Override
    public <T extends Throwable, P> ParamsExpectedException1<T, P> focusedParametrizedTestExpectingException1(
            Class<T> expectedException, String description, TestClosureParams1<P> testBody) {
        return testsGroupNodeAccumulator.addParametrizedExceptionTest1(expectedException,
                description, testBody, TestCaseType.FOCUSED);
    }

    @Override
    public <T extends Throwable, P> ParamsExpectedException1<T, P> ignoredParametrizedTestExpectingException1(
            Class<T> expectedException, String description, TestClosureParams1<P> testBody) {
        return testsGroupNodeAccumulator.addParametrizedExceptionTest1(expectedException,
                description, testBody, TestCaseType.IGNORED);
    }

    @Override
    public <P1, P2> ParamsExpected2<P1, P2> parametrizedTest2(String description, TestClosureParams2<P1, P2> testBody) {
        return testsGroupNodeAccumulator.addParametrizedPositiveTest2(description, testBody, TestCaseType.REGULAR);
    }

    @Override
    public <P1, P2> ParamsExpected2<P1, P2> focusedParametrizedTest2(String description, TestClosureParams2<P1, P2> testBody) {
        return testsGroupNodeAccumulator.addParametrizedPositiveTest2(description, testBody, TestCaseType.FOCUSED);
    }

    @Override
    public <P1, P2> ParamsExpected2<P1, P2> ignoredParametrizedTest2(String description, TestClosureParams2<P1, P2> testBody) {
        return testsGroupNodeAccumulator.addParametrizedPositiveTest2(description, testBody, TestCaseType.IGNORED);
    }

    @Override
    public <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> parametrizedTestExpectingException2(
            Class<T> expectedException, String description, TestClosureParams2<P1, P2> testBody) {
        return testsGroupNodeAccumulator.addParametrizedExceptionTest2(expectedException,
                description, testBody, TestCaseType.REGULAR);
    }

    @Override
    public <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> focusedParametrizedTestExpectingException2(
            Class<T> expectedException, String description, TestClosureParams2<P1, P2> testBody) {
        return testsGroupNodeAccumulator.addParametrizedExceptionTest2(expectedException,
                description, testBody, TestCaseType.FOCUSED);
    }

    @Override
    public <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> ignoredParametrizedTestExpectingException2(
            Class<T> expectedException, String description, TestClosureParams2<P1, P2> testBody) {
        return testsGroupNodeAccumulator.addParametrizedExceptionTest2(expectedException,
                description, testBody, TestCaseType.IGNORED);
    }

    @Override
    public <P1, P2, P3> ParamsExpected3<P1, P2, P3> parametrizedTest3(String description,
            TestClosureParams3<P1, P2, P3> testBody) {
        return testsGroupNodeAccumulator.addParametrizedPositiveTest3(description, testBody, TestCaseType.REGULAR);
    }

    @Override
    public <P1, P2, P3> ParamsExpected3<P1, P2, P3> focusedParametrizedTest3(String description,
            TestClosureParams3<P1, P2, P3> testBody) {
        return testsGroupNodeAccumulator.addParametrizedPositiveTest3(description, testBody, TestCaseType.FOCUSED);
    }

    @Override
    public <P1, P2, P3> ParamsExpected3<P1, P2, P3> ignoredParametrizedTest3(String description,
            TestClosureParams3<P1, P2, P3> testBody) {
        return testsGroupNodeAccumulator.addParametrizedPositiveTest3(description, testBody, TestCaseType.IGNORED);
    }

    @Override
    public <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> parametrizedTestExpectingException3(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody) {
        return testsGroupNodeAccumulator.addParametrizedExceptionTest3(expectedException,
                description, testBody, TestCaseType.REGULAR);
    }

    @Override
    public <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> focusedParametrizedTestExpectingException3(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody) {
        return testsGroupNodeAccumulator.addParametrizedExceptionTest3(expectedException,
                description, testBody, TestCaseType.FOCUSED);
    }

    @Override
    public <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> ignoredParametrizedTestExpectingException3(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody) {
        return testsGroupNodeAccumulator.addParametrizedExceptionTest3(expectedException,
                description, testBody, TestCaseType.IGNORED);
    }

    @Override
    public <P> ParamsExpectedSubgroup<P> parametrizedSubSpecification1(String description, ConsumerParams1<P> specClosure) {
        return new ParamsExpectedSubgroup<>(description, specClosure, TestCaseType.REGULAR, this);
    }

    public void handleSubSpecification(String description, Runnable specClosure, TestCaseType testCaseType) {
        TestsGroupNodeAccumulator previous = this.testsGroupNodeAccumulator;
        TestsGroupNodeAccumulator subgroupAccumulator = previous.subgroupAccumulator(description, testCaseType);
        this.testsGroupNodeAccumulator = subgroupAccumulator;
        specClosure.run();
        previous.addSubgroup(subgroupAccumulator.build());
        this.testsGroupNodeAccumulator = previous;
    }

    public TreeNode<TestsGroup> spec() {
        return testsGroupNodeAccumulator.build();
    }
}
