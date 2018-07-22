package org.specnaz.kotlin.params.testng

import org.specnaz.kotlin.params.KotlinParamsSpecBuilder
import org.testng.annotations.Test

@Test
abstract class SpecnazKotlinParamsTestNG(description: String,
        specClosure: (KotlinParamsSpecBuilder) -> Unit) : SpecnazKotlinParamsFactoryTestNG {
    init {
        describes(description, specClosure)
    }
}
