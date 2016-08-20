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
        if (beforeAllsError != null) {
            notifier.setupFailed(beforeAllsError);
        }

        for (SingleTestCase testCase : testsGroup.testCases) {
            runSingleTestCase(testCase, beforeAllsError);
        }

        Throwable afterAllsError = invokeAfterAlls();
        if (afterAllsError != null) {
            notifier.teardownFailed(afterAllsError);
        }
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
        return recursivelyInvokeFixturesAncestorsFirst(testsGroupNode, g -> g.beforeAlls);
    }

    private Throwable invokeBefores() {
        return recursivelyInvokeFixturesAncestorsFirst(testsGroupNode, g -> g.befores);
    }

    private Throwable invokeTestBody(SingleTestCase testCase, Throwable previousError) {
        // we only run the test if none of the 'beforeEach' methods failed
        return previousError == null
                ? invokeCallback(testCase.testBody)
                : previousError;
    }

    private Throwable invokeAfters(Throwable previousError) {
        Throwable aftersError = recursivelyInvokeFixturesAncestorsLast(testsGroupNode, g -> g.afters);
        return previousError == null ? aftersError : previousError;
    }

    private Throwable invokeAfterAlls() {
        return recursivelyInvokeFixturesAncestorsLast(testsGroupNode, g -> g.afterAlls);
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
        return invokeFixtures(fixtures, null);
    }

    private Throwable invokeFixtures(List<TestClosure> fixtures,
                                     Throwable previousError) {
        Throwable ret = previousError;
        for (TestClosure fixture : fixtures) {
            Throwable result = invokeCallback(fixture);
            if (ret == null)
                ret = result;
        }
        return ret;
    }

    private Throwable invokeCallback(TestClosure callback) {
        try {
            callback.invoke();
            return null;
        } catch (AssertionError e) {
            return e;
        } catch (Exception e) {
            return e;
        }
    }
}
