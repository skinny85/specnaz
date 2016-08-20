package org.specnaz.junit

import org.junit.runner.RunWith
import org.specnaz.SpecnazOld
import org.specnaz.SpecnazSuiteBuilder

@RunWith(SpecnazJUnitRunner::class)
abstract class SpecnazJUnit(private val tests: (SpecnazSuiteBuilder) -> Unit) : SpecnazOld {
    override fun tests() = tests
}
