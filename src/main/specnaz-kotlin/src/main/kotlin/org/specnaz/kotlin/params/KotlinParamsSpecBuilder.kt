package org.specnaz.kotlin.params

import org.specnaz.kotlin.KotlinSpecBuilder
import org.specnaz.params.ParamsExpected1
import org.specnaz.params.ParamsExpected2
import org.specnaz.params.ParamsExpected3
import org.specnaz.params.ParamsExpected4
import org.specnaz.params.ParamsExpected5
import org.specnaz.params.ParamsExpected6
import org.specnaz.params.ParamsExpectedException1
import org.specnaz.params.ParamsExpectedException2
import org.specnaz.params.ParamsExpectedException3
import org.specnaz.params.ParamsExpectedException4
import org.specnaz.params.ParamsExpectedException5
import org.specnaz.params.ParamsExpectedException6
import org.specnaz.params.ParamsExpectedSubgroup1
import org.specnaz.params.ParamsExpectedSubgroup2
import org.specnaz.params.ParamsExpectedSubgroup3
import org.specnaz.params.ParamsExpectedSubgroup4
import org.specnaz.params.ParamsExpectedSubgroup5
import org.specnaz.params.ParamsExpectedSubgroup6
import org.specnaz.params.ParamsSpecBuilder

/**
 * The equivalent of [ParamsSpecBuilder] for Kotlin,
 * and the parametrized extension of [KotlinSpecBuilder].
 * An instance of this class is passed to the lambda expression
 * given in [SpecnazKotlinParams.describes].
 */
