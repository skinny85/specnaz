package examples;

import org.specnaz.java.junit.SpecnazJunitJava;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaSpecnazSpecWithJavaBridge extends SpecnazJunitJava {
    public JavaSpecnazSpecWithJavaBridge() {
        super(it -> {
            int[] two = {0};

            it.beforeEach(() -> {
                two[0]++;
            });

            it.beforeEach(() -> {
                two[0]++;
            });

            it.should("correctly add two numbers", () -> {
                assertThat(two[0] + 2).isEqualTo(4);
            });

            it.should("correctly subtract two numbers", () -> {
                assertThat(two[0] - 2).isZero();
            });

            it.afterEach(() -> {
                two[0]--;
            });

            it.afterEach(() -> {
                two[0]--;
            });
        });
    }
}
