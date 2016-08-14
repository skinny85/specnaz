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
            SpecnazSingleTestExecutor(test, notifier).run()
        }
    }
}

class TestPlan(val plannedTests: List<PlannedTest>)
