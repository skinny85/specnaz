import org.assertj.core.api.Assertions.assertThat
import org.specnaz.kotlin.params.junit.SpecnazKotlinParamsJUnit
import org.specnaz.params.Params2.p2
import org.specnaz.params.Params3.p3

/**
 * Example of a parametrized Kotlin spec.
 */
class KotlinParametrizedSpec : SpecnazKotlinParamsJUnit("A parametrized spec", {
    it.should("confirm that %1 + %2 = %3") { a: Int, b: Int, c: Int ->
        assertThat(a + b).isEqualTo(c)
    }.provided(listOf(
            p3(1, 2, 3),
            p3(4, 4, 8),
            p3(-3, 3, 0),
            p3(Int.MAX_VALUE, 1, Int.MIN_VALUE)
    ))

    it.shouldThrow<NumberFormatException, String>("when parsing '%1' as an Int") { str ->
        Integer.parseInt(str)
    }.provided("cafe", "BABE").withoutCause()

    it.describes("with '%1' as the argument") { str: String, result: Int ->
        it.should<Int>("correctly parse '$str' as an Int = $result in radix %1") { radix ->
            assertThat(Integer.parseInt(str, radix)).isEqualTo(result)
        }.provided(16)
    }.provided(
            p2("cafe", 51966),
            p2("BABE", 47806)
    )
})
