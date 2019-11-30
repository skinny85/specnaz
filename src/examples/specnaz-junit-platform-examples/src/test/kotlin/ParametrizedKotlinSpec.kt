import org.junit.platform.commons.annotation.Testable
import org.specnaz.kotlin.params.SpecnazKotlinParams

/**
 * Example of a parametrized Kotlin spec,
 * that can extend an arbitrary class,
 * running using JUnit 5 as the execution engine.
 */
@Testable
class ParametrizedKotlinSpec : SpecnazKotlinParams { init {
    describes("A parametrized Kotlin spec") {
        it.shouldThrow<NumberFormatException, String>("when parsing '%1' as an Int") { str ->
            Integer.parseInt(str)
        }.provided("cafe", "BABE").withoutCause()
    }
}}
