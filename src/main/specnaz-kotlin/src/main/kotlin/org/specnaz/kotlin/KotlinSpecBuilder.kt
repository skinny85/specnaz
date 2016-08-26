package org.specnaz.kotlin

import org.specnaz.SpecBuilder

/**
 * The equivalent of [SpecBuilder] for Kotlin.
 *
 * The main difference is that all of the lifecycle callback methods
 * (`before`/`after`) and the test bodies take `Nothing?`
 * (the equivalent of [Void] in Java) as a first parameter,
 * which allows for a little better auto-completion experience
 * when using the default anonymous function parameter (`it`) in Kotlin.
 */
interface KotlinSpecBuilder {
    /**
     * The equivalent of [SpecBuilder.beginsAll] for Kotlin.
     */
    fun beginsAll(closure: (Nothing?) -> Unit)

    /**
     * The equivalent of [SpecBuilder.beginsEach] for Kotlin.
     */
    fun beginsEach(closure: (Nothing?) -> Unit)

    /**
     * The equivalent of [SpecBuilder.should] for Kotlin.
     */
    fun should(description: String, testBody: (Nothing?) -> Unit)

    /**
     * The equivalent of [SpecBuilder.endsEach] for Kotlin.
     */
    fun endsEach(closure: (Nothing?) -> Unit)

    /**
     * The equivalent of [SpecBuilder.endsAll] for Kotlin.
     */
    fun endsAll(closure: (Nothing?) -> Unit)

    /**
     * The equivalent of [SpecBuilder.describes] for Kotlin.
     */
    fun describes(description: String, specClosure: () -> Unit)
}
