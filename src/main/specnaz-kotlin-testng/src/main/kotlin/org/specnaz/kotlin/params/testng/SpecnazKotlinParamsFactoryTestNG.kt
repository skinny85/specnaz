package org.specnaz.kotlin.params.testng

import org.specnaz.impl.SpecsRegistryViolation
import org.specnaz.kotlin.params.SpecnazKotlinParams
import org.specnaz.testng.impl.SpecnazTestNgSpecFactory
import org.testng.annotations.Factory

interface SpecnazKotlinParamsFactoryTestNG : SpecnazKotlinParams {
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
