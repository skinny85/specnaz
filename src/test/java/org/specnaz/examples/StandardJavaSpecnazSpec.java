package org.specnaz.examples;

import kotlin.Unit;
import org.specnaz.junit.SpecnazJUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class StandardJavaSpecnazSpec extends SpecnazJUnit {
    public StandardJavaSpecnazSpec() {
        super(it -> {
            int[] two = {0};

            it.beforeEach((it1) -> {
                two[0]++;

                return null;
            });

            it.beforeEach((it1) -> {
                two[0]++;

                return null;
            });

            it.should("run this test", (it1) -> {
                assertThat(two[0] + 2).isEqualTo(4);

                return null;
            });

            return Unit.INSTANCE;
        });
    }
}
