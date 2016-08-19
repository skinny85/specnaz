package org.specnaz.impl

import org.specnaz.impl.tree.TreeNode

class SpecnazTestsGroupNodeExecutor(private val testsGroupNode: TreeNode<TestsGroup>,
                                    private val notifier: Notifier) {
    fun run() {
        runCurrentNodeTestsGroup()

        for (subGroupTestsNode in testsGroupNode.children) {
            if (subGroupTestsNode.value.testsInSubtree > 0) {
                SpecnazTestsGroupNodeExecutor(
                        subGroupTestsNode,
                        notifier.subgroup(subGroupTestsNode.value.groupDescription))
                        .run()
            }
        }
    }

    private fun runCurrentNodeTestsGroup() {
        val testsGroup = testsGroupNode.value

        if (testsGroup.testCases.isEmpty()) {
            // No point in running anything if there are no tests,
            // so we just return immediately.
            return
        }

        val beforeAllsError = invokeBeforeAlls()
        if (beforeAllsError != null) {
            notifier.setupFailed(beforeAllsError)
        }

        for (testCase in testsGroup.testCases) {
            runSingleTestCase(testCase, beforeAllsError)
        }

        val afterAllsError = invokeAfterAlls()
        if (afterAllsError != null) {
            notifier.teardownFailed(afterAllsError)
        }
    }

    private fun runSingleTestCase(testCase: TestCase, beforeAllsError: Throwable?) {
        if (beforeAllsError == null) {
            runSingleTestCase(testCase)
        } else {
            // If any of the 'beforeAll' methods failed,
            // mark the test as failed.
            // We might make it a config option later (what to do
            // in this case - ignore the tests, or fail them).
            notifier.started(testCase)
            notifier.failed(testCase, beforeAllsError)
        }
    }

    private fun runSingleTestCase(testCase: TestCase) {
        notifier.started(testCase)

        var e = invokeBefores()

        e = invokeTestBody(testCase, e)

        e = invokeAfters(e)

        if (e == null)
            notifier.passed(testCase)
        else
            notifier.failed(testCase, e)
    }

    private fun invokeBeforeAlls() =
            recursivelyInvokeFixturesAncestorsFirst(testsGroupNode, { it.beforeAlls })

    private fun invokeBefores() =
            recursivelyInvokeFixturesAncestorsFirst(testsGroupNode, { it.befores })

    private fun invokeTestBody(testCase: TestCase, previousError: Throwable?) =
            // we only run the test if none of the 'beforeEach' methods failed
            previousError ?: invokeCallback(testCase.testBody)

    private fun invokeAfters(previousError: Throwable?): Throwable? {
        val aftersError = recursivelyInvokeFixturesAncestorsLast(testsGroupNode, { it.afters })
        return previousError ?: aftersError
    }

    private fun invokeAfterAlls() =
            recursivelyInvokeFixturesAncestorsLast(testsGroupNode, { it.afterAlls })

    private fun recursivelyInvokeFixturesAncestorsFirst(testsGroupNode: TreeNode<TestsGroup>?,
                                                        extractor: (TestsGroup) -> List<(Nothing?) -> Unit>):
            Throwable? {
        if (testsGroupNode == null)
            return null

        val ancestorsError = recursivelyInvokeFixturesAncestorsFirst(testsGroupNode.parent, extractor)

        val myError = invokeFixtures(extractor(testsGroupNode.value))

        return ancestorsError ?: myError
    }

    private fun recursivelyInvokeFixturesAncestorsLast(testsGroupNode: TreeNode<TestsGroup>?,
                                                       extractor: (TestsGroup) -> List<(Nothing?) -> Unit>):
            Throwable? {
        if (testsGroupNode == null)
            return null

        val myError = invokeFixtures(extractor(testsGroupNode.value))

        val ancestorsError = recursivelyInvokeFixturesAncestorsLast(testsGroupNode.parent, extractor)

        return myError ?: ancestorsError
    }

    private fun invokeFixtures(fixtures: List<(Nothing?) -> Unit>,
                               previousError: Throwable? = null): Throwable? {
        var ret: Throwable? = previousError
        for (fixture in fixtures) {
            val result = invokeCallback(fixture)
            if (ret == null)
                ret = result
        }
        return ret
    }

    private fun invokeCallback(callback: (Nothing?) -> Unit): Throwable? {
        try {
            callback.invoke(null)
            return null
        } catch (e: AssertionError) {
            return e
        } catch (e: Exception) {
            return e
        }
    }
}
