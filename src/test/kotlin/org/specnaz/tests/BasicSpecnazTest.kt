package org.specnaz.tests

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.specnaz.Specnaz

class BasicSpecnazTest : Specnaz {
    @Test
    fun someTest() {
        assertThat(2 + 2).isEqualTo(4)
    }
}
