import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.Assumptions.assumeTrue
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
        }.provided("cafe", "BABE", "$").withoutCause()

        it.should("respect failed assumptions by aborting the test execution") {
            assumeTrue(false)
            fail("this test should not fail (should be skipped because of a failed assumption)")
        }
    }
}}
