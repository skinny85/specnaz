package org.specnaz.kotlin.junit.platform

import org.specnaz.kotlin.SpecnazKotlin
import org.specnaz.kotlin.params.SpecnazKotlinParams
import java.util.function.Predicate

object IsSpecnazKotlinClassPredicate : Predicate<Class<*>> {
    override fun test(classs: Class<*>) = SpecnazKotlin::class.java.isAssignableFrom(classs) ||
            SpecnazKotlinParams::class.java.isAssignableFrom(classs)
}
