package org.specnaz.kotlin.impl

import org.specnaz.Specnaz
import org.specnaz.kotlin.KotlinSpecBuilder

class SpecnazKotlinWrapper(private val description: String,
                           private val specClosure: (KotlinSpecBuilder) -> Unit) :
        Specnaz {
    fun callDescribes() {
        describes(description, { specBuilder ->
            specClosure.invoke(KotlinSpecBuilderWrapper(specBuilder))
        })
    }
}
