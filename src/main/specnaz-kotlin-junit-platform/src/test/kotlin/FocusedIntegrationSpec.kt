import org.assertj.core.api.Assertions.fail
import org.specnaz.kotlin.junit.platform.SpecnazKotlinJUnitPlatform

class FocusedIntegrationSpec : SpecnazKotlinJUnitPlatform("Specnaz Kotlin JUnit 5", {
    it.should("not execute this (unfocused) test") {
        fail("this unfocused test should not have been executed!")
    }

    @Suppress("DEPRECATION")
    it.fshould("only execute this focused test in the class") {
        // do nothing
    }
})
