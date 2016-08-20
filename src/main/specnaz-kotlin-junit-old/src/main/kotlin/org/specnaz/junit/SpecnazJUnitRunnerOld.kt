package org.specnaz.junit

import org.junit.runner.Description
import org.junit.runner.Description.createSuiteDescription
import org.junit.runner.Runner
import org.junit.runner.notification.RunNotifier
import org.specnaz.SpecnazOld
import org.specnaz.impl.SpecnazTestsRunner
import org.specnaz.impl.TestsGroupOld
import org.specnaz.impl.tree.TreeNode2
import org.specnaz.junit.impl.JUnitNotifierOld
import org.specnaz.junit.impl.JUnitUtils.testDescription

class SpecnazJUnitRunnerOld(testsClass: Class<*>) : Runner() {
    private val specnazTestsRunner: SpecnazTestsRunner

    init {
        val newInstance: Any
        try {
            newInstance = testsClass.newInstance()
        } catch (e: Exception) {
            throw RuntimeException(
                    "The spec class ${testsClass.simpleName} must have a no-argument constructor", e)
        }

        val specnaz: SpecnazOld
        try {
            specnaz = newInstance as SpecnazOld
        } catch (e: ClassCastException) {
            throw RuntimeException(
                    "The spec class ${testsClass.simpleName} must implement the SpecnazOld interface", e)
        }

        specnazTestsRunner = SpecnazTestsRunner(specnaz)
    }

    private var rootDescription: Description? = null

    override fun getDescription(): Description {
        val testPlan = specnazTestsRunner.testPlan()

        val rootDescription = createSuiteDescription(specnazTestsRunner.name)

        parseSubGroupDescriptions(testPlan.rootTestsGroup, rootDescription)

        this.rootDescription = rootDescription

        return rootDescription
    }

    override fun run(runNotifier: RunNotifier) {
        specnazTestsRunner.executeTests(JUnitNotifierOld(runNotifier, rootDescription!!))
    }

    private fun parseSubGroupDescriptions(node: TreeNode2<TestsGroupOld>,
                                          parentDescription: Description) {
        for (testCase in node.value.testCases) {
            parentDescription.addChild(testDescription(testCase.description, parentDescription))
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
