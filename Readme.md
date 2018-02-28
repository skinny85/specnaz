# Specnaz

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Download](https://api.bintray.com/packages/skinny85/maven-repo/Specnaz/images/download.svg)](https://bintray.com/skinny85/maven-repo/Specnaz/_latestVersion)
[![Build Status](https://travis-ci.org/skinny85/specnaz.svg?branch=develop)](https://travis-ci.org/skinny85/specnaz)

![Specnaz logo](docs/img/specnaz-logo.png)

### Library for writing beautiful, [RSpec](http://rspec.info/) or [Jasmine](http://jasmine.github.io/)-style specifications in Java, [Kotlin](https://kotlinlang.org/) and [Groovy](http://www.groovy-lang.org/)

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

Notable features:

* Fully compatible with the JUnit API (IDEs, build tools etc.).
    It means Specnaz specs and your existing JUnit tests can coexist next to each other
    (you're not forced to do any migration when starting to use Specnaz).
* Test classes can inherit from any superclass - you just need to specify the JUnit Runner in that case:
    ```java
    import org.junit.runner.RunWith;
    import org.specnaz.Specnaz;
    import org.specnaz.junit.SpecnazJUnitRunner;
    
    @RunWith(SpecnazJUnitRunner.class)
    public class StackSpec extends MyClass implements Specnaz {{
        describes("A Stack", it -> {
            // rest of the code exactly the same as above..
    ``` 
* Doesn't force you to use any particular assertion library -
    the examples all use the standard JUnit ones, but you are free to use
    [Hamcrest](http://hamcrest.org/), [AssertJ](http://joel-costigliola.github.io/assertj/),
    or any other assertion library you please.
* First-class support for the Kotlin language:
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

Check out the [reference manual](docs/reference-manual.md) for more in-depth documentation.
There is also an [examples directory](src/examples) with code samples.

I've also written [a post on my blog](http://endoflineblog.com/specnaz-my-java-testing-library)
demonstrating how you can structure your tests with Specnaz.

### Getting Specnaz

Specnaz is available from the [Bintray](https://bintray.com/) Maven repository.

* Group ID: `org.specnaz`
* Latest version: `1.2`

The Artifact ID depends on the language and testing framework you want to use
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
        <version>1.2</version>
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
    testCompile "org.specnaz:specnaz-kotlin-junit:1.2"
}
```

###### Note about JUnit

It's important to know that Specnaz itself does not depend on JUnit directly.
This is by design, in order to prevent version conflicts -
the vast majority of existing Java projects already depend on a particular version of JUnit.
If yours doesn't, remember to add it
(check [here](https://mvnrepository.com/artifact/junit/junit) for the latest version and needed coordinates).

### License

Specnaz is open-source software, released under the Apache v2 license.
See [the License file](License.txt) for details.
