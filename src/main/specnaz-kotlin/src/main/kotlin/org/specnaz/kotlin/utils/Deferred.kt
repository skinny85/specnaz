package org.specnaz.kotlin.utils

import kotlin.properties.Delegates

/**
 * When writing specifications, a common scenario is
 * wanting to defer the initialization of a variable used in
 * the spec to a `beginsEach`/`beginsAll` method:
 *
 * ```kotlin
 * class MySpec : SpecnazKotlinJUnit("My domain spec", {
 *     var myObject: MyClass
 *
 *     it.beginsEach {
 *         myObject = complicatedDomainOperationThatCanFail()
 *     }
 *
 *     it.should("have some property") {
 *         Assert.assertTrue(myObject.someProperty) // does not compile!
 *     }
 * })
 * ```
 *
 * However, Kotlin's strong type system gets in the way here.
 * The above code is not valid - the compiler will complain that
 * the variable `myObject` might not have been initialized.
 *
 * You can work around that by making `myObject` nullable,
 * but that means you have to add `!!` whenever you are dereferencing
 * the object, adding noise to the test.
 *
 * Specnaz provides the [Deferred] utility class for these cases.
 * Your class instance will be accessible through the `v` public property
 * of the [Deferred] instance, without needing to be initialized first:
 *
 * ```kotlin
 * class MySpec : SpecnazKotlinJUnit("My domain spec", {
 *     val myObject = Deferred<MyClass>()
 *
 *     it.beginsEach {
 *         myObject.v = complicatedDomainOperationThatCanFail()
 *     }
 *
 *     it.should("have some property") {
 *         Assert.assertTrue(myObject.v.someProperty)
 *     }
 * })
 * ```
 *
 * Using inheritance, you can make it read even nicer:
 *
 * ```kotlin
 * class MySpec : SpecnazKotlinJUnit("My domain spec", {
 *     val myObject = object : Deferred<MyClass>() {
 *         val someProperty : Boolean get() = v.someProperty
 *     }
 *
 *     it.beginsEach {
 *         myObject.v = complicatedDomainOperationThatCanFail()
 *     }
 *
 *     it.should("have some property") {
 *         Assert.assertTrue(myObject.someProperty)
 *     }
 * })
 * ```
 */
open class Deferred<T : Any> {
    var v: T by Delegates.notNull<T>()
}
