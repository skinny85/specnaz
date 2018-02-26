package org.specnaz.impl;

import org.specnaz.TestSettings;
import org.specnaz.params.ParamsExpected1;
import org.specnaz.params.ParamsExpected2;
import org.specnaz.params.ParamsExpected3;
import org.specnaz.params.ParamsExpected4;
import org.specnaz.params.ParamsExpected5;
import org.specnaz.params.ParamsExpected6;
import org.specnaz.params.ParamsExpected7;
import org.specnaz.params.ParamsExpected8;
import org.specnaz.params.ParamsExpectedException1;
import org.specnaz.params.ParamsExpectedException2;
import org.specnaz.params.ParamsExpectedException3;
import org.specnaz.params.ParamsExpectedException4;
import org.specnaz.params.ParamsExpectedException5;
import org.specnaz.params.ParamsExpectedException6;
import org.specnaz.params.ParamsExpectedException7;
import org.specnaz.params.TestClosureParams1;
import org.specnaz.params.TestClosureParams2;
import org.specnaz.params.TestClosureParams3;
import org.specnaz.params.TestClosureParams4;
import org.specnaz.params.TestClosureParams5;
import org.specnaz.params.TestClosureParams6;
import org.specnaz.params.TestClosureParams7;
import org.specnaz.params.TestClosureParams8;
import org.specnaz.params.impl.AbstractParametrizedTest;
import org.specnaz.params.impl.ParametrizedExceptionTest1;
import org.specnaz.params.impl.ParametrizedExceptionTest2;
import org.specnaz.params.impl.ParametrizedExceptionTest3;
import org.specnaz.params.impl.ParametrizedExceptionTest4;
import org.specnaz.params.impl.ParametrizedExceptionTest5;
import org.specnaz.params.impl.ParametrizedExceptionTest6;
import org.specnaz.params.impl.ParametrizedExceptionTest7;
import org.specnaz.params.impl.ParametrizedPositiveTest1;
import org.specnaz.params.impl.ParametrizedPositiveTest2;
import org.specnaz.params.impl.ParametrizedPositiveTest3;
import org.specnaz.params.impl.ParametrizedPositiveTest4;
import org.specnaz.params.impl.ParametrizedPositiveTest5;
import org.specnaz.params.impl.ParametrizedPositiveTest6;
import org.specnaz.params.impl.ParametrizedPositiveTest7;
import org.specnaz.params.impl.ParametrizedPositiveTest8;
import org.specnaz.utils.TestClosure;
import org.specnaz.utils.ThrowableExpectations;

import java.util.LinkedList;
import java.util.List;

public final class TestsGroupNodeAccumulator {
    private final String description;
    private final TestCaseType testCaseType;
    private final List<TestClosure> beforeAlls = new LinkedList<>(),
                                    befores    = new LinkedList<>(),
                                    afters     = new LinkedList<>(),
                                    afterAlls  = new LinkedList<>();
    private final List<SingleTestCase> testCases  = new LinkedList<>();
    private final List<AbstractParametrizedTest> parametrizedTests = new LinkedList<>();
    private final List<TreeNode<TestsGroup>> subgroups = new LinkedList<>();
    private boolean containsFocusedTests = false;

    public TestsGroupNodeAccumulator(String description, TestCaseType testCaseType) {
        this.description = description;
        this.testCaseType = testCaseType;
        if (testCaseType == TestCaseType.FOCUSED)
            containsFocusedTests = true;
    }

    public void addBeforeAll(TestClosure closure) {
        beforeAlls.add(closure);
    }

    public void addBeforeEach(TestClosure closure) {
        befores.add(closure);
    }

    public TestSettings addPositiveTest(String description, TestClosure testBody, TestCaseType testCaseType) {
        TestSettings testSettings = new TestSettings();
        addTestCase(new SinglePositiveTestCase(testSettings,
                description, testBody, descendantTestType(testCaseType)));
        return testSettings;
    }

    public <T extends Throwable> ThrowableExpectations<T> addExceptionTest(Class<T> expectedException,
            String description, TestClosure testBody, TestCaseType testCaseType) {
        ThrowableExpectations<T> throwableExpectations = new ThrowableExpectations<>(expectedException);
        addTestCase(new SingleExceptionTestCase<>(throwableExpectations,
                description, testBody, descendantTestType(testCaseType)));
        return throwableExpectations;
    }

    public void addAfterEach(TestClosure closure) {
        afters.add(closure);
    }

    public void addAfterAll(TestClosure closure) {
        afterAlls.add(closure);
    }

