package org.specnaz.kotlin.junit

import org.junit.runner.RunWith
import org.specnaz.junit.SpecnazJUnit
import org.specnaz.kotlin.KotlinSpecBuilder
import org.specnaz.kotlin.SpecnazKotlin

/**
 * The equivalent of [SpecnazJUnit] for Kotlin.
 *
 * Implements [SpecnazKotlin] and declares [SpecnazKotlinJUnitRunner] as the JUnit
 * runner with the [RunWith] annotation.
 *
 * Useful when your test class does not need to extend any particular class.
 * Because it calls [SpecnazKotlin.describes] in its primary constructor,
 * it allows you to save some code and one level of indentation
 * compared to implementing [SpecnazKotlin] directly:
 *
 * ```
 * @RunWith(SpecnazKotlinJUnitRunner::class)
 * class SomeSpec : SpecnazKotlin { init {
 *     describes("my spec") {
 *         // spec body here...
 *     }
 * }
 * ```
 *
 * versus:
 *
 * ```
 * class SomeSpec : SpecnazKotlinJunit("my spec", {
 *     // spec body here...
 * })
 * ```
 */
@RunWith(SpecnazKotlinJUnitRunner::class)
abstract class SpecnazKotlinJUnit(description: String,
                                  specClosure: (KotlinSpecBuilder) -> Unit) :
        SpecnazKotlin {
    init {
        describes(description, specClosure)
    }
}
