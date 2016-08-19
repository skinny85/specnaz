package org.specnaz.junit.impl

import org.junit.runner.Description
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunNotifier
import org.specnaz.impl.Notifier
import org.specnaz.impl.TestCase

class JUnitNotifier(private val runNotifier: RunNotifier,
                    private val parentDescription: Description) :
        Notifier {
    override fun started(test: TestCase) {
        runNotifier.fireTestStarted(advanceToNextTestDescription(test))
    }

    override fun passed(test: TestCase) {
        runNotifier.fireTestFinished(currentTestDescription())
    }

    override fun failed(test: TestCase, error: Throwable) {
        val testDescription = currentTestDescription()
        runNotifier.fireTestFailure(Failure(testDescription, error))
        runNotifier.fireTestFinished(testDescription)
    }

    override fun ignored(test: TestCase) {
        val testDescription = advanceToNextTestDescription(test)
        runNotifier.fireTestIgnored(testDescription)
        runNotifier.fireTestFinished(testDescription)
    }

    private var testIndex = -1

    private fun advanceToNextTestDescription(test: TestCase): Description {
        testIndex++
        val children = parentDescription.children
        while (testIndex < children.size) {
            val description = children[testIndex]
            if (description.isTest)
                return description
            else
                testIndex++
        }
        throw IllegalStateException("Could not find Description for test '${test.description}'")
    }

    private fun currentTestDescription(): Description {
        return parentDescription.children[testIndex]
    }

    override fun subgroup(description: String): Notifier {
        return JUnitNotifier(runNotifier,
                advanceToNextSuiteDescription(description))
    }

    private var suiteIndex = -1

    private fun advanceToNextSuiteDescription(groupDescription: String): Description {
        suiteIndex++
        val children = parentDescription.children
        while (suiteIndex < children.size) {
            val description = children[suiteIndex]
            if (description.isSuite)
                return description
            else
                suiteIndex++
        }
        throw IllegalStateException("Could not find Description for group '$groupDescription'")
    }

    override fun setupFailed(e: Throwable) {
        // We do nothing here, because adding a Description not present in the original
        // test plan messes up JUnit's tree view. Instead, the Executor will mark the
        // tests as failed.
    }

    override fun teardownFailed(e: Throwable) {
        // In case of an 'afterAll' failure, we have no choice - the tests were already
        // marked as passed, so we cannot do anything (marking them as 'failed' after
        // does not work, JUnit just creates a new Description for it in the tree).
        // We accept that the tree will be broken in this case - hopefully, it's a rare situation.

        val description = JUnitUtils.testDescription("should not fail in an 'afterAll' method", parentDescription)
        parentDescription.addChild(description)
        runNotifier.fireTestStarted(description)
        runNotifier.fireTestFailure(Failure(description, e))
    }
}
