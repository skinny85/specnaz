package org.specnaz.kotlin.params

import org.specnaz.kotlin.KotlinSpecBuilder
import org.specnaz.params.ParamsExpected1
import org.specnaz.params.ParamsExpected2
import org.specnaz.params.ParamsExpectedException1
import org.specnaz.params.ParamsExpectedException2
import org.specnaz.params.ParamsExpectedSubgroup1
import org.specnaz.params.ParamsSpecBuilder

class KotlinParamsSpecBuilder(val paramsSpecBuilder: ParamsSpecBuilder) : KotlinSpecBuilder(paramsSpecBuilder) {
    fun <P> should(description: String, testBody: (P) -> Unit): ParamsExpected1<P> {
        return paramsSpecBuilder.should(description, testBody)
    }

    inline fun <reified T : Throwable, P> shouldThrow(
            description: String, crossinline testBody: (P) -> Unit): ParamsExpectedException1<T, P> {
        return paramsSpecBuilder.shouldThrow(T::class.java, description, { p -> testBody.invoke(p) })
    }

    fun <P1, P2> should(description: String, testBody: (P1, P2) -> Unit): ParamsExpected2<P1, P2> {
        return paramsSpecBuilder.should(description, testBody)
    }

    inline fun <reified T : Throwable, P1, P2> shouldThrow(
            description: String, crossinline testBody: (P1, P2) -> Unit): ParamsExpectedException2<T, P1, P2> {
        return paramsSpecBuilder.shouldThrow(T::class.java, description, { p1, p2 -> testBody.invoke(p1, p2) })
    }

    fun <P> describes(description: String, specClosure: (P) -> Unit): ParamsExpectedSubgroup1<P> {
        return paramsSpecBuilder.describes(description, specClosure)
    }
}
