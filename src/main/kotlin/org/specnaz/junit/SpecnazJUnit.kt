package org.specnaz.junit

import org.junit.runner.RunWith
import org.specnaz.Specnaz
import org.specnaz.SpecnazSuiteBuilder

@RunWith(SpecnazJUnitRunner::class)
abstract class SpecnazJUnit(private val tests: (SpecnazSuiteBuilder) -> Unit) : Specnaz {
    override fun tests() = tests
}
