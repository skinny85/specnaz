package org.specnaz.impl

interface Notifier {
    fun started(test: TestCase)
    fun passed(test: TestCase)
    fun failed(test: TestCase, assertion: AssertionError)
    fun threw(test: TestCase, error: Throwable)
    fun ignored(test: TestCase)
}
