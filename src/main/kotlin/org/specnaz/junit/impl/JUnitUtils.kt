package org.specnaz.junit.impl

import org.junit.runner.Description
import org.specnaz.impl.PlannedTest

object JUnitUtils {
    fun testDescription(test: PlannedTest, testsClass: Class<*>) =
            Description.createTestDescription(testsClass, test.description)
}
