package org.specnaz.kotlin.params.junit

import org.junit.runner.RunWith
import org.specnaz.kotlin.junit.SpecnazKotlinJUnitRunner
import org.specnaz.kotlin.params.KotlinParamsSpecBuilder
import org.specnaz.kotlin.params.SpecnazKotlinParams

/**
 * The parametrized equivalent of [org.specnaz.kotlin.junit.xSpecnazKotlinJUnit].
 * Allows you to ignore an entire parametrized spec extending from
 * [SpecnazKotlinParamsJUnit].
 */
@RunWith(SpecnazKotlinJUnitRunner::class)
abstract class xSpecnazKotlinParamsJUnit(description: String,
                                         specClosure: (KotlinParamsSpecBuilder) -> Unit) :
        SpecnazKotlinParams {
    init {
        this.xdescribes(description, specClosure)
    }
}
