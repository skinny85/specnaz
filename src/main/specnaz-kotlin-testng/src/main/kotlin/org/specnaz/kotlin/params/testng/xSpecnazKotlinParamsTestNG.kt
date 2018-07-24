package org.specnaz.kotlin.params.testng

import org.specnaz.kotlin.params.KotlinParamsSpecBuilder
import org.testng.annotations.Test

/**
 * The parametrized equivalent of [org.specnaz.kotlin.testng.xSpecnazKotlinTestNG].
 *
 * @see SpecnazKotlinParamsTestNG
 * @see org.specnaz.kotlin.params.SpecnazKotlinParams.xdescribes
 */
@Test
abstract class xSpecnazKotlinParamsTestNG(description: String,
        specClosure: (KotlinParamsSpecBuilder) -> Unit) : SpecnazKotlinParamsFactoryTestNG {
    init {
        xdescribes(description, specClosure)
    }
}
