package org.specnaz.kotlin.params.testng

import org.specnaz.impl.SpecsRegistryViolation
import org.specnaz.kotlin.params.SpecnazKotlinParams
import org.specnaz.testng.impl.SpecnazTestNgSpecFactory
import org.testng.annotations.Factory

/**
 * The parametrized equivalent of [org.specnaz.kotlin.testng.SpecnazKotlinFactoryTestNG].
 * You use it exactly like [org.specnaz.kotlin.testng.SpecnazKotlinFactoryTestNG].
 *
 * @see org.specnaz.kotlin.testng.SpecnazKotlinFactoryTestNG
 * @see org.specnaz.kotlin.params.SpecnazKotlinParams
 * @see SpecnazKotlinParamsTestNG
 */
interface SpecnazKotlinParamsFactoryTestNG : SpecnazKotlinParams {
    /**
     * The parametrized equivalent of [org.specnaz.kotlin.testng.SpecnazKotlinTestNG.specs].
     */
    @Factory
    fun specs(): Array<Any> {
        try {
            return SpecnazTestNgSpecFactory.specs(this)
        } catch (e: SpecsRegistryViolation) {
            throw IllegalStateException("SpecnazKotlinParams.describes() was never called in the " +
                    "no-argument constructor of " + this.javaClass.simpleName)
        }
    }
}
