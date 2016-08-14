package org.specnaz.impl

open class PlannedTest(description: String) {
    val description: String

    init {
        this.description = "should " + description
    }
}

class SpecnazTest(description: String,
                  val testBody: (Nothing?) -> Unit,
                  val befores: List<(Nothing?) -> Unit>) :
        PlannedTest(description)
