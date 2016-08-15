package org.specnaz.tests

import org.assertj.core.api.Assertions.assertThat
import org.specnaz.junit.SpecnazJUnit

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
