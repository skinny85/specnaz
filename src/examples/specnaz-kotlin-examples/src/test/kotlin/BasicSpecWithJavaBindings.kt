import org.assertj.core.api.Assertions.assertThat
import org.specnaz.junit.SpecnazJUnit

class BasicSpecWithJavaBindings : SpecnazJUnit() { init {
    describes("arithmetic operations") {
        var two = -2

        it.beginsAll {
            two += 2
        }

        it.beginsEach {
            two++
        }

        it.beginsEach {
            two++
        }

        it.endsEach {
            two--
        }

        it.endsEach {
            two--
        }

        it.endsAll {
            assertThat(two).isZero()
            two -= 2
        }

        it.should("add two numbers correctly") {
            assertThat(two + 2).isEqualTo(4)
        }

        it.should("subtract two numbers correctly") {
            assertThat(two - 2).isZero()
        }

        it.shouldThrow(ArithmeticException::class.java, "when dividing by zero") {
            1 / (two - 2)
        }

        it.describes("with a subgroup") {
            it.beginsAll {
                two += 3
            }

            it.endsAll {
                two -= 3
            }

            it.should("run all parent 'before' callbacks") {
                assertThat(two).isEqualTo(5)
            }

            it.describes("and a third-degree subgroup") {
                it.beginsEach {
                    two += 4
                }

                it.endsEach {
                    two -= 4
                }

                it.should("run all ancestors 'before' callbacks") {
                    assertThat(two).isEqualTo(9)
                }

                it.describes("with a subgroup without tests") {
                }
            }
        }
    }
}}
