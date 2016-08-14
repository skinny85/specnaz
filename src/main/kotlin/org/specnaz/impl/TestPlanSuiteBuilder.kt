package org.specnaz.impl

import org.specnaz.SpecnazSuiteBuilder

class TestPlanSuiteBuilder() : SpecnazSuiteBuilder {
    var plannedTests: List<PlannedTest> = emptyList()

    override fun should(description: String, testBody: (Nothing?) -> Unit) {
        plannedTests += PlannedTest(description)
    }
}
