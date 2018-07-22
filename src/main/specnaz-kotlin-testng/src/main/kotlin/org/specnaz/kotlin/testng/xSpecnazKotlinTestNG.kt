package org.specnaz.kotlin.testng

import org.specnaz.kotlin.KotlinSpecBuilder
import org.testng.annotations.Test

@Test
abstract class xSpecnazKotlinTestNG(description: String,
        specClosure: (KotlinSpecBuilder) -> Unit) : SpecnazKotlinFactoryTestNG {
    init {
        xdescribes(description, specClosure)
    }
}
