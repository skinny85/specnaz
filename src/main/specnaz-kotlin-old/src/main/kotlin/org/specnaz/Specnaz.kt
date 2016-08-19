package org.specnaz

interface Specnaz {
    fun tests(): (SpecnazSuiteBuilder) -> Unit
}
