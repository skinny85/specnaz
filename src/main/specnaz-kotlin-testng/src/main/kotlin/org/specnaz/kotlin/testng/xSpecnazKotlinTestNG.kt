package org.specnaz.kotlin.testng

import org.specnaz.kotlin.KotlinSpecBuilder
import org.testng.annotations.Test

/**
 * A way to ignore all specs in a class that uses [SpecnazKotlinTestNG] as its superclass.
 *
 * If you want to turn off all specs in a particular class,
 * you can't use the [org.specnaz.kotlin.SpecnazKotlin.xdescribes]
 * method if your spec class inherits from [SpecnazKotlinTestNG] -
 * the method call is buried in the constructor of [SpecnazKotlinTestNG].
 * To alleviate that, this class calls [org.specnaz.kotlin.SpecnazKotlin.xdescribes]
 * in its constructor instead of [org.specnaz.kotlin.SpecnazKotlin.describes].
 * Which means all you need to do to ignore all
 * specs in a given class extending [SpecnazKotlinTestNG]
 * is add an 'x' in front of [SpecnazKotlinTestNG].
 *
 * @see SpecnazKotlinTestNG
 * @see org.specnaz.kotlin.SpecnazKotlin.xdescribes
 */
@Test
abstract class xSpecnazKotlinTestNG(description: String,
        specClosure: (KotlinSpecBuilder) -> Unit) : SpecnazKotlinFactoryTestNG {
    init {
        xdescribes(description, specClosure)
    }
}
