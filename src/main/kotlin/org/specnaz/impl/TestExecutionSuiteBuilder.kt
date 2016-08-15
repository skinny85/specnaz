package org.specnaz.impl

import org.specnaz.SpecnazSuiteBuilder

class TestExecutionSuiteBuilder() : SpecnazSuiteBuilder {
    private var beforeAlls: List<(Nothing?) -> Unit> = emptyList()
    private var befores: List<(Nothing?) -> Unit> = emptyList()
    private var testCases: List<TestCase> = emptyList()
    private var afters: List<(Nothing?) -> Unit> = emptyList()

    val tests: TestsGroup
        get() = TestsGroup(
                beforeAlls,
                befores,
                testCases,
                afters)

    override fun beforeAll(setup: (Nothing?) -> Unit) {
        beforeAlls += setup
    }

    override fun beforeEach(setup: (Nothing?) -> Unit) {
        befores += setup
    }

    override fun should(description: String, testBody: (Nothing?) -> Unit) {
        testCases += TestCase(description, testBody)
    }

    override fun afterEach(teardown: (Nothing?) -> Unit) {
        afters += teardown
    }
}
