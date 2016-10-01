Using Specnaz from Groovy
-------------------------

You can use the Specnaz Java API directly from Groovy:

```groovy
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

```

The code is more concise -
Groovy, like Kotlin, automatically binds the `it` variable to the parameter
of a one-argument anonymous function - and has less punctuation.
Thanks to Groovy's `PowerAsserts`, you also don't need an assertion library.
Another cool thing is that Java's irritating
'variables referenced in lambdas must be final or effectively final'
limitation is also gone.

The one potential 'gotcha' that you need to be aware of is with the
`describes` method.
Groovy _always_ creates a one-argument function when using the closure syntax
without arguments.
Because the functional interface that `describes` expects as the second
parameter is a 0-argument method, in that case `it` will be bound to
`Void` - so, basically `null`, shadowing the outer variable that allows
you to construct the sub-specification.

To avoid that, you have to explicitly tell Groovy that you want a
zero-argument closure by using the `->` syntax after the opening brace.

So, it should be:

```groovy
describes("a subgroup", { ->
```

and _not_:


```groovy
describes("a subgroup", { // wrong!
```
