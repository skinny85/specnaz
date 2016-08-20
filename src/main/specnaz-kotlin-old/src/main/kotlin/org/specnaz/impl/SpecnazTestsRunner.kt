package org.specnaz.impl

import org.specnaz.SpecName
import org.specnaz.SpecnazOld
import org.specnaz.impl.tree.TreeNode

class SpecnazTestsRunner(private val specnaz: SpecnazOld) {
    val name = specnaz.javaClass.getAnnotation(SpecName::class.java)?.value
            ?: specnaz.javaClass.simpleName

    private val testPlan: TestsTree by lazy {
        val suiteBuilder = TestsTreeSuiteBuilder(name)
        specnaz.tests().invoke(suiteBuilder)
        TestsTree(suiteBuilder.tests)
    }

    fun testPlan() = testPlan

    fun executeTests(notifier: Notifier) {
        SpecnazTestsGroupNodeExecutor(testPlan.rootTestsGroup, notifier).run()
    }
}

class TestsTree(val rootTestsGroup: TreeNode<TestsGroup>)

class TestsGroup(val groupDescription: String,
                 val beforeAlls: List<(Nothing?) -> Unit>,
                 val befores: List<(Nothing?) -> Unit>,
                 val testCases: List<TestCase>,
                 val afters: List<(Nothing?) -> Unit>,
                 val afterAlls: List<(Nothing?) -> Unit>,
                 val testsInSubtree: Int)
