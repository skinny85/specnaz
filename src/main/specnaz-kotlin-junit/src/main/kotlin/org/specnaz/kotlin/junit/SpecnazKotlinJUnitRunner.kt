package org.specnaz.kotlin.junit

import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runner.Runner
import org.junit.runner.notification.RunNotifier
import org.specnaz.junit.SpecnazJUnitRunner
import org.specnaz.junit.core.SpecnazCoreDslJUnitRunner
import org.specnaz.junit.utils.Utils
import org.specnaz.kotlin.SpecnazKotlin
import org.specnaz.kotlin.params.SpecnazKotlinParams

/**
 * The equivalent of [SpecnazJUnitRunner] for Kotlin -
 * the JUnit 4 [Runner] for running [SpecnazKotlin] specifications.
 *
 * The easiest way to use it is to extend [SpecnazKotlinJUnit].
 * If your test class has to extend from a particular class,
 * you can always specify it with JUnit's [RunWith] annotation:
 *
 * ```kotlin
 * @RunWith(SpecnazKotlinJUnitRunner::class)
 * class SomeSpec : SomeClass(), SpecnazKotlin { init {
 *     describes("my spec") {
 *         // spec body here...
 *     }
 * }
 * ```
 *
 * This Runner also handles parametrized tests implementing the [SpecnazKotlinParams] interface.
 */
class SpecnazKotlinJUnitRunner(classs: Class<*>) : Runner() {
    private val coreDslRunner: SpecnazCoreDslJUnitRunner

    init {
        val targetInterface = determineTargetInterface(classs)
        try {
            coreDslRunner = SpecnazCoreDslJUnitRunner(classs,
                    Utils.instantiateTestClass(classs, targetInterface))
        } catch (e: IllegalStateException) {
            throw IllegalStateException(
                    "${targetInterface.simpleName}.describes() was never called in the no-argument constructor of ${classs.simpleName}")
        }
    }

    override fun getDescription(): Description = coreDslRunner.description

    override fun run(runNotifier: RunNotifier) {
        coreDslRunner.run(runNotifier)
    }

    private fun determineTargetInterface(classs: Class<*>): Class<*> {
        return if (SpecnazKotlinParams::class.java.isAssignableFrom(classs))
            SpecnazKotlinParams::class.java
        else
            SpecnazKotlin::class.java
    }
}
