package org.specnaz.impl;

import org.specnaz.utils.TestClosure;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TestsGroupNodeExecutor {
    private final TreeNode<TestsGroup> testsGroupNode;
    private final boolean runOnlyFocusedTests;

    public TestsGroupNodeExecutor(TreeNode<TestsGroup> testsGroupNode, boolean runOnlyFocusedTests) {
        this.testsGroupNode = testsGroupNode;
        this.runOnlyFocusedTests = runOnlyFocusedTests;
    }

    public Collection<TestsGroupNodeExecutor> testsGroupNodeExecutors() {
        return streamOfTestsGroupNodeExecutors().collect(Collectors.toList());
    }

    public Executable beforeAllsExecutable() {
        return this::invokeBeforeAlls;
    }

    public Collection<ExecutableTestCase> executableTestCases(Throwable beforeAllsError) {
        return testCases().stream().map(testCase -> shouldIgnoreTest(testCase)
                ? new ExecutableTestCase(testCase)
                : new ExecutableTestCase(testCase, () -> runSingleTestCase(testCase, beforeAllsError))
        ).collect(Collectors.toList());
    }

    public Executable afterAllsExecutable() {
        return this::invokeAfterAlls;
    }

    public List<String> descriptionsPath() {
        List<String> ret = new LinkedList<>();
        descriptionsPath(testsGroupNode, ret);
        return Collections.unmodifiableList(ret);
    }

    private Stream<TestsGroupNodeExecutor> streamOfTestsGroupNodeExecutors() {
        Stream<TestsGroupNodeExecutor> ret = testCases().isEmpty()
                ? Stream.empty()
                : Stream.of(this);
        return Stream.concat(
                ret,
                testsGroupNode.children().stream()
                        .map(child -> new TestsGroupNodeExecutor(child, runOnlyFocusedTests))
                        .flatMap(TestsGroupNodeExecutor::streamOfTestsGroupNodeExecutors));
    }

    private void descriptionsPath(TreeNode<TestsGroup> testsGroupNode, List<String> ret) {
        if (testsGroupNode == null)
            return;

        descriptionsPath(testsGroupNode.parent(), ret);
        ret.add(testsGroupNode.value.description);
    }

    private Throwable invokeBeforeAlls() {
        return allTestsInGroupAreIgnored()
                ? null
                : recursivelyInvokeFixturesAncestorsFirst(testsGroupNode, g -> g.beforeAlls);
    }

    private Throwable runSingleTestCase(SingleTestCase testCase, Throwable beforeAllsError) {
        return beforeAllsError == null
                ? shouldIgnoreTest(testCase) ? null : runSingleTestCase(testCase)
                : beforeAllsError;
    }

    private Throwable runSingleTestCase(SingleTestCase testCase) {
        Throwable e = invokeBefores();

        e = invokeTestBody(testCase, e);

        e = invokeAfters(e);

        return e;
    }

    private Throwable invokeBefores() {
        return recursivelyInvokeFixturesAncestorsFirst(testsGroupNode, g -> g.befores);
    }

    private Throwable invokeTestBody(SingleTestCase testCase, Throwable previousError) {
        // we only run the test if none of the 'beforeEach' methods failed
        return previousError == null
                ? testCase.exercise()
                : previousError;
    }

    private Throwable invokeAfters(Throwable previousError) {
        Throwable aftersError = recursivelyInvokeFixturesAncestorsLast(testsGroupNode, g -> g.afters);
        return previousError == null ? aftersError : previousError;
    }

    private Throwable invokeAfterAlls() {
        return allTestsInGroupAreIgnored()
                ? null
                : recursivelyInvokeFixturesAncestorsLast(testsGroupNode, g -> g.afterAlls);
    }

    private Throwable recursivelyInvokeFixturesAncestorsFirst(
            TreeNode<TestsGroup> testsGroupNode,
            Function<TestsGroup, List<TestClosure>> extractor) {
        if (testsGroupNode == null)
            return null;

        Throwable ancestorsError = recursivelyInvokeFixturesAncestorsFirst(testsGroupNode.parent(), extractor);

        Throwable myError = invokeFixtures(extractor.apply(testsGroupNode.value));

        return ancestorsError == null ? myError : ancestorsError;
    }

    private Throwable recursivelyInvokeFixturesAncestorsLast(
            TreeNode<TestsGroup> testsGroupNode,
            Function<TestsGroup, List<TestClosure>> extractor) {
        if (testsGroupNode == null)
            return null;

        Throwable myError = invokeFixtures(extractor.apply(testsGroupNode.value));

        Throwable ancestorsError = recursivelyInvokeFixturesAncestorsLast(testsGroupNode.parent(), extractor);

        return myError == null ? ancestorsError : myError;
    }

    private Throwable invokeFixtures(List<TestClosure> fixtures) {
        Throwable ret = null;
        for (TestClosure fixture : fixtures) {
            Throwable result = SingleTestCase.invokeCallback(fixture);
            if (ret == null)
                ret = result;
        }
        return ret;
    }

    private boolean allTestsInGroupAreIgnored() {
        return testCases().stream().allMatch(this::shouldIgnoreTest);
    }

    private Collection<SingleTestCase> testCases() {
        return testsGroupNode.value.testCases;
    }

    private boolean shouldIgnoreTest(SingleTestCase testCase) {
        return testCase.type == TestCaseType.IGNORED ||
                (runOnlyFocusedTests && testCase.type != TestCaseType.FOCUSED);
    }
}
