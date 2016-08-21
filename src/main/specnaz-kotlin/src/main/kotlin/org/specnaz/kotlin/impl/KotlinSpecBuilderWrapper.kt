package org.specnaz.kotlin.impl

import org.specnaz.SpecBuilder
import org.specnaz.kotlin.KotlinSpecBuilder

class KotlinSpecBuilderWrapper(private val specBuilder: SpecBuilder) : KotlinSpecBuilder {
    override fun beginsAll(closure: (Nothing?) -> Unit) {
        specBuilder.beginsAll { closure.invoke(null) }
    }

    override fun beginsEach(closure: (Nothing?) -> Unit) {
        specBuilder.beginsEach { closure.invoke(null) }
    }

    override fun should(description: String, testBody: (Nothing?) -> Unit) {
        specBuilder.should(description, { testBody.invoke(null) })
    }

    override fun endsEach(closure: (Nothing?) -> Unit) {
        specBuilder.endsEach { closure.invoke(null) }
    }

    override fun endsAll(closure: (Nothing?) -> Unit) {
        specBuilder.endsAll { closure.invoke(null) }
    }

    override fun describes(description: String, specClosure: () -> Unit) {
        specBuilder.describes(description, { specClosure.invoke() })
    }
}
