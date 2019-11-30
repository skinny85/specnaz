package org.specnaz.kotlin.params.junit.platform

import org.junit.platform.commons.annotation.Testable
import org.specnaz.kotlin.params.KotlinParamsSpecBuilder
import org.specnaz.kotlin.params.SpecnazKotlinParams

/**
 * A helper class that implements the [SpecnazKotlinParams] interface
 * and is annotated with [Testable].
 * Calls the [SpecnazKotlinParams.describes] method in its primary constructor.
 * Convenient when your test class doesn't need to extend any particular class -
 * allows you to save one level of indentation when inheriting from it.
 */
@Testable
open class SpecnazKotlinParamsJUnitPlatform(description: String, specClosure: (KotlinParamsSpecBuilder) -> Unit) :
        SpecnazKotlinParams {
    init {
        describes(description, specClosure)
    }
}
