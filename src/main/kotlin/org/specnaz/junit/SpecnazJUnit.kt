package org.specnaz.junit

import org.junit.runner.RunWith
import org.specnaz.Specnaz

@RunWith(SpecnazJUnitRunner::class)
abstract class SpecnazJUnit(private val tests: () -> Unit) : Specnaz {
    override fun tests() = tests
}
