import org.assertj.core.api.Assertions.assertThat
import org.specnaz.kotlin.params.junit.platform.SpecnazKotlinParamsJUnitPlatform

class ParametrizedIntegrationSpec : SpecnazKotlinParamsJUnitPlatform("Specnaz Kotlin JUnit 5", {
    it.should("correctly execute parametrized tests (%1 > 0)") { i: Int ->
        assertThat(i).isGreaterThan(0)
    }.provided(1, 2, 3)
})
