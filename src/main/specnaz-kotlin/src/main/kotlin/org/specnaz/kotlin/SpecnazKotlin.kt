package org.specnaz.kotlin

import org.specnaz.kotlin.impl.KotlinSpecsRegistry

interface SpecnazKotlin {
    fun describes(description: String, specClosure: (KotlinSpecBuilder) -> Unit) {
        KotlinSpecsRegistry.register(this, description, specClosure)
    }
}
