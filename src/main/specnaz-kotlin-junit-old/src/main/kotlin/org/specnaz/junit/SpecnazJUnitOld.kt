package org.specnaz.junit

import org.junit.runner.RunWith
import org.specnaz.SpecnazOld
import org.specnaz.SpecnazSuiteBuilder

@RunWith(SpecnazJUnitRunnerOld::class)
abstract class SpecnazJUnitOld(private val tests: (SpecnazSuiteBuilder) -> Unit) : SpecnazOld {
    override fun tests() = tests
}
