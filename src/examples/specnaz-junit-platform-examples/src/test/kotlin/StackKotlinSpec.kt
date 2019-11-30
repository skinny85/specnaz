import org.specnaz.kotlin.junit.platform.SpecnazKotlinJUnitPlatform
import java.util.Stack
import org.assertj.core.api.Assertions.assertThat

/**
 * This is the example spec from the Readme file,
 * but written in Kotlin and using JUnit 5.
 * If your test class needs to extend a particular class,
 * and/or you want to see a JUnit 5 parametrized spec,
 * see the [ParametrizedKotlinSpec] class.
 */
class StackKotlinSpec : SpecnazKotlinJUnitPlatform("A Stack", {
    var stack = Stack<Int>()

    it.endsEach {
        stack = Stack()
    }

    it.should("be empty when first created") {
        assertThat(stack).isEmpty()
    }

    it.describes("with 10 and 20 pushed on it") {
        it.beginsEach {
            stack.push(10)
            stack.push(20)
        }

        it.should("have size equal to 2") {
            assertThat(stack.size).isEqualTo(2)
        }

        it.should("have 20 as the top element") {
            assertThat(stack.peek()).isEqualTo(20)
        }
    }
})
