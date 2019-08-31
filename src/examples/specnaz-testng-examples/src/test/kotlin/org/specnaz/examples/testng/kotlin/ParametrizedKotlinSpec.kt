package org.specnaz.examples.testng.kotlin

import org.specnaz.kotlin.params.testng.SpecnazKotlinParamsFactoryTestNG
import org.testng.annotations.Test

/**
 * Example of a parametrized Kotlin spec,
 * running using TestNG as the execution engine.
 */
@Test
class ParametrizedKotlinSpec : SpecnazKotlinParamsFactoryTestNG { init {
    describes("A parametrized Kotlin spec") {
        it.shouldThrow<NumberFormatException, String>("when parsing '%1' as an Int") { str ->
            Integer.parseInt(str)
        }.provided("cafe", "BABE").withoutCause()
    }
}}
