package org.specnaz.impl

interface Notifier {
    fun setupFailed(e: Throwable)
    fun teardownFailed(e: Throwable)

    fun started(test: TestCase)
    fun passed(test: TestCase)
    fun failed(test: TestCase, assertion: AssertionError)
    fun threw(test: TestCase, error: Throwable)
    fun ignored(test: TestCase)

    fun subgroup(description: String): Notifier
}