    public TestsGroupNodeAccumulator subgroupAccumulator(String description, TestCaseType testCaseType) {
        return new TestsGroupNodeAccumulator(description, descendantTestType(testCaseType));
    }

    public void addSubgroup(TreeNode<TestsGroup> subgroupNode) {
        subgroups.add(subgroupNode);
    }

    public <P> ParamsExpected1<P> addParametrizedPositiveTest1(String description, TestClosureParams1<P> testBody,
            TestCaseType testCaseType) {
        return new ParamsExpected1<>(addParametrizedTest(new ParametrizedPositiveTest1<>(
                new TestSettings(), description, testBody, descendantTestType(testCaseType))));
    }

    public <T extends Throwable, P> ParamsExpectedException1<T, P> addParametrizedExceptionTest1(
            Class<T> expectedException, String description, TestClosureParams1<P> testBody, TestCaseType testCaseType) {
        return new ParamsExpectedException1<>(addParametrizedTest(new ParametrizedExceptionTest1<>(
                new ThrowableExpectations<>(expectedException), description, testBody, descendantTestType(testCaseType))));
    }

    public <P1, P2> ParamsExpected2<P1, P2> addParametrizedPositiveTest2(String description,
            TestClosureParams2<P1, P2> testBody, TestCaseType testCaseType) {
        return new ParamsExpected2<>(addParametrizedTest(new ParametrizedPositiveTest2<>(
                new TestSettings(), description, testBody, descendantTestType(testCaseType))));
    }

    public <T extends Throwable, P1, P2> ParamsExpectedException2<T, P1, P2> addParametrizedExceptionTest2(
            Class<T> expectedException, String description, TestClosureParams2<P1, P2> testBody, TestCaseType testCaseType) {
        return new ParamsExpectedException2<>(addParametrizedTest(new ParametrizedExceptionTest2<>(
                new ThrowableExpectations<>(expectedException), description, testBody, descendantTestType(testCaseType))));
    }

    public <P1, P2, P3> ParamsExpected3<P1, P2, P3> addParametrizedPositiveTest3(String description,
            TestClosureParams3<P1, P2, P3> testBody, TestCaseType testCaseType) {
        return new ParamsExpected3<>(addParametrizedTest(new ParametrizedPositiveTest3<>(
                new TestSettings(), description, testBody, descendantTestType(testCaseType))));
    }

    public <T extends Throwable, P1, P2, P3> ParamsExpectedException3<T, P1, P2, P3> addParametrizedExceptionTest3(
            Class<T> expectedException, String description, TestClosureParams3<P1, P2, P3> testBody, TestCaseType testCaseType) {
        return new ParamsExpectedException3<>(addParametrizedTest(new ParametrizedExceptionTest3<>(
                new ThrowableExpectations<>(expectedException), description, testBody, descendantTestType(testCaseType))));
    }

    public <P1, P2, P3, P4> ParamsExpected4<P1, P2, P3, P4> addParametrizedPositiveTest4(String description,
            TestClosureParams4<P1, P2, P3, P4> testBody, TestCaseType testCaseType) {
        return new ParamsExpected4<>(addParametrizedTest(new ParametrizedPositiveTest4<>(
                new TestSettings(), description, testBody, descendantTestType(testCaseType))));
    }

    public <T extends Throwable, P1, P2, P3, P4> ParamsExpectedException4<T, P1, P2, P3, P4> addParametrizedExceptionTest4(
            Class<T> expectedException, String description, TestClosureParams4<P1, P2, P3, P4> testBody, TestCaseType testCaseType) {
        return new ParamsExpectedException4<>(addParametrizedTest(new ParametrizedExceptionTest4<>(
                new ThrowableExpectations<>(expectedException), description, testBody, descendantTestType(testCaseType))));
    }

    public <P1, P2, P3, P4, P5> ParamsExpected5<P1, P2, P3, P4, P5> addParametrizedPositiveTest5(
            String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody, TestCaseType testCaseType) {
        return new ParamsExpected5<>(addParametrizedTest(new ParametrizedPositiveTest5<>(
                new TestSettings(), description, testBody, descendantTestType(testCaseType))));
    }

    public <T extends Throwable, P1, P2, P3, P4, P5> ParamsExpectedException5<T, P1, P2, P3, P4, P5> addParametrizedExceptionTest5(
            Class<T> expectedException, String description, TestClosureParams5<P1, P2, P3, P4, P5> testBody, TestCaseType testCaseType) {
        return new ParamsExpectedException5<>(addParametrizedTest(new ParametrizedExceptionTest5<>(
                new ThrowableExpectations<>(expectedException), description, testBody, descendantTestType(testCaseType))));
    }

