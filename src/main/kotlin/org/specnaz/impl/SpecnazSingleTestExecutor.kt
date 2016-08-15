package org.specnaz.impl

class SpecnazSingleTestExecutor(private val test: SpecnazTest,
                                private val notifier: Notifier) {
    private var passing = false

    fun run() {
        passing = true

        notifier.started(test)

        invokeFixtures(test.befores)

        invokeTestBody()

        invokeFixtures(test.afters)

        if (passing) {
            notifier.passed(test)
        }
    }

    private fun invokeFixtures(fixuters: List<(Nothing?) -> Unit>) {
        fixuters.forEach { invokeCallback(it) }
    }

    private fun invokeTestBody() {
        // we skip the test if any of the 'before' fixtures failed
        if (passing)
            invokeCallback(test.testBody)
    }

    private fun invokeCallback(callback: (Nothing?) -> Unit) {
        try {
            callback.invoke(null)
        } catch (e: AssertionError) {
            failIfNotFailedAlready(e)
        } catch (e: Exception) {
            faiIfNotFailedAlready(e)
        }
    }

    private fun failIfNotFailedAlready(e: AssertionError) {
        if (passing) {
            passing = false
            notifier.failed(test, e)
        }
    }

    private fun faiIfNotFailedAlready(e: Exception) {
        if (passing) {
            passing = false
            notifier.threw(test, e)
        }
    }
}
