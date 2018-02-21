package org.specnaz.kotlin.params

import org.specnaz.impl.SpecsRegistry
import org.specnaz.impl.SpecsRegistryViolation
import org.specnaz.params.impl.ParamsSpecBuilderCoreDslAdapter

/**
 * The equivalent of the [org.specnaz.params.SpecnazParams] interface for Kotlin,
 * and the parametrized version of [org.specnaz.kotlin.SpecnazKotlin].
 * Can be implemented directly, or through a framework helper
 * like `SpecnazKotlinParamsJUnit`.
 */
interface SpecnazKotlinParams {
    /**
     * The analog of the [org.specnaz.params.SpecnazParams.describes] method for Kotlin.
     *
     * @param description
     *     the top-level description of the spec
     * @param specClosure
     *     a function, returning [Unit] and taking a [KotlinParamsSpecBuilder] as the argument,
     *     that defines the specification
     */
    fun describes(description: String, specClosure: (KotlinParamsSpecBuilder) -> Unit) {
        try {
            SpecsRegistry.register(this, description, false, {
                coreDslBuilder -> specClosure(KotlinParamsSpecBuilder(
                    ParamsSpecBuilderCoreDslAdapter(coreDslBuilder)))
            })
        } catch (e: SpecsRegistryViolation) {
            throw IllegalStateException("SpecnazKotlinParams.describes() was called multiple times in the " +
                "no-argument constructor of ${this.javaClass.simpleName}")
        }
    }

    /**
     * The analog of the [org.specnaz.params.SpecnazParams.xdescribes] method for Kotlin.
     *
     * Allows you to ignore the entire parametrized spec defined in this class.
     *
     * @param description
     *     the top-level description of the spec
     * @param specClosure
     *     a function, returning [Unit] and taking a [KotlinParamsSpecBuilder] as the argument,
     *     that defines the specification
     */
    fun xdescribes(description: String, specClosure: (KotlinParamsSpecBuilder) -> Unit) {
        try {
            SpecsRegistry.register(this, description, true, {
                coreDslBuilder -> specClosure(KotlinParamsSpecBuilder(
                    ParamsSpecBuilderCoreDslAdapter(coreDslBuilder)))
            })
        } catch (e: SpecsRegistryViolation) {
            throw IllegalStateException("SpecnazKotlinParams.describes() was called multiple times in the " +
                    "no-argument constructor of ${this.javaClass.simpleName}")
        }
    }
}
