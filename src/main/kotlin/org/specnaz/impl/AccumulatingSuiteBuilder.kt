package org.specnaz.impl

import org.specnaz.SpecnazSuiteBuilder

class AccumulatingSuiteBuilder() : SpecnazSuiteBuilder {
    var tests: List<SpecnazTest> = emptyList()

    override fun should(description: String, testBody: () -> Unit) {
        tests += SpecnazTest(description, testBody)
    }
}

class SpecnazTest(description: String, val testBody: () -> Unit) {
    val description: String

    init {
        this.description = "should " + description
    }
}
