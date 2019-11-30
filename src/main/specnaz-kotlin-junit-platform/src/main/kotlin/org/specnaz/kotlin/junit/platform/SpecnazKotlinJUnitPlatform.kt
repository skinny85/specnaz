package org.specnaz.kotlin.junit.platform

import org.junit.platform.commons.annotation.Testable
import org.specnaz.kotlin.KotlinSpecBuilder
import org.specnaz.kotlin.SpecnazKotlin

/**
 * A helper class that implements the [SpecnazKotlin] interface
 * and is annotated with [Testable].
 * Calls the [SpecnazKotlin.describes] method in its primary constructor.
 * Convenient when your test class doesn't need to extend any particular class -
 * allows you to save one level of indentation when inheriting from it.
 */
@Testable
open class SpecnazKotlinJUnitPlatform(description: String, specClosure: (KotlinSpecBuilder) -> Unit) :
        SpecnazKotlin {
    init {
        describes(description, specClosure)
    }
}
