package org.specnaz.kotlin.params.junit

import org.junit.runner.Description
import org.junit.runner.Runner
import org.junit.runner.notification.RunNotifier
import org.specnaz.junit.core.SpecnazCoreDslJUnitRunner
import org.specnaz.junit.utils.Utils
import org.specnaz.kotlin.params.SpecnazKotlinParams

class SpecnazKotlinParamsJUnitRunner(classs: Class<*>) : Runner() {
    private val coreDslRunner: SpecnazCoreDslJUnitRunner

    init {
        try {
            coreDslRunner = SpecnazCoreDslJUnitRunner(classs,
                    Utils.instantiateTestClass(classs, SpecnazKotlinParams::class.java))
        } catch (e: IllegalStateException) {
            throw IllegalStateException(
                    "SpecnazKotlinParams.describes() was never called in the no-argument constructor of ${classs.simpleName}")
        }
    }

    override fun getDescription(): Description = coreDslRunner.description

    override fun run(runNotifier: RunNotifier) {
        coreDslRunner.run(runNotifier)
    }
}
