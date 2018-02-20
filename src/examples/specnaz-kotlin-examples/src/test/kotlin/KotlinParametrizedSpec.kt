import org.assertj.core.api.Assertions.assertThat
import org.specnaz.kotlin.params.junit.SpecnazKotlinParamsJUnit
import org.specnaz.params.Params2.p
import org.specnaz.params.Params3.p

/**
 * Example of a parametrized Kotlin spec.
 */
class KotlinParametrizedSpec : SpecnazKotlinParamsJUnit("A parametrized spec", {
    it.should("confirm that %1 + %2 = %3", { a: Int, b: Int, c: Int ->
        assertThat(a + b).isEqualTo(c)
    }).provided(listOf(
            p(1, 2, 3),
            p(4, 4, 8),
            p(-3, 3, 0),
            p(Int.MAX_VALUE, 1, Int.MIN_VALUE)
    ))

    it.shouldThrow<NumberFormatException, String>("when parsing '%1' as an Int") { str ->
        Integer.parseInt(str)
    }.provided("cafe", "BABE").withoutCause()

    it.describes("with '%1' as the argument") { str: String, result: Int ->
        it.should<Int>("correctly parse '$str' as an Int = $result in radix %1") { radix ->
            assertThat(Integer.parseInt(str, radix)).isEqualTo(result)
        }.provided(16)
    }.provided(
                    p("cafe", 51966),
                    p("BABE", 47806)
            )
})
