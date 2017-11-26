package org.specnaz.impl;

import org.specnaz.utils.TestClosure;

import java.util.List;
import java.util.function.Function;

public class TestsGroupNodeRunner {
    private final TreeNode<TestsGroup> testsGroupNode;
    private final Notifier notifier;
    private final boolean runOnlyFocusedTests;

    public TestsGroupNodeRunner(TreeNode<TestsGroup> testsGroupNode, Notifier notifier, boolean runOnlyFocusedTests) {
        this.testsGroupNode = testsGroupNode;
        this.notifier = notifier;
        this.runOnlyFocusedTests = runOnlyFocusedTests;
    }

    public void run() {
        runCurrentNodeTestsGroup();

        for (TreeNode<TestsGroup> subGroupTestsNode : testsGroupNode.children()) {
            if (subGroupTestsNode.value.testsInTree > 0) {
                new TestsGroupNodeRunner(
                        subGroupTestsNode,
                        notifier.subgroup(subGroupTestsNode.value.description), runOnlyFocusedTests)
                        .run();
            }
        }
    }

    private void runCurrentNodeTestsGroup() {
        TestsGroup testsGroup = testsGroupNode.value;

        if (testsGroup.testCases.isEmpty()) {
            // No point in running anything if there are no tests,
            // so we just return immediately.
            return;
        }

        boolean skipAllsFixtures = shouldSkipAllsFixtures(testsGroup);

        Throwable beforeAllsError = invokeBeforeAlls(skipAllsFixtures);

        for (SingleTestCase testCase : testsGroup.testCases) {
            runSingleTestCase(testCase, beforeAllsError);
        }

        invokeAfterAlls(skipAllsFixtures);
    }

    private boolean shouldSkipAllsFixtures(TestsGroup testsGroup) {
        // There are 2 situations when it doesn't make sense to run the beforeAll/afterAll
        // fixtures for a given group:
        // 1. If we're supposed to run only focused tests, but this group doesn't contain any.
        // 2. If this group contains only ignored tests.
        return (runOnlyFocusedTests &&
                testsGroup.testCases.stream().noneMatch(tc -> tc.type == TestCaseType.FOCUSED)) ||
                (testsGroup.testCases.stream().allMatch(tc -> tc.type == TestCaseType.IGNORED));
    }

    private void runSingleTestCase(SingleTestCase testCase, Throwable beforeAllsError) {
        if (beforeAllsError == null) {
            runSingleTestCase(testCase);
        } else {
            // If any of the 'beforeAll' methods failed,
            // mark the test as failed.
            // We might make it a config option later (what to do
            // in this case - ignore the tests, or fail them).
            notifier.started(testCase);
            notifier.failed(testCase, beforeAllsError);
        }
    }

    private void runSingleTestCase(SingleTestCase testCase) {
        if (testCase.type == TestCaseType.IGNORED ||
                (runOnlyFocusedTests && testCase.type == TestCaseType.REGULAR)) {
            notifier.ignored(testCase);
            return;
        }

        notifier.started(testCase);

        Throwable e = invokeBefores();

        e = invokeTestBody(testCase, e);

        e = invokeAfters(e);

        if (e == null)
            notifier.passed(testCase);
        else
            notifier.failed(testCase, e);
    }

    private Throwable invokeBeforeAlls(boolean skipAllsFixtures) {
        if (testsGroupNode.value.beforeAllsCount() == 0)
            return null;

        notifier.setupStarted();

        if (skipAllsFixtures) {
            notifier.setupSucceeded();
            return null;
        } else {
            Throwable beforeAllsError = recursivelyInvokeFixturesAncestorsFirst(testsGroupNode, g -> g.beforeAlls);
            if (beforeAllsError == null) {
                notifier.setupSucceeded();
            } else {
                notifier.setupFailed(beforeAllsError);
            }
            return beforeAllsError;
        }
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

    private void invokeAfterAlls(boolean skipAllsFixtures) {
        if (testsGroupNode.value.afterAllsCount() == 0)
            return;

        notifier.teardownStarted();

        if (skipAllsFixtures) {
            notifier.teardownSucceeded();
        } else {
            Throwable afterAllsError = recursivelyInvokeFixturesAncestorsLast(testsGroupNode, g -> g.afterAlls);
            if (afterAllsError == null)
                notifier.teardownSucceeded();
            else
                notifier.teardownFailed(afterAllsError);
        }
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
}
