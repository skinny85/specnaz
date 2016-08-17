package org.specnaz.impl

import org.specnaz.SpecnazSuiteBuilder
import org.specnaz.impl.tree.TreeNode

class TestExecutionSuiteBuilder(private val groupDescription: String) : SpecnazSuiteBuilder {
    private var beforeAlls: List<(Nothing?) -> Unit> = emptyList()
    private var befores: List<(Nothing?) -> Unit> = emptyList()
    private var testCases: List<TestCase> = emptyList()
    private var afters: List<(Nothing?) -> Unit> = emptyList()
    private var afterAlls: List<(Nothing?) -> Unit> = emptyList()
    private var subGroups: List<TreeNode<TestsGroup>> = emptyList()

    val tests: TreeNode<TestsGroup>
        get() {
            val rootNode = TreeNode(
                    TestsGroup(
                            groupDescription,
                            beforeAlls,
                            befores,
                            testCases,
                            afters,
                            afterAlls))

            for (subGroupNode in subGroups)
                rootNode.attach(subGroupNode)

            return rootNode
        }

    override fun beforeAll(setup: (Nothing?) -> Unit) {
        beforeAlls += setup
    }

    override fun beforeEach(setup: (Nothing?) -> Unit) {
        befores += setup
    }

    override fun should(description: String, testBody: (Nothing?) -> Unit) {
        testCases += TestCase(description, testBody)
    }

    override fun afterEach(teardown: (Nothing?) -> Unit) {
        afters += teardown
    }

    override fun afterAll(teardown: (Nothing?) -> Unit) {
        afterAlls += teardown
    }

    override fun spec(description: String, subSpec: (SpecnazSuiteBuilder) -> Unit) {
        val subGroupsTestExecutionSuiteBuilder = TestExecutionSuiteBuilder(description)
        subSpec.invoke(subGroupsTestExecutionSuiteBuilder)
        subGroups += subGroupsTestExecutionSuiteBuilder.tests
    }
}
