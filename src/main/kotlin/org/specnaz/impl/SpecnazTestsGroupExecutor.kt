package org.specnaz.impl

class SpecnazTestsGroupExecutor(private val testsGroup: TestsGroup,
                                private val notifier: Notifier) {
    fun run() {
        if (testsGroup.testCases.isEmpty()) {
            // No point in running anything if there are no tests,
            // so we just return immediately.
            return
        }

        val e = invokeFixtures(testsGroup.beforeAlls)

        if (e != null) {
            val fakeTestCase = TestCase("not fail in a beforeAll method", {})
            notifier.started(fakeTestCase)
            notifier.threw(fakeTestCase, e)
        }

        for (testCase in testsGroup.testCases) {
            runSingleTestCase(testCase, e)
        }
    }

    private fun runSingleTestCase(testCase: TestCase, beforeAllsError: Throwable?) {
        if (beforeAllsError == null) {
            runSingleTestCase(testCase)
        } else {
            // If any of the 'beforeAll' methods failed,
            // mark the test as ignored.
            notifier.ignored(testCase)
        }
    }

    private fun runSingleTestCase(testCase: TestCase) {
        notifier.started(testCase)

        var e = invokeFixtures(testsGroup.befores)

        e = invokeTestBody(testCase, e)

        e = invokeFixtures(testsGroup.afters, e)

        if (e == null) {
            notifier.passed(testCase)
        } else {
            if (e is AssertionError)
                notifier.failed(testCase, e)
            else
                notifier.threw(testCase, e)
        }
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

    private fun invokeTestBody(testCase: TestCase, previousError: Throwable?) =
            // we only run the test if none of the 'beforeEach' methods failed
            previousError ?: invokeCallback(testCase.testBody)

    private fun invokeCallback(fixture: (Nothing?) -> Unit): Throwable? {
        try {
            fixture.invoke(null)
            return null
        } catch (e: AssertionError) {
            return e
        } catch (e: Exception) {
            return e
        }
    }
}
