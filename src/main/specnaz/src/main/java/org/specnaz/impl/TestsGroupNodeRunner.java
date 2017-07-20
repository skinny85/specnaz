package org.specnaz.impl;

import org.specnaz.utils.TestClosure;

import java.util.List;
import java.util.function.Function;

public class TestsGroupNodeRunner {
    private final TreeNode<TestsGroup> testsGroupNode;
    private final Notifier notifier;

    public TestsGroupNodeRunner(TreeNode<TestsGroup> testsGroupNode, Notifier notifier) {
        this.testsGroupNode = testsGroupNode;
        this.notifier = notifier;
    }

    public void run() {
        runCurrentNodeTestsGroup();

        for (TreeNode<TestsGroup> subGroupTestsNode : testsGroupNode.children()) {
            if (subGroupTestsNode.value.testsInTree > 0) {
                new TestsGroupNodeRunner(
                        subGroupTestsNode,
                        notifier.subgroup(subGroupTestsNode.value.description))
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

        Throwable beforeAllsError = invokeBeforeAlls();

        for (SingleTestCase testCase : testsGroup.testCases) {
            runSingleTestCase(testCase, beforeAllsError);
        }

        invokeAfterAlls();
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
        notifier.started(testCase);

        Throwable e = invokeBefores();

        e = invokeTestBody(testCase, e);

        e = invokeAfters(e);

        if (e == null)
            notifier.passed(testCase);
        else
            notifier.failed(testCase, e);
    }

    private Throwable invokeBeforeAlls() {
        if (testsGroupNode.value.beforeAllsCount() == 0)
            return null;

        notifier.setupStarted();
        Throwable beforeAllsError = recursivelyInvokeFixturesAncestorsFirst(testsGroupNode, g -> g.beforeAlls);
        if (beforeAllsError == null) {
            notifier.setupSucceeded();
        } else {
            notifier.setupFailed(beforeAllsError);
        }
        return beforeAllsError;
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

    private void invokeAfterAlls() {
        if (testsGroupNode.value.afterAllsCount() == 0)
            return;

        notifier.teardownStarted();
        Throwable afterAllsError = recursivelyInvokeFixturesAncestorsLast(testsGroupNode, g -> g.afterAlls);
        if (afterAllsError == null)
            notifier.teardownSucceeded();
        else
            notifier.teardownFailed(afterAllsError);
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