class KotlinParamsSpecBuilder(val paramsSpecBuilder: ParamsSpecBuilder) : KotlinSpecBuilder(paramsSpecBuilder) {
    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.should],
     * taking a single parameter, for Kotlin.
     */
    fun <P> should(description: String, testBody: (P) -> Unit): ParamsExpected1<P> {
        return paramsSpecBuilder.should(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fshould],
     * taking a single parameter, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    fun <P> fshould(description: String, testBody: (P) -> Unit): ParamsExpected1<P> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fshould(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xshould],
     * taking a single parameter, for Kotlin.
     */
    fun <P> xshould(description: String, testBody: (P) -> Unit): ParamsExpected1<P> {
        return paramsSpecBuilder.xshould(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.shouldThrow],
     * taking a single parameter, for Kotlin.
     */
    inline fun <reified T : Throwable, P> shouldThrow(
            description: String, crossinline testBody: (P) -> Unit): ParamsExpectedException1<T, P> {
        return paramsSpecBuilder.shouldThrow(T::class.java, description, { p -> testBody.invoke(p) })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fshouldThrow],
     * taking a single parameter, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    inline fun <reified T : Throwable, P> fshouldThrow(
            description: String, crossinline testBody: (P) -> Unit): ParamsExpectedException1<T, P> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fshouldThrow(T::class.java, description, { p -> testBody.invoke(p) })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xshouldThrow],
     * taking a single parameter, for Kotlin.
     */
    inline fun <reified T : Throwable, P> xshouldThrow(
            description: String, crossinline testBody: (P) -> Unit): ParamsExpectedException1<T, P> {
        return paramsSpecBuilder.xshouldThrow(T::class.java, description, { p -> testBody.invoke(p) })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.should],
     * taking 2 parameters, for Kotlin.
     */
    fun <P1, P2> should(description: String, testBody: (P1, P2) -> Unit): ParamsExpected2<P1, P2> {
        return paramsSpecBuilder.should(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fshould],
     * taking 2 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    fun <P1, P2> fshould(description: String, testBody: (P1, P2) -> Unit): ParamsExpected2<P1, P2> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fshould(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xshould],
     * taking 2 parameters, for Kotlin.
     */
    fun <P1, P2> xshould(description: String, testBody: (P1, P2) -> Unit): ParamsExpected2<P1, P2> {
        return paramsSpecBuilder.xshould(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.shouldThrow],
     * taking 2 parameters, for Kotlin.
     */
    inline fun <reified T : Throwable, P1, P2> shouldThrow(
            description: String, crossinline testBody: (P1, P2) -> Unit): ParamsExpectedException2<T, P1, P2> {
        return paramsSpecBuilder.shouldThrow(T::class.java, description, { p1, p2 -> testBody.invoke(p1, p2) })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fshouldThrow],
     * taking 2 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    inline fun <reified T : Throwable, P1, P2> fshouldThrow(
            description: String, crossinline testBody: (P1, P2) -> Unit): ParamsExpectedException2<T, P1, P2> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fshouldThrow(T::class.java, description, { p1, p2 -> testBody.invoke(p1, p2) })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xshouldThrow],
     * taking 2 parameters, for Kotlin.
     */
    inline fun <reified T : Throwable, P1, P2> xshouldThrow(
            description: String, crossinline testBody: (P1, P2) -> Unit): ParamsExpectedException2<T, P1, P2> {
        return paramsSpecBuilder.xshouldThrow(T::class.java, description, { p1, p2 -> testBody.invoke(p1, p2) })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.should],
     * taking 3 parameters, for Kotlin.
     */
    fun <P1, P2, P3> should(description: String, testBody: (P1, P2, P3) -> Unit): ParamsExpected3<P1, P2, P3> {
        return paramsSpecBuilder.should(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fshould],
     * taking 3 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    fun <P1, P2, P3> fshould(description: String, testBody: (P1, P2, P3) -> Unit): ParamsExpected3<P1, P2, P3> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fshould(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xshould],
     * taking 3 parameters, for Kotlin.
     */
    fun <P1, P2, P3> xshould(description: String, testBody: (P1, P2, P3) -> Unit): ParamsExpected3<P1, P2, P3> {
        return paramsSpecBuilder.xshould(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.shouldThrow],
     * taking 3 parameters, for Kotlin.
     */
    inline fun <reified T : Throwable, P1, P2, P3> shouldThrow(
            description: String, crossinline testBody: (P1, P2, P3) -> Unit): ParamsExpectedException3<T, P1, P2, P3> {
        return paramsSpecBuilder.shouldThrow(T::class.java, description, { p1, p2, p3 -> testBody.invoke(p1, p2, p3) })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fshouldThrow],
     * taking 3 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    inline fun <reified T : Throwable, P1, P2, P3> fshouldThrow(
            description: String, crossinline testBody: (P1, P2, P3) -> Unit): ParamsExpectedException3<T, P1, P2, P3> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fshouldThrow(T::class.java, description, { p1, p2, p3 -> testBody.invoke(p1, p2, p3) })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xshouldThrow],
     * taking 3 parameters, for Kotlin.
     */
    inline fun <reified T : Throwable, P1, P2, P3> xshouldThrow(
            description: String, crossinline testBody: (P1, P2, P3) -> Unit): ParamsExpectedException3<T, P1, P2, P3> {
        return paramsSpecBuilder.xshouldThrow(T::class.java, description, { p1, p2, p3 -> testBody.invoke(p1, p2, p3) })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.should],
     * taking 4 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4> should(description: String, testBody: (P1, P2, P3, P4) -> Unit):
            ParamsExpected4<P1, P2, P3, P4> {
        return paramsSpecBuilder.should(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fshould],
     * taking 4 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    fun <P1, P2, P3, P4> fshould(description: String, testBody: (P1, P2, P3, P4) -> Unit):
            ParamsExpected4<P1, P2, P3, P4> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fshould(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xshould],
     * taking 4 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4> xshould(description: String, testBody: (P1, P2, P3, P4) -> Unit):
            ParamsExpected4<P1, P2, P3, P4> {
        return paramsSpecBuilder.xshould(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.shouldThrow],
     * taking 4 parameters, for Kotlin.
     */
    inline fun <reified T : Throwable, P1, P2, P3, P4> shouldThrow(
            description: String, crossinline testBody: (P1, P2, P3, P4) -> Unit): ParamsExpectedException4<T, P1, P2, P3, P4> {
        return paramsSpecBuilder.shouldThrow(T::class.java, description, {
            p1, p2, p3, p4 -> testBody.invoke(p1, p2, p3, p4)
        })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fshouldThrow],
     * taking 4 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    inline fun <reified T : Throwable, P1, P2, P3, P4> fshouldThrow(
            description: String, crossinline testBody: (P1, P2, P3, P4) -> Unit): ParamsExpectedException4<T, P1, P2, P3, P4> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fshouldThrow(T::class.java, description, {
            p1, p2, p3, p4 -> testBody.invoke(p1, p2, p3, p4)
        })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xshouldThrow],
     * taking 4 parameters, for Kotlin.
     */
    inline fun <reified T : Throwable, P1, P2, P3, P4> xshouldThrow(
            description: String, crossinline testBody: (P1, P2, P3, P4) -> Unit): ParamsExpectedException4<T, P1, P2, P3, P4> {
        return paramsSpecBuilder.xshouldThrow(T::class.java, description, {
            p1, p2, p3, p4 -> testBody.invoke(p1, p2, p3, p4)
        })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.should],
     * taking 5 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4, P5> should(description: String, testBody: (P1, P2, P3, P4, P5) -> Unit):
            ParamsExpected5<P1, P2, P3, P4, P5> {
        return paramsSpecBuilder.should(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fshould],
     * taking 5 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    fun <P1, P2, P3, P4, P5> fshould(description: String, testBody: (P1, P2, P3, P4, P5) -> Unit):
            ParamsExpected5<P1, P2, P3, P4, P5> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fshould(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xshould],
     * taking 5 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4, P5> xshould(description: String, testBody: (P1, P2, P3, P4, P5) -> Unit):
            ParamsExpected5<P1, P2, P3, P4, P5> {
        return paramsSpecBuilder.xshould(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.shouldThrow],
     * taking 5 parameters, for Kotlin.
     */
    inline fun <reified T : Throwable, P1, P2, P3, P4, P5> shouldThrow(
            description: String, crossinline testBody: (P1, P2, P3, P4, P5) -> Unit):
            ParamsExpectedException5<T, P1, P2, P3, P4, P5> {
        return paramsSpecBuilder.shouldThrow(T::class.java, description, {
            p1, p2, p3, p4, p5 -> testBody.invoke(p1, p2, p3, p4, p5)
        })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fshouldThrow],
     * taking 5 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    inline fun <reified T : Throwable, P1, P2, P3, P4, P5> fshouldThrow(
            description: String, crossinline testBody: (P1, P2, P3, P4, P5) -> Unit):
            ParamsExpectedException5<T, P1, P2, P3, P4, P5> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fshouldThrow(T::class.java, description, {
            p1, p2, p3, p4, p5 -> testBody.invoke(p1, p2, p3, p4, p5)
        })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xshouldThrow],
     * taking 5 parameters, for Kotlin.
     */
    inline fun <reified T : Throwable, P1, P2, P3, P4, P5> xshouldThrow(
            description: String, crossinline testBody: (P1, P2, P3, P4, P5) -> Unit):
            ParamsExpectedException5<T, P1, P2, P3, P4, P5> {
        return paramsSpecBuilder.xshouldThrow(T::class.java, description, {
            p1, p2, p3, p4, p5 -> testBody.invoke(p1, p2, p3, p4, p5)
        })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.should],
     * taking 6 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4, P5, P6> should(description: String, testBody: (P1, P2, P3, P4, P5, P6) -> Unit):
            ParamsExpected6<P1, P2, P3, P4, P5, P6> {
        return paramsSpecBuilder.should(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fshould],
     * taking 6 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    fun <P1, P2, P3, P4, P5, P6> fshould(description: String, testBody: (P1, P2, P3, P4, P5, P6) -> Unit):
            ParamsExpected6<P1, P2, P3, P4, P5, P6> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fshould(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xshould],
     * taking 6 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4, P5, P6> xshould(description: String, testBody: (P1, P2, P3, P4, P5, P6) -> Unit):
            ParamsExpected6<P1, P2, P3, P4, P5, P6> {
        return paramsSpecBuilder.xshould(description, testBody)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.shouldThrow],
     * taking 6 parameters, for Kotlin.
     */
    inline fun <reified T : Throwable, P1, P2, P3, P4, P5, P6> shouldThrow(
            description: String, crossinline testBody: (P1, P2, P3, P4, P5, P6) -> Unit):
            ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> {
        return paramsSpecBuilder.shouldThrow(T::class.java, description, {
            p1, p2, p3, p4, p5, p6 -> testBody.invoke(p1, p2, p3, p4, p5, p6)
        })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fshouldThrow],
     * taking 6 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    inline fun <reified T : Throwable, P1, P2, P3, P4, P5, P6> fshouldThrow(
            description: String, crossinline testBody: (P1, P2, P3, P4, P5, P6) -> Unit):
            ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fshouldThrow(T::class.java, description, {
            p1, p2, p3, p4, p5, p6 -> testBody.invoke(p1, p2, p3, p4, p5, p6)
        })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xshouldThrow],
     * taking 6 parameters, for Kotlin.
     */
    inline fun <reified T : Throwable, P1, P2, P3, P4, P5, P6> xshouldThrow(
            description: String, crossinline testBody: (P1, P2, P3, P4, P5, P6) -> Unit):
            ParamsExpectedException6<T, P1, P2, P3, P4, P5, P6> {
        return paramsSpecBuilder.xshouldThrow(T::class.java, description, {
            p1, p2, p3, p4, p5, p6 -> testBody.invoke(p1, p2, p3, p4, p5, p6)
        })
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.describes],
     * taking a single parameter, for Kotlin.
     */
    fun <P> describes(description: String, specClosure: (P) -> Unit): ParamsExpectedSubgroup1<P> {
        return paramsSpecBuilder.describes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fdescribes],
     * taking a single parameter, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    fun <P> fdescribes(description: String, specClosure: (P) -> Unit): ParamsExpectedSubgroup1<P> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fdescribes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xdescribes],
     * taking a single parameter, for Kotlin.
     */
    fun <P> xdescribes(description: String, specClosure: (P) -> Unit): ParamsExpectedSubgroup1<P> {
        return paramsSpecBuilder.xdescribes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.describes],
     * taking 2 parameters, for Kotlin.
     */
    fun <P1, P2> describes(description: String, specClosure: (P1, P2) -> Unit): ParamsExpectedSubgroup2<P1, P2> {
        return paramsSpecBuilder.describes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fdescribes],
     * taking 2 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    fun <P1, P2> fdescribes(description: String, specClosure: (P1, P2) -> Unit): ParamsExpectedSubgroup2<P1, P2> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fdescribes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xdescribes],
     * taking 2 parameters, for Kotlin.
     */
    fun <P1, P2> xdescribes(description: String, specClosure: (P1, P2) -> Unit): ParamsExpectedSubgroup2<P1, P2> {
        return paramsSpecBuilder.xdescribes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.describes],
     * taking 3 parameters, for Kotlin.
     */
    fun <P1, P2, P3> describes(description: String, specClosure: (P1, P2, P3) -> Unit):
            ParamsExpectedSubgroup3<P1, P2, P3> {
        return paramsSpecBuilder.describes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fdescribes],
     * taking 3 parameters, for Kotlin.
     */
    fun <P1, P2, P3> fdescribes(description: String, specClosure: (P1, P2, P3) -> Unit):
            ParamsExpectedSubgroup3<P1, P2, P3> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fdescribes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xdescribes],
     * taking 3 parameters, for Kotlin.
     */
    fun <P1, P2, P3> xdescribes(description: String, specClosure: (P1, P2, P3) -> Unit):
            ParamsExpectedSubgroup3<P1, P2, P3> {
        return paramsSpecBuilder.xdescribes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.describes],
     * taking 4 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4> describes(description: String, specClosure: (P1, P2, P3, P4) -> Unit):
            ParamsExpectedSubgroup4<P1, P2, P3, P4> {
        return paramsSpecBuilder.describes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fdescribes],
     * taking 4 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    fun <P1, P2, P3, P4> fdescribes(description: String, specClosure: (P1, P2, P3, P4) -> Unit):
            ParamsExpectedSubgroup4<P1, P2, P3, P4> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fdescribes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xdescribes],
     * taking 4 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4> xdescribes(description: String, specClosure: (P1, P2, P3, P4) -> Unit):
            ParamsExpectedSubgroup4<P1, P2, P3, P4> {
        return paramsSpecBuilder.xdescribes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.describes],
     * taking 5 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4, P5> describes(description: String, specClosure: (P1, P2, P3, P4, P5) -> Unit):
            ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> {
        return paramsSpecBuilder.describes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fdescribes],
     * taking 5 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4, P5> fdescribes(description: String, specClosure: (P1, P2, P3, P4, P5) -> Unit):
            ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fdescribes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xdescribes],
     * taking 5 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4, P5> xdescribes(description: String, specClosure: (P1, P2, P3, P4, P5) -> Unit):
            ParamsExpectedSubgroup5<P1, P2, P3, P4, P5> {
        return paramsSpecBuilder.xdescribes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.describes],
     * taking 5 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4, P5, P6> describes(description: String, specClosure: (P1, P2, P3, P4, P5, P6) -> Unit):
            ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> {
        return paramsSpecBuilder.describes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.fdescribes],
     * taking 5 parameters, for Kotlin.
     */
    @Deprecated("Deprecated for the same reason SpecBuilder#fshould is")
    fun <P1, P2, P3, P4, P5, P6> fdescribes(description: String, specClosure: (P1, P2, P3, P4, P5, P6) -> Unit):
            ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> {
        @Suppress("DEPRECATION")
        return paramsSpecBuilder.fdescribes(description, specClosure)
    }

    /**
     * The parametrized equivalent of [org.specnaz.SpecBuilder.xdescribes],
     * taking 5 parameters, for Kotlin.
     */
    fun <P1, P2, P3, P4, P5, P6> xdescribes(description: String, specClosure: (P1, P2, P3, P4, P5, P6) -> Unit):
            ParamsExpectedSubgroup6<P1, P2, P3, P4, P5, P6> {
        return paramsSpecBuilder.xdescribes(description, specClosure)
    }
}
