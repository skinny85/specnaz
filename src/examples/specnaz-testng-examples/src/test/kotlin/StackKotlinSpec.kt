import org.specnaz.kotlin.testng.SpecnazKotlinTestNG
import org.testng.Assert
import java.util.Stack

class StackKotlinSpec : SpecnazKotlinTestNG("A Stack (Kotlin)", {
    var stack = Stack<Int>()

    it.endsEach {
        stack = Stack()
    }

    it.should("be empty when first created") {
        Assert.assertTrue(stack.isEmpty())
    }

    it.describes("with 10 and 20 pushed on it") {
        it.beginsEach {
            stack.push(10)
            stack.push(20)
        }

        it.should("have size equal to 2") {
            Assert.assertEquals(stack.size, 2)
        }

        it.should("have 20 as the top element") {
            Assert.assertEquals(stack.peek(), 20)
        }
    }
})
