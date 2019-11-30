package org.specnaz.kotlin.params.junit.platform

import org.junit.platform.commons.annotation.Testable
import org.specnaz.kotlin.params.KotlinParamsSpecBuilder
import org.specnaz.kotlin.params.SpecnazKotlinParams

/**
 * A companion class to [SpecnazKotlinParamsJUnitPlatform]
 * that allows you to easily ignore all tests in a given class -
 * simply add the letter 'x' in front of [SpecnazKotlinParamsJUnitPlatform],
 * similarly like you can do for `should` and `describes` methods.
 */
@Testable
open class xSpecnazKotlinParamsJUnitPlatform(description: String, specClosure: (KotlinParamsSpecBuilder) -> Unit) :
        SpecnazKotlinParams {
    init {
        xdescribes(description, specClosure)
    }
}
