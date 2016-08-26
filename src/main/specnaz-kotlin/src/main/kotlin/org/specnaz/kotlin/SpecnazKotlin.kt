package org.specnaz.kotlin

import org.specnaz.SpecBuilder
import org.specnaz.Specnaz
import org.specnaz.kotlin.impl.KotlinSpecsRegistry

/**
 * The equivalent of the [Specnaz] interface for Kotlin code.
 * Defines pretty much an identical [describes] method,
 * differing only in the second argument being a function of
 * [KotlinSpecBuilder] instead of [SpecBuilder].
 *
 * The contract of the implementing class is exactly the same as with [Specnaz]:
 * the class must have a `public` no-argument constructor.
 * In that constructor, the [describes] method must be called exactly once.
 */
interface SpecnazKotlin {
    /**
     * The analog of the [Specnaz.describes] method for Kotlin.
     *
     * Must be called from the test classes' no-argument constructor exactly once
     * (calling it zero or more than one time will result in an error).
     *
     * It's a default method, and should never be overridden.
     * Also, do not rely on the implementation -
     * it is not part of the public contract of the method,
     * and can and will change between Specnaz versions.
     *
     * @param description
     *     the top-level description of the spec
     * @param specClosure
     *     a function, returning [Unit] and taking a [KotlinSpecBuilder] as the argument,
     *     that defines the specification
     */
    fun describes(description: String, specClosure: (KotlinSpecBuilder) -> Unit) {
        KotlinSpecsRegistry.register(this, description, specClosure)
    }
}
