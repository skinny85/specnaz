package org.specnaz.kotlin.junit

import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runner.Runner
import org.junit.runner.notification.RunNotifier
import org.specnaz.junit.SpecnazJUnitRunner
import org.specnaz.junit.core.SpecnazCoreDslJUnitRunner
import org.specnaz.junit.utils.Utils
import org.specnaz.kotlin.SpecnazKotlin

/**
 * The equivalent of [SpecnazJUnitRunner] for Kotlin -
 * the JUnit [Runner] for running [SpecnazKotlin] specifications.
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
 */
class SpecnazKotlinJUnitRunner(classs: Class<*>) : Runner() {
    private val coreDslRunner: SpecnazCoreDslJUnitRunner

    init {
        try {
            coreDslRunner = SpecnazCoreDslJUnitRunner(classs,
                    Utils.instantiateTestClass(classs, SpecnazKotlin::class.java))
        } catch (e: IllegalStateException) {
            throw IllegalStateException(
                    "SpecnazKotlin.describes() was never called in the no-argument constructor of ${classs.simpleName}")
        }
    }

    override fun getDescription(): Description = coreDslRunner.description

    override fun run(runNotifier: RunNotifier) {
        coreDslRunner.run(runNotifier)
    }
}
