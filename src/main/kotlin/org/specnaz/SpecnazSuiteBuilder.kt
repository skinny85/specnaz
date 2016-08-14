package org.specnaz

interface SpecnazSuiteBuilder {
    fun should(description: String, testBody: () -> Unit)
}
