package org.specnaz.impl

import org.specnaz.SpecnazSuiteBuilder

class TestExecutionSuiteBuilder() : SpecnazSuiteBuilder {
    var tests: List<SpecnazTest> = emptyList()

    override fun should(description: String, testBody: (Nothing?) -> Unit) {
        tests += SpecnazTest(description, testBody)
    }
}
