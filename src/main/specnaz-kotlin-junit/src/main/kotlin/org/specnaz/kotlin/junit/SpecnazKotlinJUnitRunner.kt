package org.specnaz.kotlin.junit

import org.specnaz.Specnaz
import org.specnaz.junit.SpecnazJUnitRunner
import org.specnaz.kotlin.SpecnazKotlin
import org.specnaz.kotlin.impl.KotlinSpecsRegistry

class SpecnazKotlinJUnitRunner(classs: Class<*>) :
        SpecnazJUnitRunner(classs.simpleName, toSpecInstance(classs)) {
    companion object {
        private fun toSpecInstance(classs: Class<*>): Specnaz {
            if (SpecnazKotlin::class.java.isAssignableFrom(classs)) {
                val kotlinClass = classs.asSubclass(SpecnazKotlin::class.java)
                val specnazKotlinInstance: SpecnazKotlin
                try {
                    specnazKotlinInstance = kotlinClass.newInstance()
                } catch (e: Exception) {
                    throw IllegalArgumentException(
                            "Could not instantiate spec class ${classs.simpleName}", e)
                }
                val wrapper = KotlinSpecsRegistry.specFor(specnazKotlinInstance)
                wrapper.callDescribes()
                return wrapper
            } else {
                throw IllegalArgumentException(
                        "A Kotlin spec class must implement the SpecnazKotlin interface; " +
                                "${classs.simpleName} does not")
            }
        }
    }
}
