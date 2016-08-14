package org.specnaz.junit.impl

import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunNotifier
import org.specnaz.impl.Notifier
import org.specnaz.impl.SpecnazTest

class JUnitNotifier(private val runNotifier: RunNotifier,
                    private val testsClass: Class<*>) :
        Notifier {
    override fun started(test: SpecnazTest) {
        runNotifier.fireTestStarted(testDescription(test))
    }

    override fun passed(test: SpecnazTest) {
        runNotifier.fireTestFinished(testDescription(test))
    }

    override fun failed(test: SpecnazTest, assertion: AssertionError) {
        threw(test, assertion)
    }

    override fun threw(test: SpecnazTest, error: Throwable) {
        val testDescription = testDescription(test)
        runNotifier.fireTestFailure(Failure(testDescription, error))
        runNotifier.fireTestFinished(testDescription)
    }

    override fun ignored(test: SpecnazTest) {
        val testDescription = testDescription(test)
        runNotifier.fireTestIgnored(testDescription)
        runNotifier.fireTestFinished(testDescription)
    }

    private fun testDescription(test: SpecnazTest) =
            JUnitUtils.testDescription(test, testsClass)
}
