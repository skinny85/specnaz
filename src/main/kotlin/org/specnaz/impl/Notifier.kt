package org.specnaz.impl

interface Notifier {
    fun started(test: SpecnazTest)
    fun passed(test: SpecnazTest)
    fun failed(test: SpecnazTest, assertion: AssertionError)
    fun threw(test: SpecnazTest, error: Throwable)
    fun ignored(test: SpecnazTest)
}
