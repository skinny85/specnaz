package org.specnaz.junit

import org.junit.runner.Description
import org.junit.runner.Runner
import org.junit.runner.notification.RunNotifier
import org.specnaz.Specnaz
import org.specnaz.impl.PlannedTest
import org.specnaz.impl.SpecnazTestsRunner
import org.specnaz.junit.impl.JUnitNotifier
import org.specnaz.junit.impl.JUnitUtils

class SpecnazJUnitRunner(private val testsClass: Class<*>) : Runner() {
    private val specnaz: Specnaz
    private val specnazTestsRunner: SpecnazTestsRunner

    init {
        specnaz = testsClass.newInstance() as Specnaz
        specnazTestsRunner = SpecnazTestsRunner(specnaz)
    }

    override fun getDescription(): Description? {
        val testPlan = specnazTestsRunner.testPlan()

        val rootDescription = Description.createSuiteDescription(testsClass)

        for (plannedTest in testPlan.plannedTests) {
            rootDescription.addChild(testDescription(plannedTest))
        }

        return rootDescription
    }

    override fun run(runNotifier: RunNotifier) {
        specnazTestsRunner.executeTests(JUnitNotifier(runNotifier, testsClass))
    }

    private fun testDescription(plannedTest: PlannedTest) =
            JUnitUtils.testDescription(plannedTest, testsClass)
}
