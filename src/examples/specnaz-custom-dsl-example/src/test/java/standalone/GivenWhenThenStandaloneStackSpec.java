package standalone;

import org.junit.runner.RunWith;
import org.specnaz.examples.custom_dsl.given_when_then.standalone.GivenWhenThenStandaloneJUnitRunner;
import org.specnaz.examples.custom_dsl.given_when_then.standalone.SpecnazGivenWhenThenStandalone;
import org.specnaz.utils.IntBox;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.specnaz.utils.IntBox.boxWith;

@RunWith(GivenWhenThenStandaloneJUnitRunner.class)
public class GivenWhenThenStandaloneStackSpec implements SpecnazGivenWhenThenStandalone {
    {
        given("a Stack", that -> {
            Stack<Integer> stack = new Stack<>();

            that.given("with 10 and 20 and 30 pushed on it",
                    () -> {
                        stack.push(10);
                        stack.push(20);
                        stack.push(30);
                    },
                    () -> {
                        IntBox top = boxWith(0);

                        that.when("pop is called on it",
                                () -> {
                                    top.$ = stack.pop();
                                },
                                _that -> {
                                    _that.then("the size of the stack is 2", () -> {
                                        assertThat(stack).hasSize(2);
                                    });

                                    _that.then("the popped value is 30", () -> {
                                        assertThat(top.$).isEqualTo(30);
                                    });
                                }
                        );
                    }
            );
        });

        given("a", then -> {

        });
    }
}
