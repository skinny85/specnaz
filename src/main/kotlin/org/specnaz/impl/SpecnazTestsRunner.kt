package org.specnaz.impl

import org.specnaz.Specnaz

class SpecnazTestsRunner(private val specnaz: Specnaz) {
    private val testPlan: TestPlan by lazy {
        val testPlanSuiteBuilder = TestPlanSuiteBuilder()
        specnaz.tests().invoke(testPlanSuiteBuilder)
        TestPlan(testPlanSuiteBuilder.plannedTests)
    }

    fun testPlan() = testPlan

    fun executeTests(notifier: Notifier) {
        val testExecutionSuiteBuilder = TestExecutionSuiteBuilder()

        specnaz.tests().invoke(testExecutionSuiteBuilder)

        for (test in testExecutionSuiteBuilder.tests) {
            notifier.started(test)
            try {
                test.testBody.invoke(null)
                notifier.passed(test)
            } catch (e: AssertionError) {
                notifier.failed(test, e)
            } catch (e: Exception) {
                notifier.threw(test, e)
            }
        }
    }
}

class TestPlan(val plannedTests: List<PlannedTest>)
