package org.specnaz.kotlin.junit.platform

import org.junit.platform.commons.annotation.Testable
import org.specnaz.kotlin.KotlinSpecBuilder
import org.specnaz.kotlin.SpecnazKotlin

/**
 * A companion class to [SpecnazKotlinJUnitPlatform]
 * that allows you to easily ignore all tests in a given class -
 * simply add the letter 'x' in front of [SpecnazKotlinJUnitPlatform],
 * similarly like you can do for `should` and `describes` methods.
 */
@Testable
open class xSpecnazKotlinJUnitPlatform(description: String, specClosure: (KotlinSpecBuilder) -> Unit) :
        SpecnazKotlin {
    init {
        xdescribes(description, specClosure)
    }
}
