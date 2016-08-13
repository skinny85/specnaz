package org.specnaz

import org.junit.runner.RunWith
import org.specnaz.junit.SpecnazJUnitRunner

@RunWith(SpecnazJUnitRunner::class)
abstract class Specnaz {
    abstract fun tests(): () -> Unit
}

