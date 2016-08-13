package org.specnaz.junit

import org.junit.runner.Description
import org.junit.runner.Runner
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunNotifier
import org.specnaz.Specnaz

class SpecnazJUnitRunner(private val testsClass: Class<*>) : Runner() {
    private val specnaz: Specnaz

    init {
        specnaz = testsClass.newInstance() as Specnaz
    }

    override fun getDescription(): Description? {
        val description = Description.createSuiteDescription(testsClass)
        description.addChild(childTest())
        return description
    }

    override fun run(notifier: RunNotifier) {
        notifier.fireTestStarted(childTest())
        try {
            specnaz.tests().invoke()
        } catch (e: Exception) {
            notifier.fireTestFailure(Failure(childTest(), e))
        } catch (e: AssertionError) {
            notifier.fireTestFailure(Failure(childTest(), e))
        } finally {
            notifier.fireTestFinished(childTest())
        }
    }

    private fun childTest() = Description.createTestDescription(testsClass, "specnaz tests")
}
