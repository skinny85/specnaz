package org.specnaz.tests

import org.assertj.core.api.Assertions.assertThat
import org.specnaz.SpecName
import org.specnaz.junit.SpecnazJUnit

@SpecName("arithmetic functions")
class BasicSpecnazTest : SpecnazJUnit({
    var two = -2

    it.beforeAll {
        two += 2
    }

    it.beforeEach {
        two++
    }

    it.beforeEach {
        two++
    }

    it.should("sum two numbers correctly") {
        assertThat(two + 2).isEqualTo(4)
    }

    it.should("subtract two numbers correctly") {
        assertThat(two - 2).isZero()
    }

    it.spec("with a subgroup") {
        it.beforeAll {
            two += 3
        }

        it.should("run all parent 'before' callbacks") {
            assertThat(two).isEqualTo(5)
        }

        it.spec("and a third-degree subgroup") {
            it.beforeEach {
                two += 4
            }

            it.should("run all ancestors 'before' callbacks") {
                assertThat(two).isEqualTo(9)
            }

            it.afterEach {
                two -= 4
            }

            it.spec("with a subgroup without tests") {
            }
        }

        it.afterAll {
            two -= 3
        }
    }

    it.afterEach {
        two--
    }

    it.afterEach {
        two--
    }

    it.afterAll {
        assertThat(two).isZero()
        two -= 2
    }
})
