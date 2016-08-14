package org.specnaz.junit

import org.junit.runner.Description
import org.junit.runner.Runner
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunNotifier
import org.specnaz.Specnaz
import org.specnaz.impl.AccumulatingSuiteBuilder
import org.specnaz.impl.SpecnazTest

class SpecnazJUnitRunner(private val testsClass: Class<*>) : Runner() {
    private val specnaz: Specnaz

    init {
        specnaz = testsClass.newInstance() as Specnaz
    }

    override fun getDescription(): Description? {
        val rootDescription = Description.createSuiteDescription(testsClass)

        val accumulatingSuiteBuilder = AccumulatingSuiteBuilder()
        specnaz.tests().invoke(accumulatingSuiteBuilder)

        for (test in accumulatingSuiteBuilder.tests) {
            rootDescription.addChild(childTestDescription(test))
        }

        return rootDescription
    }

    override fun run(notifier: RunNotifier) {
        val accumulatingSuiteBuilder = AccumulatingSuiteBuilder()

        specnaz.tests().invoke(accumulatingSuiteBuilder)

        for (test in accumulatingSuiteBuilder.tests) {
            val testJUnitDescription = childTestDescription(test)

            notifier.fireTestStarted(testJUnitDescription)
            try {
                test.testBody.invoke()
            } catch (e: Exception) {
                notifier.fireTestFailure(Failure(testJUnitDescription, e))
            } catch (e: AssertionError) {
                notifier.fireTestFailure(Failure(testJUnitDescription, e))
            } finally {
                notifier.fireTestFinished(testJUnitDescription)
            }
        }
    }

    private fun childTestDescription(test: SpecnazTest) =
            Description.createTestDescription(testsClass, test.description)
}
