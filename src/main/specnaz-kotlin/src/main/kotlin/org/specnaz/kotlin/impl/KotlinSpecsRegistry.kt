package org.specnaz.kotlin.impl

import org.specnaz.kotlin.KotlinSpecBuilder
import org.specnaz.kotlin.SpecnazKotlin
import java.util.IdentityHashMap

object KotlinSpecsRegistry {
    private val registry = IdentityHashMap<SpecnazKotlin, SpecnazKotlinWrapper>()

    fun register(specInstance: SpecnazKotlin, description: String, specClosure: (KotlinSpecBuilder) -> Unit) {
        if (registry.containsKey(specInstance))
            throw IllegalStateException("SpecnazKotlin.describes() called multiple times in class " +
                    "${specInstance.javaClass.simpleName}")
        registry.put(specInstance, SpecnazKotlinWrapper(description, specClosure))
    }

    fun specFor(specInstance: SpecnazKotlin) = registry.get(specInstance)
            ?: throw IllegalStateException(
                "SpecnazKotlin.describes() was never called in class " +
                    "${specInstance.javaClass.simpleName}")
}

