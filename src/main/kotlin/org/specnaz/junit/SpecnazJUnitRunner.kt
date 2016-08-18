package org.specnaz.junit

import org.junit.runner.Description
import org.junit.runner.Description.createSuiteDescription
import org.junit.runner.Runner
import org.junit.runner.notification.RunNotifier
import org.specnaz.Specnaz
import org.specnaz.impl.PlannedTestGroup
import org.specnaz.impl.SpecnazTestsRunner
import org.specnaz.impl.tree.TreeNode
import org.specnaz.junit.impl.JUnitNotifier
import org.specnaz.junit.impl.JUnitUtils.testDescription

class SpecnazJUnitRunner(testsClass: Class<*>) : Runner() {
    private val specnazTestsRunner: SpecnazTestsRunner

    init {
        val newInstance: Any
        try {
            newInstance = testsClass.newInstance()
        } catch (e: Exception) {
            throw RuntimeException(
                    "The spec class ${testsClass.simpleName} must have a no-argument constructor", e)
        }

        val specnaz: Specnaz
        try {
            specnaz = newInstance as Specnaz
        } catch (e: ClassCastException) {
            throw RuntimeException(
                    "The spec class ${testsClass.simpleName} must implement the Specnaz interface", e)
        }

        specnazTestsRunner = SpecnazTestsRunner(specnaz)
    }

    private var rootDescription: Description? = null

    override fun getDescription(): Description {
        val testPlan = specnazTestsRunner.testPlan()

        val rootDescription = createSuiteDescription(specnazTestsRunner.name)

        parseSubGroupDescriptions(testPlan.plannedTests, rootDescription)

        this.rootDescription = rootDescription

        return rootDescription
    }

    override fun run(runNotifier: RunNotifier) {
        specnazTestsRunner.executeTests(JUnitNotifier(runNotifier, rootDescription!!))
    }

    private fun parseSubGroupDescriptions(node: TreeNode<PlannedTestGroup>,
                                          parentDescription: Description) {
        for (plannedTest in node.value.testsInThisGroup) {
            parentDescription.addChild(testDescription(plannedTest.description, parentDescription))
        }

        for (child in node.children) {
            if (child.value.testsInSubtree > 0) {
                val description = createSuiteDescription(child.value.groupDescription)
                parentDescription.addChild(description)
                parseSubGroupDescriptions(child, description)
            }
        }
    }
}
