package org.specnaz.impl

import org.specnaz.SpecnazSuiteBuilder

class TestExecutionSuiteBuilder() : SpecnazSuiteBuilder {
    private var testsBodies: List<TestCase> = emptyList()
    private var befores: List<(Nothing?) -> Unit> = emptyList()
    private var afters: List<(Nothing?) -> Unit> = emptyList()

    val tests: List<SpecnazTest>
        get() = testsBodies.map { testCase ->
            testCase.toSpecnazTest(befores, afters)
        }

    override fun beforeEach(setup: (Nothing?) -> Unit) {
        befores += setup
    }

    override fun should(description: String, testBody: (Nothing?) -> Unit) {
        testsBodies += TestCase(description, testBody)
    }

    override fun afterEach(teardown: (Nothing?) -> Unit) {
        afters += teardown
    }

    private class TestCase(val description: String, val testBody: (Nothing?) -> Unit) {
        fun toSpecnazTest(befores: List<(Nothing?) -> Unit>, afters: List<(Nothing?) -> Unit>) =
                SpecnazTest(description, testBody, befores, afters)
    }
}
