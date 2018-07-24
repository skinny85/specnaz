package org.specnaz.kotlin.testng

import org.specnaz.impl.SpecsRegistryViolation
import org.specnaz.kotlin.SpecnazKotlin
import org.specnaz.testng.SpecnazFactoryTestNG
import org.specnaz.testng.impl.SpecnazTestNgSpecFactory
import org.testng.annotations.Factory

/**
 * The Kotlin equivalent of [SpecnazFactoryTestNG].
 * You use it identically as [SpecnazFactoryTestNG]:
 * your class must implement this interface
 * (it has one, default, method)
 * and be annotated with [org.testng.annotations.Test].
 * Other than that, the specification looks like any other
 * [SpecnazKotlin] spec: the [SpecnazKotlin.describes]
 * method must be called in the public, no-argument
 * constructor exactly once, with the body of the specification.
 * Example:
 *
 * ```kotlin
 * import org.specnaz.kotlin.testng.SpecnazKotlinFactoryTestNG
 * import org.testng.annotations.Test
 *
 * @Test
 * class MySpec : SpecnazKotlinFactoryTestNG {
 *     init {
 *         describes("My specification") {
 *             // use 'it' here to construct the specification...
 *         }
 *     }
 * }
 * ```
 *
 * If your test class does not need to extend a particular class,
 * you can use the [SpecnazKotlinTestNG] helper class
 * to make the test setup more concise (and save two levels of indentation).
 *
 * @see SpecnazFactoryTestNG
 * @see SpecnazKotlin
 * @see SpecnazKotlinTestNG
 */
interface SpecnazKotlinFactoryTestNG : SpecnazKotlin {
    /**
     * The Kotlin equivalent of [SpecnazFactoryTestNG.specs].
     */
    @Factory
    fun specs(): Array<Any> {
        try {
            return SpecnazTestNgSpecFactory.specs(this)
        } catch (e: SpecsRegistryViolation) {
            throw IllegalStateException("SpecnazKotlin.describes() was never called in the " +
                    "no-argument constructor of " + this.javaClass.simpleName)
        }
    }
}
