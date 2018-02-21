package org.specnaz.kotlin.params.junit

import org.junit.runner.RunWith
import org.specnaz.kotlin.junit.SpecnazKotlinJUnitRunner
import org.specnaz.kotlin.params.KotlinParamsSpecBuilder
import org.specnaz.kotlin.params.SpecnazKotlinParams

/**
 * The equivalent of [org.specnaz.params.junit.SpecnazParamsJUnit] for Kotlin,
 * and the parametrized version of [org.specnaz.kotlin.junit.SpecnazKotlinJUnit].
 */
@RunWith(SpecnazKotlinJUnitRunner::class)
abstract class SpecnazKotlinParamsJUnit(description: String,
                                        specClosure: (KotlinParamsSpecBuilder) -> Unit) :
        SpecnazKotlinParams {
    init {
        this.describes(description, specClosure)
    }
}
