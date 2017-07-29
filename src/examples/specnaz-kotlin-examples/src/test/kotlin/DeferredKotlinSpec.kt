import org.assertj.core.api.Assertions.assertThat
import org.specnaz.kotlin.junit.SpecnazKotlinJUnit
import org.specnaz.kotlin.utils.Deferred

/**
 * This spec demonstrates how to use the [Deferred] class.
 */
class DeferredKotlinSpec : SpecnazKotlinJUnit("uses deferred delegates", {
    // the irritating thing with Kotlin is that you have to provide
    // either default values to the variables, or make them nullable
    @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
    var resultOfSomeComplicatedCalculation: MyCustomHardToInstantiateClass

    fun doAComplicatedCalculation() = MyCustomHardToInstantiateClass()

    it.beginsEach {
        resultOfSomeComplicatedCalculation = doAComplicatedCalculation()
    }

    it.should("not want to compile") {
//        assertThat(resultOfSomeComplicatedCalculation).isNotNull()
        // it will say that 'resultOfSomeComplicatedCalculation must be initialized'
    }

    // this is where the deferred utility class comes in handy

    // we declare a Deferred variable of the desired type
    val anotherResult = Deferred<MyCustomHardToInstantiateClass>()

    // we instantiate the actual object in a fixture,
    // and store it in the 'v' property of the Deferred object
    it.beginsEach {
        anotherResult.v = doAComplicatedCalculation()
    }

    // and everything works!
    it.should("compile now") {
        assertThat(anotherResult.v.someProperty).isTrue()
    }

    // using inheritence, we can make it even more concise
    val propResult = object : Deferred<MyCustomHardToInstantiateClass>() {
        val someProperty : Boolean get() = v.someProperty
    }

    it.beginsEach {
        propResult.v = doAComplicatedCalculation()
    }

    it.should("be even better now") {
        assertThat(propResult.someProperty).isTrue() // not propResult.v.someProperty!
    }
})

class MyCustomHardToInstantiateClass {
    val someProperty: Boolean get() = true
}
