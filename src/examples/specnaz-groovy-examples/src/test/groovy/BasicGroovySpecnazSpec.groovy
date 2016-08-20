import org.specnaz.junit.SpecnazJUnit

import static org.assertj.core.api.Assertions.assertThat

class BasicGroovySpecnazSpec extends SpecnazJUnit {{
    describes("arithmetic operations") {
        it.should("add two numbers correctly") {
            assertThat(2 + 2).isEqualTo(4)
        }

        it.should("subtract two numbers correctly") {
            assertThat(2 - 2).isZero()
        }
    }
}}
