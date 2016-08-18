package org.specnaz.impl

import org.specnaz.SpecnazSuiteBuilder
import org.specnaz.impl.tree.TreeNode

class TestPlanSuiteBuilder(private val groupDescription: String) :
        SpecnazSuiteBuilder {
    private var testsInThisGroup: List<PlannedTest> = emptyList()
    private var subGroups: List<TreeNode<PlannedTestGroup>> = emptyList()

    val testPlan: TreeNode<PlannedTestGroup>
        get() {
            val testsInSubtree = testsInThisGroup.size + subGroups.map{it.value.testsInSubtree}.sum()

            val rootNode = TreeNode(
                    PlannedTestGroup(groupDescription, testsInThisGroup, testsInSubtree))

            for (subGroupNode in subGroups)
                rootNode.attach(subGroupNode)

            return rootNode
        }

    override fun should(description: String, testBody: (Nothing?) -> Unit) {
        testsInThisGroup += PlannedTest(description)
    }

    override fun spec(description: String, subSpec: (SpecnazSuiteBuilder) -> Unit) {
        val subGroupTestPlanSuiteBuilder = TestPlanSuiteBuilder(description)
        subSpec.invoke(subGroupTestPlanSuiteBuilder)
        subGroups += subGroupTestPlanSuiteBuilder.testPlan
    }

    override fun beforeAll(setup: (Nothing?) -> Unit) {
    }

    override fun beforeEach(setup: (Nothing?) -> Unit) {
    }

    override fun afterEach(teardown: (Nothing?) -> Unit) {
    }

    override fun afterAll(teardown: (Nothing?) -> Unit) {
    }
}
