package org.specnaz.kotlin.testng

import org.specnaz.kotlin.KotlinSpecBuilder
import org.testng.annotations.Test

@Test
abstract class SpecnazKotlinTestNG(description: String,
        specClosure: (KotlinSpecBuilder) -> Unit) : SpecnazKotlinFactoryTestNG {
    init {
        describes(description, specClosure)
    }
}
