package org.specnaz.junit.impl

import org.junit.runner.Description
import org.specnaz.impl.PlannedTest

object JUnitUtils {
    fun testDescription(name: String, parentDescription: Description) =
            Description.createTestDescription(parentDescription.displayName, name)
}
