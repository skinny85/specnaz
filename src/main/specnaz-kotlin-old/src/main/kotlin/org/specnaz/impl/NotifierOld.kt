package org.specnaz.impl

interface NotifierOld {
    fun setupFailed(e: Throwable)
    fun teardownFailed(e: Throwable)

    fun started(test: TestCase)
    fun passed(test: TestCase)
    fun failed(test: TestCase, error: Throwable)
    fun ignored(test: TestCase)

    fun subgroup(description: String): NotifierOld
}
