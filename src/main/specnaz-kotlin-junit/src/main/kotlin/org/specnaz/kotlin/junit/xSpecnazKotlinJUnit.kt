package org.specnaz.kotlin.junit

import org.junit.runner.RunWith
import org.specnaz.kotlin.KotlinSpecBuilder
import org.specnaz.kotlin.SpecnazKotlin

/**
 * A way to ignore all specs in a class that uses [SpecnazKotlinJUnit] as its superclass.
 *
 * If you want to turn off all specs in a particular class,
 * you can't use the [SpecnazKotlin.xdescribes] method if your spec class inherits from
 * [SpecnazKotlinJUnit] - the method call is buried in the constructor of the superclass.
 * To alleviate that, this class calls [SpecnazKotlin.xdescribes] in its constructor
 * instead of [SpecnazKotlin.describes]. Which means all you need to do to ignore all
 * specs in a given class is to add an 'x' in front of [SpecnazKotlinJUnit].
 */
@RunWith(SpecnazKotlinJUnitRunner::class)
abstract class xSpecnazKotlinJUnit(description: String,
                                   specClosure: (KotlinSpecBuilder) -> Unit) :
        SpecnazKotlin {
    init {
        xdescribes(description, specClosure)
    }
}
