package org.specnaz.kotlin.junit.platform;

import org.specnaz.junit.platform.CommonSpecnazJUnitPlatformTestEngine
import java.util.function.Predicate

class SpecnazKotlinJUnitPlatformTestEngine : CommonSpecnazJUnitPlatformTestEngine() {
    override fun getId() = "specnaz-kotlin"

    override fun classPredicate(): Predicate<Class<*>> = IsSpecnazKotlinClassPredicate
}