    public <P1, P2, P3, P4, P5, P6> ParamsExpected6<P1, P2, P3, P4, P5, P6> addParametrizedPositiveTest6(
            String description, TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody, TestCaseType testCaseType) {
        return new ParamsExpected6<>(addParametrizedTest(new ParametrizedPositiveTest6<>(
                new TestSettings(), description, testBody, descendantTestType(testCaseType))));
    }

    public <T extends Throwable, P1, P2, P3, P4, P5, P6> ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> addParametrizedExceptionTest6(
            Class<T> expectedException, String description, TestClosureParams6<P1, P2, P3, P4, P5, P6> testBody, TestCaseType testCaseType) {
        return new ParamsExpectedException6<>(addParametrizedTest(new ParametrizedExceptionTest6<>(
                new ThrowableExpectations<>(expectedException), description, testBody, descendantTestType(testCaseType))));
    }

    public <P1, P2, P3, P4, P5, P6, P7> ParamsExpected7<P1, P2, P3, P4, P5, P6, P7> addParametrizedPositiveTest7(
            String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody, TestCaseType testCaseType) {
        return new ParamsExpected7<>(addParametrizedTest(new ParametrizedPositiveTest7<>(
                new TestSettings(), description, testBody, descendantTestType(testCaseType))));
    }

    public <T extends Throwable, P1, P2, P3, P4, P5, P6, P7> ParamsExpectedException7<T, P1, P2, P3, P4, P5, P6, P7> addParametrizedExceptionTest7(
            Class<T> expectedException, String description, TestClosureParams7<P1, P2, P3, P4, P5, P6, P7> testBody, TestCaseType testCaseType) {
        return new ParamsExpectedException7<>(addParametrizedTest(new ParametrizedExceptionTest7<>(
                new ThrowableExpectations<>(expectedException), description, testBody, descendantTestType(testCaseType))));
    }

    public <P1, P2, P3, P4, P5, P6, P7, P8> ParamsExpected8<P1, P2, P3, P4, P5, P6, P7, P8> addParametrizedPositiveTest8(
            String description, TestClosureParams8<P1, P2, P3, P4, P5, P6, P7, P8> testBody, TestCaseType testCaseType) {
        return new ParamsExpected8<>(addParametrizedTest(new ParametrizedPositiveTest8<>(
                new TestSettings(), description, testBody, descendantTestType(testCaseType))));
    }

    public TreeNode<TestsGroup> build() {
        // count tests in subgroups
        int testsInSubgroups = subgroups.stream().mapToInt(node -> node.value.testsInTree).sum();
        // see if any subgroup contains focused tests
        containsFocusedTests |= subgroups.stream().anyMatch(s -> s.value.containsFocusedTests);

        List<SingleTestCase> testCases = new LinkedList<>(this.testCases);
        for (AbstractParametrizedTest parametrizedTest : parametrizedTests) {
            testCases.addAll(parametrizedTest.testCases());
        }

        TestsGroup testsGroup = new TestsGroup(description, beforeAlls, befores,
                testCases, afters, afterAlls, testsInSubgroups, containsFocusedTests);
        TreeNode<TestsGroup> testsGroupTreeNode = new TreeNode<>(testsGroup);
        for (TreeNode<TestsGroup> subgroupNode: subgroups) {
            testsGroupTreeNode.attach(subgroupNode);
            incrementFixturesCount(subgroupNode);
        }
        return testsGroupTreeNode;
    }

    private void incrementFixturesCount(TreeNode<TestsGroup> testsGroupNode) {
        TestsGroup testsGroup = testsGroupNode.value;
        testsGroup.incrementBeforeAllsCount(beforeAlls.size());
        testsGroup.incrementAfterAllsCount(afterAlls.size());

        for (TreeNode<TestsGroup> child : testsGroupNode.children()) {
            incrementFixturesCount(child);
        }
    }

    private void addTestCase(SingleTestCase testCase) {
        if (testCase.type == TestCaseType.FOCUSED)
            containsFocusedTests = true;

        testCases.add(testCase);
    }

    private <T extends AbstractParametrizedTest> T addParametrizedTest(T parametrizedTest) {
        if (parametrizedTest.testCaseType == TestCaseType.FOCUSED)
            containsFocusedTests = true;

        parametrizedTests.add(parametrizedTest);

        return parametrizedTest;
    }

    private TestCaseType descendantTestType(TestCaseType testCaseType) {
        return this.testCaseType.descendantTestType(testCaseType);
    }
}
