import org.specnaz.kotlin.params.testng.SpecnazKotlinParamsFactoryTestNG
import org.testng.annotations.Test

@Test
class ParametrizedKotlinSpec : SpecnazKotlinParamsFactoryTestNG { init {
    describes("A parametrized Kotlin spec") {
        it.shouldThrow<NumberFormatException, String>("when parsing '%1' as an Int") { str ->
            Integer.parseInt(str)
        }.provided("cafe", "BABE").withoutCause()
    }
}}
