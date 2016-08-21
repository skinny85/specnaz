package org.specnaz.kotlin.junit

import org.junit.runner.RunWith
import org.specnaz.kotlin.KotlinSpecBuilder
import org.specnaz.kotlin.SpecnazKotlin

@RunWith(SpecnazKotlinJUnitRunner::class)
abstract class SpecnazKotlinJUnit(description: String,
                                  specClosure: (KotlinSpecBuilder) -> Unit) :
        SpecnazKotlin {
    init {
        describes(description, specClosure)
    }
}
