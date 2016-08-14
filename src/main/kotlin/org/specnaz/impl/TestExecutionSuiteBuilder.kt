package org.specnaz.impl

import org.specnaz.SpecnazSuiteBuilder

class TestExecutionSuiteBuilder() : SpecnazSuiteBuilder {
    private var testsBodies: List<TestCase> = emptyList()
    private var befores: List<(Nothing?) -> Unit> = emptyList()

    val tests: List<SpecnazTest>
        get() = testsBodies.map { testCase ->
            testCase.toSpecnazTest(befores)
        }

    override fun beforeEach(setup: (Nothing?) -> Unit) {
        befores += setup
    }

    override fun should(description: String, testBody: (Nothing?) -> Unit) {
        testsBodies += TestCase(description, testBody)
    }

    private class TestCase(val description: String, val testBody: (Nothing?) -> Unit) {
        fun toSpecnazTest(befores: List<(Nothing?) -> Unit>) =
                SpecnazTest(description, testBody, befores)
    }
}
