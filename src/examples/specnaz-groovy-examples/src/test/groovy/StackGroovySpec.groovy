import org.specnaz.junit.SpecnazJUnit

public class StackGroovySpec extends SpecnazJUnit {{
    describes("A Stack") {
        Stack<Integer> stack = new Stack<>()

        it.should("be empty when first created") {
            assert stack.isEmpty()
        }

        it.endsEach {
            stack = new Stack<>()
        }

        it.describes("with 10 and 20 pushed on it") { ->
            it.beginsEach {
                stack.push(10)
                stack.push(20)
            }

            it.should("have size equal to 2") {
                assert stack.size() == 2
            }

            it.should("have 20 as the top element") {
                assert stack.peek() == 20
            }
        }
    }
}}
