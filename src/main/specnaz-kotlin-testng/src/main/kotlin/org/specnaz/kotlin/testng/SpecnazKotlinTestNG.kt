package org.specnaz.kotlin.testng

import org.specnaz.kotlin.KotlinSpecBuilder
import org.testng.annotations.Test

/**
 * Helper class for Specnaz specs in Kotlin using TestNG.
 *
 * This class is the TestNG equivalent of the
 * [org.specnaz.kotlin.junit.SpecnazKotlinJUnit] class.
 * It implements the [SpecnazKotlinFactoryTestNG] interface and calls the
 * [org.specnaz.kotlin.SpecnazKotlin.describes] in its constructor.
 *
 * It's useful if your test class does not need to extend any particular class.
 * In that case, you can extend this class and pass your description and
 * specification-building closure directly in the constructor,
 * thus saving some boilerplate (and two levels of indentation).
 *
 * Example:
 *
 * ```kotlin
 * import org.specnaz.kotlin.testng.SpecnazKotlinTestNG
 *
 * class MySpec : SpecnazKotlinTestNG("My specification", {
 *     // use 'it' here to construct the specification...
 * })
 * ```
 *
 * @see SpecnazKotlinFactoryTestNG
 * @see xSpecnazKotlinTestNG
 * @see org.specnaz.kotlin.SpecnazKotlin
 */
@Test
abstract class SpecnazKotlinTestNG(description: String,
        specClosure: (KotlinSpecBuilder) -> Unit) : SpecnazKotlinFactoryTestNG {
    init {
        describes(description, specClosure)
    }
}
