package org.specnaz.kotlin.testng

import org.specnaz.impl.SpecsRegistryViolation
import org.specnaz.kotlin.SpecnazKotlin
import org.specnaz.testng.impl.SpecnazTestNgSpecFactory
import org.testng.annotations.Factory

interface SpecnazKotlinFactoryTestNG : SpecnazKotlin {
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
