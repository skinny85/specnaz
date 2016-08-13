package org.specnaz.tests

import org.assertj.core.api.Assertions.assertThat
import org.specnaz.Specnaz

class BasicSpecnazTest : Specnaz() {
    override fun tests(): () -> Unit = {
        assertThat(2 + 2).isEqualTo(4)
    }
}
