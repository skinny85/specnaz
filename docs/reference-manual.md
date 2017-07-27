![specnaz logo](img/specnaz-logo.png)

RSpec and Jasmine-inspired, extensible test and specification library for Java and Kotlin
-------------------------------------------------------------------------------

This is the reference documentation for the Specnaz library.
For a quick introduction to Specnaz, check out the main
[Readme file](../Readme.md). For code examples, look into the
[src/examples directory](../src/examples).

Table of Contents
=================

  * [Getting Specnaz](#getting-specnaz)
    * [Using dependency managers (Maven, Gradle etc.)](#using-dependency-managers-maven-gradle-etc)
    * [Manual configuration](#manual-configuration)
  * [Writing tests](#writing-tests)
    * [Basic test structure - the Specnaz interface](#basic-test-structure---the-specnaz-interface)
    * [Framework integrations - JUnit](#framework-integrations---junit)
    * [Calling describes](#calling-describes)
    * [Creating the spec](#creating-the-spec)
      * [should](#should)
      * [shouldThrow](#shouldthrow)
      * [beginsEach](#beginseach)
      * [endsEach](#endseach)
      * [beginsAll](#beginsall)
      * [endsAll](#endsall)
      * [describes](#describes)
        * [Nested specifications and fixture execution order](#nested-specifications-and-fixture-execution-order)
          * [Difference between RSpec and Jasmine](#difference-between-rspec-and-jasmine)
    * [Using Boxes](#using-boxes)
    * [Using Specnaz in other JVM languages](#using-specnaz-in-other-jvm-languages)
      * [Kotlin](#kotlin)
        * [Using native Java classes](#using-native-java-classes)
        * [Using the Kotlin bindings](#using-the-kotlin-bindings)
        * [The Deferred helper](#the-deferred-helper)
      * [Groovy](#groovy)
  * [Extending Specnaz](#extending-specnaz)


# Getting Specnaz

## Using dependency managers (Maven, Gradle etc.)

Specnaz is available through the Bintray Maven repository.
Take a look at the [main Readme file](../Readme.md#getting-specnaz) on how to get it using
build tools that also do dependency management
(Maven, Gradle, SBT, Ivy etc.).

## Manual configuration

If you're not using a dependency manager, you need to manually download
the needed JARs and put them on your classpath:

* [specnaz](https://bintray.com/skinny85/maven-repo/download_file?file_path=org%2Fspecnaz%2Fspecnaz%2F1.1%2Fspecnaz-1.1.jar)
* [specnaz-junit](https://bintray.com/skinny85/maven-repo/download_file?file_path=org%2Fspecnaz%2Fspecnaz-junit%2F1.1%2Fspecnaz-junit-1.1.jar)

If you want to use the Kotlin integration,
in addition to the ones above, you also need:

* [specnaz-kotlin](https://bintray.com/skinny85/maven-repo/download_file?file_path=org%2Fspecnaz%2Fspecnaz-kotlin%2F1.1%2Fspecnaz-kotlin-1.1.jar)
* [specnaz-kotlin-junit](https://bintray.com/skinny85/maven-repo/download_file?file_path=org%2Fspecnaz%2Fspecnaz-kotlin-junit%2F1.1%2Fspecnaz-kotlin-junit-1.1.jar)

# Writing tests

## Basic test structure - the Specnaz interface

The requirements that your tests class must fulfill are simple.
There are 3 of them:

* Your test classes must implement the `org.specnaz.Specnaz` interface
   This is an interface with one method (`describes`) which is default
   (it has an implementation), so your class doesn't need any
   additional code to use it.
* Your test class must have a public, no-argument constructor.
* In that constructor, the `describes` method from the `org.specnaz.Specnaz`
interface must be called *exactly once*.
    The `describes` method is how you construct the tests
    (usually called 'specifications' by convention)
    in Specnaz. We will look at that method in more details in later chapters.

## Framework integrations - JUnit

Specnaz currently supports only JUnit as the testing harness
(work is underway on adding others, like TestNG).

The easiest way to use it is to extend the `org.specnaz.junit.SpecnazJUnit`
helper class, which already implements the `Specnaz` interface:

```java
public class StackSpec extends SpecnazJUnit {
    // body of the spec here...
}
```

If you want to extend a class other than `SpecnazJUnit`,
you need to specify the JUnit `Runner` for Specnaz,
`org.specnaz.junit.SpecnazJUnitRunner`,
using the `@RunWith` annotation:

```java
@RunWith(SpecnazJUnitRunner.class)
public class StackSpec extends CommonSpec implements Specnaz {
    // body of the spec here...
}
```

## Calling `describes`

Because the `describes` method needs to be called in the default constructor,
the most concise way of formulating your specification is by using Java's
initializer block:

```java
public class StackSpec extends SpecnazJUnit {
    {
        describes("A Stack", it -> {
            // body of the spec here...
        });
    }
}
```

You can use the "double-braces" syntax as well,
which saves you one level of indentation:

```java
public class StackSpec extends SpecnazJUnit {{
    describes("A Stack", it -> {
        // body of the spec here...
    });
}}
```

The first argument to the method is the top-level description of the spec.

As you can see, by convention, the parameter of the closure given as the
second argument, of type `org.specnaz.SpecBuilder`, is named `it`.
The methods in the `SpecBuilder` interface were named expecting this
convention, and sticking to it assures that your specification reads well
(it also means you will be consistent with specs written in languages
other than Java).

## Creating the spec

The specification is built by calling methods on the declared
`SpecBuilder` instance. They are:

### should

The `should` method introduces a test.
It's the equivalent of RSpec's or Jasmine's `it` method.

Simple example:

```java
public class StackSpec extends SpecnazJUnit {{
    describes("A Stack", it -> {
        it.should("be empty when first created", () -> {
            Stack<Integer> newStack = new Stack<>();
            
            Assert.assertTrue(newStack.isEmpty());
        });
    });
}}
```

You can have any number of these tests in one group:

```java
public class StackSpec extends SpecnazJUnit {{
    describes("A Stack", it -> {
        it.should("be empty when first created", () -> {
            Stack<Integer> newStack = new Stack<>();
            
            Assert.assertTrue(newStack.isEmpty());
        });
        
        it.should("have size 0 when first created", () -> {
            Stack<Integer> newStack = new Stack<>();
            
            Assert.assertEquals(0, newStack.size());
        });
    });
}}
```

The descriptions you give as the first argument will become the test
names in the report - except they will have the word "should" prepended
to them, so formulate your descriptions with that in mind.

Note that you cannot nest `should` methods inside each other;
something like this:

```java
it.should("first test", () -> {
    it.should("nested test", () -> { // this is wrong!
```

wil not work. Take a look at the `describes` method below if you want to
nest test contexts within each other.

### shouldThrow

This method is very similar to the `should` method - it also introduces a test.
The difference is that the test passes only if executing it results in an Exception
of the type passed to this method.

Example:

```java
it.shouldThrow(ArithmeticException.class, "when dividing by zero", () -> {
    int unused = 1 / 0;
});
```

The description you give as the second argument will become the test name in the report -
except it will have the words "should throw ExpectedExceptionClass" prepended to them,
so formulate your descriptions with that in mind.

### beginsEach

The `beginsEach` method introduces a test fixture that will be ran before
each `should` test case.
It's the equivalent of RSpec's or Jasmine's `beforeEach`.
You can also think of it as JUnit's `@Before` or TestNG's `@BeforeTest`.

Example:

```java
public class StackSpec extends SpecnazJUnit {
    Stack<Integer> stack;

    {
        describes("A Stack", it -> {
            it.beginsEach(() -> {
                stack = new Stack<>();
            });

            it.should("be empty when first created", () -> {
                Assert.assertTrue(stack.isEmpty());
            });

            it.should("have size 0 when first created", () -> {
                Assert.assertEquals(0, stack.size());
            });
        });
    }
}
```

You can have any number of `beginsEach` fixtures in each group,
and they are guaranteed to run in the order that they were declared.

### endsEach

The `endsEach` method is analogous to `beginsEach`, except it runs _after_
each test case introduced by `should`.
It's the equivalent of RSpec's or Jasmine's `afterEach`.
You can also think of it as JUnit's `@After` or TestNG's `@AfterTest`.

Example:

```java
public class StackSpec extends SpecnazJUnit {
    Stack<Integer> stack = new Stack<>();

    {
        describes("A Stack", it -> {
            it.endsEach(() -> {
                stack = new Stack<>();
            });

            it.should("be empty when first created", () -> {
                Assert.assertTrue(stack.isEmpty());
            });

            it.should("have size 0 when first created", () -> {
                Assert.assertEquals(0, stack.size());
            });
        });
    }
}
```

You can have any number of `endsEach` fixtures in each group,
and they are guaranteed to run in the order that they were declared.

### beginsAll

The `beginsAll` method is similar to `beginsEach`,
except it runs once, before all of the `should` test cases.
It's the equivalent of RSpec's or Jasmine's `beforeAll`.
You can also think of it as JUnit's or TestNG's `@BeforeClass`.

Example:

```java
public class StackSpec extends SpecnazJUnit {
    Stack<Integer> stack;

    {
        describes("A Stack", it -> {
            it.beginsAll(() -> {
                stack = new Stack<>();
            });

            it.should("be empty when first created", () -> {
                Assert.assertTrue(stack.isEmpty());
            });

            it.should("have size 0 when first created", () -> {
                Assert.assertEquals(0, stack.size());
            });
        });
    }
}
```

You can have any number of `beginsAll` fixtures in each group,
and they are guaranteed to run in the order that they were declared.

### endsAll

The `endsAll` method is analogous to `beginsAll`:
it runs once, after all of the test cases introduced by `should`.
It's the equivalent of RSpec's or Jasmine's `afterAll`.
You can also think of it as JUnit's or TestNG's `@AfterClass`.

Example:

```java
public class StackSpec extends SpecnazJUnit {
    Stack<Integer> stack = new Stack<>();

    {
        describes("A Stack", it -> {
            it.endsAll(() -> {
                stack = new Stack<>();
            });

            it.should("be empty when first created", () -> {
                Assert.assertTrue(stack.isEmpty());
            });

            it.should("have size 0 when first created", () -> {
                Assert.assertEquals(0, stack.size());
            });
        });
    }
}
```

You can have any number of `endsAll` fixtures in each group,
and they are guaranteed to run in the order that they were declared.

### describes

The `describes` method is what gives Specnaz its power.
It's used to create sub-specifications,
which share their parent's fixtures.

Example:

```java
public class StackSpec extends SpecnazJUnit {{
    describes("A Stack", it -> {
        Stack<Integer> stack = new Stack<>();

        it.endsEach(() -> {
            stack.clear();
        });

        it.should("be empty when first created", () -> {
            Assert.assertTrue(stack.isEmpty());
        });

        it.describes("with 10 and 20 pushed on it", () -> {
            it.beginsEach(() -> {
                stack.push(10);
                stack.push(20);
            });

            it.should("have size equal to 2", () -> {
                Assert.assertEquals(2, stack.size());
            });

            it.should("have 20 as the top element", () -> {
                Assert.assertEquals(20, (int)stack.peek());
            });
        });
    });
}}
```

You can have any number of `describes` methods in each group.
The nested spec can use exactly the same methods the parent one can,
including `describes`, which means you can create arbitrary-shaped
specification trees.

#### Nested specifications and fixture execution order

It's important to understand precisely the order in which fixtures execute
in case of nested specifications.
To do that, we first need to introduce the concept of *groups*.

A *group* is simply the collection of fixtures and tests on the same
level of nesting in the specification tree.

That may sound a little abstract, so here's an example:

```java
public class NestedSpec extends SpecnazJUnit {{
    describes("Outer group", it -> {
        it.beginsAll(() -> {
            // outer group beginsAll
        });
        
        it.beginsEach(() -> {
            // outer group beginsEach
        });
        
        it.endsEach(() -> {
            // outer group endsEach
        });
        
        it.endsAll(() -> {
            // outer group endsAll
        });
        
        it.should("outer group test 1", () -> {
            // outer group test 1
        });
        
        it.should("outer group test 2", () -> {
            // outer group test 2
        });
        
        it.describes("inner group", () -> {
            it.beginsAll(() -> {
                // inner group beginsAll
            });
            
            it.beginsEach(() -> {
                // inner group beginsEach
            });
            
            it.endsEach(() -> {
                // inner group endsEach
            });
            
            it.endsAll(() -> {
                // inner group endsAll
            });
            
            it.should("inner group test 1", () -> {
                // inner group test 1
            });
            
            it.should("inner group test 2", () -> {
                // inner group test 2
            });
        });
    });
}}
```

This specification consists of two groups, named 'outer' and 'inner'.
Each group has one of every type of fixture, and two test cases.
Because they are nested, we say that 'inner' is a *child group* of 'outer';
conversely, 'outer' is a *parent group* of 'inner'.

The first, obvious rule, is that child groups have no influence on the
execution of parent groups.
So, the outer group will execute as follows:

* outer group beginsAll
* outer group beginsEach
* outer group test 1
* outer group endsEach
* outer group beginsEach
* outer group test 2
* outer group endsEach
* outer group endsAll

In case of nested groups, the rule is fairly simple:

> When a group's test cases are executed,
> all of the ancestor groups fixtures are executed as well.

The only difference is the order in which they are executed:

* `beginsAll`/`beginsEach` execute in 'outside-in' order -
meaning, from the top-most parent down to the child group being executed
* `endsAll`/`endsEach` execute in reverse order, so 'inside-out' -
starting from the child group being executed up to the top-most parent group

So, the execution of the 'inner' group will look like the following:

* outer group beginsAll
* inner group beginsAll
* outer group beginsEach
* inner group beginsEach
* inner group test 1
* inner group endsEach
* outer group endsEach
* outer group beginsEach
* inner group beginsEach
* inner group test 2
* inner group endsEach
* outer group endsEach
* inner group endsAll
* outer group endsAll

##### Difference between RSpec and Jasmine

If you have experience with similar testing tools in other languages,
like RSec and Jasmine, you might notice that Specnaz behaves differently here.

In RSpec and Jasmine, the child groups do not execute their parent's
`beforeAll`/`afterAll` callbacks again.
They are executed only once, when that group's test cases are ran
(the `afterAll` ones when the last child group is finished).

Both ways of executing the fixtures make sense, and both are useful under
different circumstances.
They are also equivalent - you can express one in terms of the other.

The reason for doing it this way is that Specnaz was designed for Java.
And in Java, while it's fairly easy to go from the Specnaz way to the
RSpec/Jasmine way, it's much harder to go in the opposite direction.
Let me illustrate.

Let's say we have the following Jasmine spec:

```javascript
describe("outer group", function () {
    beforeAll(function () {
        // beforeAll setup...
    });
    
    it("outer test", function () {
        // outer group test
    });
    
    describe("inner group", function () {
        it("inner test", function () {
            // inner group test
        });
    });
});
```

The `beforeAll` setup will run only once, right before the `outer test`,
and not again before `inner test`.

If we wanted to achieve the same in Specnaz, it would be fairly straightforward:

```java
public class BeforeAllOnceSpec extends SpecnazJUnit {
    boolean setupRan = false;

    {
        describes("outer group", it -> {
            it.beginsAll(() -> {
                if (!setupRan) {
                    setupRan = true;
                    // beforeAll setup...
                }
            });

            it.should("outer test", () -> {
                // outer group test
            });

            it.describes("inner group", () -> {
                it.should("inner test", () -> {
                    // inner group test
                });
            });
        });
    }
}
```

However, imagine the opposite - that we wanted to have the Specnaz
behavior in the Jasmine spec.
It would look something like the following:

```javascript
describe("outer group", function () {
    function init() {
        // beforeAll setup...
    }
    
    beforeAll(function () {
        init();
    });

    it("outer test", function () {
        // outer group test
    });
    
    describe("inner group", function () {
        beforeAll(function () {
            init();
        });

        it("inner test", function () {
            // inner group test
        });
    });
});
```

While this is easy to achieve in JavaScript, in Java, we don't have nested
methods, which makes it a lot more cumbersome.
We can use objects and lambda expressions to simulate JavaScript's local
functions, but that is a lot more awkward and less readable,
both for the definition and then later for the usage.
We can use instance methods on the test class itself,
but that means we can't define them on the level that they are actually used,
and they won't have access to the variables of the closure.

For these reasons, Specnaz changes the traditional behavior of the
`beginsAll` and `endsAll` fixtures.

## Using `Box`es

One awkward part of writing specs in Java is the limitation that inside
the lambda expression, you can only reference final and effectively
final variables of the enclosing scope.
We often want to write something like this:

```java
public class StackSpec extends SpecnazJUnit {{
    Stack<Integer> stack;
    
    describes("A Stack", it -> {
        it.beginsEach(() -> {
            stack = new Stack<>(); // not valid in Java!
        });
        
        // rest of the spec here...
    });
}}
```

We can work around this by using instance variables of the test class,
like we have been in these examples.
However, that means we can't use the "double braces" syntax,
meaning we need to have the code indented an extra level, and, even worse,
we have to define all of the local test variables at the same level,
instead of doing it where they are actually used, making our tests less readable.

To help with this use-case, Specnaz ships with a utility class -
`org.specnaz.utils.Box`, which is a very simple wrapper around any value,
which is kept in a `public` field, called `$`.
This way, the reference to the `Box` class is final,
but the value of the field can be mutated at will.

Here is a simple example:

```java
public class StackSpec extends SpecnazJUnit {{
    Box<Stack<Integer>> stack = Box.emptyBox();

    describes("A Stack", it -> {
        it.beginsEach(() -> {
            stack.$ = new Stack<>();
        });

        it.should("be empty when first created", () -> {
            assertThat(stack.$).isEmpty();
        });
    });
}}
```

For readability, and to leverage Java's type inference, `Box` has a
private constructor - you create instances of it using static factory
methods, `emptyBox` and `boxWith`.

There are also equivalent classes for boxing primitive values
(`int`, `bool`, `long` etc.), named `IntBox`, `BoolBox`, `LongBox` etc.

## Using Specnaz in other JVM languages

### Kotlin

Specnaz has first-class support for writing specs in Kotlin.

#### Using native Java classes

You can use the Java Specnaz classes from Kotlin without any problems:

```kotlin
class StackJavaSpec : SpecnazJUnit() { init {
    describes("A Stack") {
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
                Assert.assertEquals(2, stack.size)
            }

            it.should("have 20 as the top element") {
                Assert.assertEquals(20, stack.peek())
            }
        }
    }
}}
```

As you can see, the Kotlin test is very similar to the Java one, but looks
a little better - there is less punctuation (semicolons are gone, the parenthesis
can be closed after the first argument or omitted entirely, as Kotlin allows
you to give the lambda expression outside of them if it's the last parameter)
and you don't have to name the parameter to the lambda given as the second
argument to the `describes` method - Kotlin automatically binds it to the
`it` variable.
Kotlin also doesn't have the limitation that variables referenced in closures
must be final or effectively final, which means you don't have to use the
`Box` classes.

#### Using the Kotlin bindings

Specnaz also includes a separate subproject that provides classes
tailor-made to be used from Kotlin.

Here is an example of using them:

```kotlin
class StackKotlinSpec : SpecnazKotlinJUnit("A Stack", {
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
            Assert.assertEquals(2, stack.size)
        }

        it.should("have 20 as the top element") {
            Assert.assertEquals(20, stack.peek())
        }
    }
})
```

They provide the following advantages:

* The JUnit helper class, `org.specnaz.kotlin.junit.SpecnazKotlinJUnit`,
implements the Kotlin analog of the `Specnaz` interface,
`org.specnaz.kotlin.SpecnazKotlin`, and calls the `describes` method
from that interface in its primary constructor.
Which means you can save one level of indentation when you don't need
to extend a particular superclass.
* The Kotlin equivalent of `SpecBuilder`, `org.specnaz.kotlin.KotlinSpecBuilder`,
defines its own versions of all of the spec building methods like `should`,
`beginsEach`, `describes` etc., with signatures using the Kotlin function types.
This is better in 2 ways:
    * the IDE support is better (for example, Intellij lists each
    method of the `SpecBuilder` twice in the auto-completion menu,
    once with the lambda function and once with the `TestClosure` instance,
    which is irritating)
    * you get better type-safety, as all of the redefined functions
    (except `describes`) take a `Nothing?` (Kotlin's equivalent of Java's
    `Void`) as their first argument, which means that if you didn't override
    the default name of the lambda parameter (`it`), the type system will
    not allow you to do illegal things when building the spec
    (like nesting `should` calls).

If your test class has to extend a particular class, you can still get
the last two benefits by using the `org.specnaz.kotlin.junit.SpecnazKotlinJUnitRunner`
JUnit `Runner` and implementing `SpecnazKotlin`:

```kotlin
@RunWith(SpecnazKotlinJUnitRunner::class)
class StackKotlinSpec : SpecCommon(), SpecnazKotlin { init {
    describes("A Stack") {
        // spec body...
    }
}}
```

#### The `Deferred` helper

One irritating thing about writing tests in Kotlin is that the compiler
checks both for `null` values and forces variables to be always initialized
before being used. That's of course great in production code, but gets
tedious in tests. For example:

```kotlin
class KotlinSpec : SpecnazKotlinJUnit("A spec", {
    var someDomainClass: MyDomainClass
    
    it.beginsEach {
        someDomainClass = myDomainOperation()
    }
    
    it.should("do something properly") {
        Assert.assertTrue(someDomainClass.something()); // does not compile!
    }
})
```

The above snippet will not work - the compiler will complain that
`someDomainClass` must be initialized before being used.
We could use Kotlin's `lazyinit`, or the non-`null` `Delegate`,
but the problem is those only work for class attributes, not local variables.

Because of that, Specnaz ships with a simple helper class,
`org.specnaz.kotlin.utils.Deferred`.
You use it like this:

```kotlin
class KotlinSpec : SpecnazKotlinJUnit("A spec", {
    val someDomainClass = Deferred<MyDomainClass>()

    it.beginsEach {
        someDomainClass.v = myDomainOperation()
    }
    
    it.should("do something properly") {
        Assert.assertTrue(someDomainClass.v.something());
    }
})
```

The value of the appropriate type will be stored in the `v`
public instance variable of the `Deferred` class, and you can use it
without explicitly initializing it first, which means the above code will
compile.

If you're willing to use inheritance (`Deferred` is an open class),
you can make your tests read even better:

```kotlin
class KotlinSpec : SpecnazKotlinJUnit("A spec", {
    val someDomainClass = object : Deferred<MyDomainClass>() {
        val something: Boolean get() = v.something
    }

    it.beginsEach {
        someDomainClass.v = myDomainOperation()
    }
    
    it.should("do something properly") {
        Assert.assertTrue(someDomainClass.something()); // notice no '.v'
    }
})
```

### Groovy

You can use the Specnaz from Groovy without any special bindings.
Check out the [Readme file of the Groovy examples](../src/examples/specnaz-groovy-examples/Readme.md)
for more info.

# Extending Specnaz

Specnaz allows you to extend it by creating your own DSL
(Domain-Specific Language) for tests.
In fact, the `begins`/`ends`/`should` DSL that you've seen used in this
documentation is not built-in into the framework -
it's created using the core Specnaz API, which the library clients can use as well.

Take a look at the [specnaz-custom-dsl-example project Readme file](../src/examples/specnaz-custom-dsl-example/Readme.md)
for documentation and a working example of how to extend Specnaz.
