package org.specnaz.kotlin

interface KotlinSpecBuilder {
    fun beginsAll(closure: (Nothing?) -> Unit)

    fun beginsEach(closure: (Nothing?) -> Unit)

    fun should(description: String, testBody: (Nothing?) -> Unit)

    fun endsEach(closure: (Nothing?) -> Unit)

    fun endsAll(closure: (Nothing?) -> Unit)

    fun describes(description: String, specClosure: () -> Unit)
}
