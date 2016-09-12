package org.specnaz.kotlin.junit

import org.junit.runner.RunWith
import org.junit.runner.Runner
import org.specnaz.junit.SpecnazJUnitRunner
import org.specnaz.kotlin.SpecnazKotlin

/**
 * The equivalent of [SpecnazJUnitRunner] for Kotlin -
 * the JUnit [Runner] for running [SpecnazKotlin] specifications.
 *
 * The easiest way to use it is to extend [SpecnazKotlinJUnit].
 * If your test class has to extend from a particular class,
 * you can always specify it with JUnit's [RunWith] annotation:
 *
 * ```
 * @RunWith(SpecnazKotlinJUnitRunner::class)
 * class SomeSpec : SomeClass(), SpecnazKotlin { init {
 *     describes("my spec") {
 *         // spec body here...
 *     }
 * }
 * ```
 */
class SpecnazKotlinJUnitRunner(classs: Class<*>) :
        SpecnazJUnitRunner(classs.simpleName, toSpecInstance(classs)) {
    companion object {
        private fun toSpecInstance(classs: Class<*>): SpecnazKotlin {
            if (SpecnazKotlin::class.java.isAssignableFrom(classs)) {
                val kotlinClass = classs.asSubclass(SpecnazKotlin::class.java)
                val specnazKotlinInstance: SpecnazKotlin
                try {
                    specnazKotlinInstance = kotlinClass.newInstance()
                } catch (e: Exception) {
                    throw IllegalArgumentException(
                            "Could not instantiate spec class ${classs.simpleName}", e)
                }
                return specnazKotlinInstance
            } else {
                throw IllegalArgumentException(
                        "A Kotlin spec class must implement the SpecnazKotlin interface; " +
                                "${classs.simpleName} does not")
            }
        }
    }
}
