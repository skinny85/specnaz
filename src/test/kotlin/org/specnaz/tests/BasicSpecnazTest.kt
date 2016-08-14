package org.specnaz.tests

import org.assertj.core.api.Assertions.assertThat
import org.specnaz.junit.SpecnazJUnit

class BasicSpecnazTest : SpecnazJUnit({
    var two = 0

    it.beforeEach {
        two = 1
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
})
