package org.specnaz.junit.impl

import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunNotifier
import org.specnaz.impl.Notifier
import org.specnaz.impl.TestCase

class JUnitNotifier(private val runNotifier: RunNotifier,
                    private val testsClass: Class<*>) :
        Notifier {
    override fun started(test: TestCase) {
        runNotifier.fireTestStarted(testDescription(test))
    }

    override fun passed(test: TestCase) {
        runNotifier.fireTestFinished(testDescription(test))
    }

    override fun failed(test: TestCase, assertion: AssertionError) {
        threw(test, assertion)
    }

    override fun threw(test: TestCase, error: Throwable) {
        val testDescription = testDescription(test)
        runNotifier.fireTestFailure(Failure(testDescription, error))
        runNotifier.fireTestFinished(testDescription)
    }

    override fun ignored(test: TestCase) {
        val testDescription = testDescription(test)
        runNotifier.fireTestIgnored(testDescription)
        runNotifier.fireTestFinished(testDescription)
    }

    private fun testDescription(test: TestCase) =
            JUnitUtils.testDescription(test, testsClass)
}
