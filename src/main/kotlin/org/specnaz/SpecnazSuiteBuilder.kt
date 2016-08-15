package org.specnaz

interface SpecnazSuiteBuilder {
    fun beforeAll(setup: (Nothing?) -> Unit)
    fun beforeEach(setup: (Nothing?) -> Unit)
    fun should(description: String, testBody: (Nothing?) -> Unit)
    fun afterEach(teardown: (Nothing?) -> Unit)
}
