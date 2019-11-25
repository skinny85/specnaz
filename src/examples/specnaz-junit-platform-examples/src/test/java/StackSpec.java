import org.junit.platform.commons.annotation.Testable;
import org.specnaz.Specnaz;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is the "standard" Stack example from the ReadMe,
 * but using the JUnit 5 runtime.
 * As you can see, all you need to do is annotate your test class with the
 * {@link Testable} annotation, and implement the {@link Specnaz} interface -
 * the rest is the same as any other Specnaz spec.
 */
@Testable
public class StackSpec implements Specnaz {{
    describes("A Stack", it -> {
        Stack<Integer> stack = new Stack<>();

        it.endsEach(() -> {
            stack.clear();
        });

        it.should("be empty when first created", () -> {
            assertThat(stack).isEmpty();
        });

        it.describes("with 10 and 20 pushed on it", () -> {
            it.beginsEach(() -> {
                stack.push(10);
                stack.push(20);
            });

            it.should("have size equal to 2", () -> {
                assertThat(stack).hasSize(2);
            });

            it.should("have 20 as the top element", () -> {
                assertThat(stack.peek()).isEqualTo(20);
            });
        });
    });
}}
