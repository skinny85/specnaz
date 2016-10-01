![specnaz logo](docs/img/specnaz-logo.png)

Specnaz is a Java library for writing tests that are radically different in shape than ones you get when using traditional xUnit-type frameworks like JUnit.
It borrows heavily from such tools as [RSpec](http://rspec.info/) and [Jasmine](http://jasmine.github.io/),
and uses Java 8 features like lambda expressions and default methods to achieve concise, readable and [DRY](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself) tests
(also often called "specifications", to highlight how different they are from xUnit-style "tests").

It also supports other JVM languages, mainly [Kotlin](https://kotlinlang.org/) and [Groovy](http://www.groovy-lang.org/).

#### First example

```java
import java.util.Stack;
import org.junit.Assert;
import org.specnaz.junit.SpecnazJUnit;

public class StackSpec extends SpecnazJUnit {{
    describes("A Stack", it -> {
        Stack<Integer> stack = new Stack<>();

        it.should("be empty when first created", () -> {
            Assert.assertTrue(stack.isEmpty());
        });

        it.endsEach(() -> {
            stack.clear();
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

Specnaz is fully compatible with the JUnit API,
which means it should work out of the box with existing Java project infrastructure -
IDEs, build tools etc.
It also means that you are not forced to migrate your entire test suite to Specnaz when you start using it -
your existing tests and the Specnaz specifications can exist next to each other without a problem, and you can migrate (or not) at your own pace.

Specnaz does not force your test classes to inherit from a particular superclass - `SpecnazJUnit` is only a convenience class to save you some typing.
Your test classes are free to extend any class you want - the only requirement is that they must implement the `Specnaz` interface (which `SpecnazJUnit` does).
It's an interface with one default method (`describes`, seen above), so you don't have to write any additional code when implementing it directly.
In cases when you're extending a class other than `SpecnazJUnit`, but still plan on using JUnit as the test harness, you have to remember to provide the Specnaz JUnit runner with the `@RunWith` annotation:

```java
import java.util.Stack;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.specnaz.Specnaz;
import org.specnaz.junit.SpecnazJUnitRunner;

@RunWith(SpecnazJUnitRunner.class)
public class StackSpec implements Specnaz {{
    describes("A Stack", it -> {
        // rest of the code is exactly the same as above
        // ...
```

Specnaz also doesn't force you to use any particular assertion library - the examples all use the standard JUnit ones, but you are free to use [Hamcrest](http://hamcrest.org/), [AssertJ](http://joel-costigliola.github.io/assertj/), or any other assertion library you please.

Specnaz also supports Kotlin as a first-class language:

```kotlin
import java.util.Stack
import org.junit.Assert
import org.specnaz.kotlin.junit.SpecnazKotlinJUnit

class StackKotlinSpec : SpecnazKotlinJUnit("A Stack", {
    var stack = Stack<Int>()

    it.should("be empty when first created") {
        Assert.assertTrue(stack.isEmpty())
    }

    it.endsEach {
        stack = Stack()
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

Same as in Java, when writing specs in Kotlin, you are not forced to inherit from any particular class:

```kotlin
import java.util.Stack
import org.junit.Assert
import org.junit.runner.RunWith
import org.specnaz.kotlin.SpecnazKotlin
import org.specnaz.kotlin.junit.SpecnazKotlinJUnitRunner

@RunWith(SpecnazKotlinJUnitRunner::class)
class StackKotlinSpec : SomeCustomClass(), SpecnazKotlin { init {
    describes("A Stack") {
        // rest of the code is exactly the same as above
        // ...
```

#### More information

Check out the [reference manual](docs/reference-manual.md) for more documentation and examples.

#### Getting Specnaz

Specnaz is available from the [Bintray](https://bintray.com/) Maven repository.
The group ID is always `org.specnaz`, while the artifact ID depends on the language and testing framework you want to use
(Specnaz currently supports only JUnit, but there are plans to add support for others in the future):

| Programming language | Testing framework | Artifact ID            |
|----------------------|-------------------|------------------------|
| Java                 | JUnit             | `specnaz-junit`        |
| Kotlin               | JUnit             | `specnaz-kotlin-junit` |

The repository URL is: `http://dl.bintray.com/skinny85/maven-repo`.

###### Example Maven settings

```xml
<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-specnaz</id>
        <name>bintray-specnaz</name>
        <url>http://dl.bintray.com/skinny85/maven-repo</url>
    </repository>
</repositories>

<!-- ... -->

<dependenies>
    <dependency>
        <groupId>org.specnaz</groupId>
        <artifactId>specnaz-junit</artifactId>
        <version>1.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

###### Example Gradle settings

```groovy
repositories {
    maven {
        url 'http://dl.bintray.com/skinny85/maven-repo'
    }
}

dependencies {
    // ...
    testCompile "org.specnaz:specnaz-kotlin-junit:1.0"
}
```

###### Note about JUnit

It's important to know that Specnaz itself does not depend on JUnit directly.
This is by design, in order to prevent version conflicts -
the vast majority of existing Java projects already depend on a particular version of JUnit.
If yours doesn't, remember to add it
(check [here](https://mvnrepository.com/artifact/junit/junit) for the latest version and needed coordinates).

#### License

Specnaz is 100% open source, and released under the Apache v2 license.
See [the license file](license.txt) for details.

#### About the author

Specnaz was written and is maintained by Adam Ruka.

I have a blog where I write about various programming topics (like Specnaz) [here](http://endoflineblog.com).

If you would like to, follow me on Twitter [here](https://twitter.com/adam_ruka).
