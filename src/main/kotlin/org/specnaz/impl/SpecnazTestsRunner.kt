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

        SpecnazTestsGroupExecutor(testExecutionSuiteBuilder.tests, notifier).run()
    }
}

class TestPlan(val plannedTests: List<PlannedTest>)

class TestsGroup(val beforeAlls: List<(Nothing?) -> Unit>,
                 val befores: List<(Nothing?) -> Unit>,
                 val testCases: List<TestCase>,
                 val afters: List<(Nothing?) -> Unit>,
                 val afterAlls: List<(Nothing?) -> Unit>)
