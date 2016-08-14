package org.specnaz.tests

import org.assertj.core.api.Assertions.assertThat
import org.specnaz.junit.SpecnazJUnit

class BasicSpecnazTest : SpecnazJUnit({
    it.should("sum two numbers correctly") {
        assertThat(2 + 2).isEqualTo(4)
    }

    it.should("subtract two numbers correctly") {
        assertThat(2 - 2).isZero()
    }
})
