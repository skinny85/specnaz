package org.specnaz.kotlin.params.testng

import org.specnaz.kotlin.params.KotlinParamsSpecBuilder
import org.testng.annotations.Test

/**
 * The parametrized equivalent of [org.specnaz.kotlin.testng.SpecnazKotlinTestNG].
 * You use it exactly like [org.specnaz.kotlin.testng.SpecnazKotlinTestNG].
 *
 * @see org.specnaz.kotlin.testng.SpecnazKotlinTestNG
 * @see SpecnazKotlinParamsFactoryTestNG
 * @see org.specnaz.kotlin.params.SpecnazKotlinParams
 * @see xSpecnazKotlinParamsTestNG
 */
@Test
abstract class SpecnazKotlinParamsTestNG(description: String,
        specClosure: (KotlinParamsSpecBuilder) -> Unit) : SpecnazKotlinParamsFactoryTestNG {
    init {
        describes(description, specClosure)
    }
}
