package org.specnaz.impl

import org.specnaz.Specnaz
import org.specnaz.impl.tree.TreeNode

class SpecnazTestsRunner(private val specnaz: Specnaz) {
    val name = specnaz.javaClass.simpleName

    private val testPlan: TestPlan by lazy {
        val testPlanSuiteBuilder = TestPlanSuiteBuilder(name)
        specnaz.tests().invoke(testPlanSuiteBuilder)
        TestPlan(testPlanSuiteBuilder.testPlan)
    }

    fun testPlan() = testPlan

    fun executeTests(notifier: Notifier) {
        val testExecutionSuiteBuilder = TestExecutionSuiteBuilder(name)

        specnaz.tests().invoke(testExecutionSuiteBuilder)

        SpecnazTestsGroupNodeExecutor(testExecutionSuiteBuilder.tests, notifier).run()
    }
}

class TestPlan(val plannedTests: TreeNode<PlannedTestGroup>)

class PlannedTestGroup(val groupDescription: String, val testsInThisGroup: List<PlannedTest>)

class TestsGroup(val groupDescription: String,
                 val beforeAlls: List<(Nothing?) -> Unit>,
                 val befores: List<(Nothing?) -> Unit>,
                 val testCases: List<TestCase>,
                 val afters: List<(Nothing?) -> Unit>,
                 val afterAlls: List<(Nothing?) -> Unit>)
