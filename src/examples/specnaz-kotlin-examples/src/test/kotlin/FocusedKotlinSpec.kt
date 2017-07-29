import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.specnaz.kotlin.junit.SpecnazKotlinJUnit

/**
 * This example shows the usage of 'focused' methods in Kotlin,
 * and how the fixtures like {@code beginsAll/Each} and
 * {@code endsAll/Each} are executed even for groups with all
 * tests ignored.
 */
class FocusedKotlinSpec : SpecnazKotlinJUnit("A spec with focused tests", {
    var counter = 1

    it.beginsAll {
        counter += 1
    }

    it.should("ignore a non-focused test") {
        fail("this unfocused test should not have been executed")
    }

    it.describes("and a subgroup with focused tests") {
        @Suppress("DEPRECATION")
        it.fshould("execute the parent fixtures for focused tests") {
            assertThat(counter).isEqualTo(2)
        }
    }
})
