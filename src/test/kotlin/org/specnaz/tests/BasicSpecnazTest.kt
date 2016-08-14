package org.specnaz.tests

import org.assertj.core.api.Assertions.assertThat
import org.specnaz.junit.SpecnazJUnit

class BasicSpecnazTest : SpecnazJUnit({
    assertThat(2 + 2).isEqualTo(4)
})
