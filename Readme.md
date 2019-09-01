# Specnaz

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Download](https://api.bintray.com/packages/skinny85/maven-repo/Specnaz/images/download.svg)](https://bintray.com/skinny85/maven-repo/Specnaz/_latestVersion)
[![Build Status](https://travis-ci.org/skinny85/specnaz.svg?branch=develop)](https://travis-ci.org/skinny85/specnaz)

### Library for writing beautiful, [RSpec](http://rspec.info/)/[Jasmine](http://jasmine.github.io/)/[Mocha](https://mochajs.org/)/[Jest](https://facebook.github.io/jest/)-style specifications in Java, [Kotlin](https://kotlinlang.org/) and [Groovy](http://www.groovy-lang.org/)

![Specnaz logo](docs/img/specnaz-logo.png)

### First example

```java
import org.specnaz.Specnaz;
import org.specnaz.junit.SpecnazJUnit;
import org.specnaz.junit.SpecnazJUnitRunner;
import org.junit.runner.RunWith;
import org.junit.Assert;
import java.util.Stack;

// You can also say:
// public class StackSpec extends SpecnazJUnit {{
// - in that case, you can drop the @RunWith annotation
@RunWith(SpecnazJUnitRunner.class)
public class StackSpec implements Specnaz {{
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

This is how the above test looks like when executed from an IDE:

![StackSpec result](docs/img/stack-spec-result.png)

### Notable features

#### Fully compatible with JUnit

Specnaz is 100% compatible with the JUnit API,
which means it works out of the box with existing IDEs and build tools,
without requiring any additional plugins.

It also means Specnaz specs and your existing JUnit tests can coexist next to each other -
you're not forced to do any migration when starting to use Specnaz.

Specnaz also supports the [JUnit Rules API](https://github.com/junit-team/junit4/wiki/rules) natively,
which means you can leverage existing third-party Rules when writing Specnaz specs.
Here is an example of integrating with [Mockito](http://site.mockito.org/):

```java
import org.specnaz.junit.SpecnazJUnit;
import org.specnaz.junit.rules.Rule;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.when;

public class MockitoExampleSpec extends SpecnazJUnit {
    public Rule<MockitoRule> mockitoRule = Rule.of(() -> MockitoJUnit.rule());

    @Mock
    private List<Integer> listMock;

    {
        describes("Using the JUnit Mockito Rule in Specnaz", it -> {
            it.should("initialize fields annotated with @Mock", () -> {
                when(listMock.get(0)).thenReturn(400 + 56);

                assertEquals(456, (int) listMock.get(0));
            });
        });
    }
}
```

See [here](docs/reference-manual.md#junit-rules) for more information about this feature.

#### Built-in parametrized test capabilities

Specnaz allows you to easily create concise and type-safe parametrized tests.
Example:

```java
import org.specnaz.junit.SpecnazJUnitRunner;
import org.specnaz.params.SpecnazParams;
import org.specnaz.params.junit.SpecnazParamsJUnit;
import org.junit.runner.RunWith;

import static org.specnaz.params.Params3.p3;

// You can also say:
// public class ParametrizedSpec extends SpecnazParamsJUnit {{
// - in that case, you can skip the @RunWith annotation
@RunWith(SpecnazJUnitRunner.class)
public class ParametrizedSpec implements SpecnazParams {{
    describes("A parametrized spec", it -> {
        it.should("confirm that %1 + %2 = %3", (Integer a, Integer b, Integer c) -> {
            assertThat(a + b).isEqualTo(c);
        }).provided(
                p3(1, 2, 3),
                p3(4, 4, 8),
                p3(-3, 3, 0),
                p3(Integer.MAX_VALUE, 1, Integer.MIN_VALUE)
        );
    });
}}
```

See [here](docs/reference-manual.md#parametrized-test-support) for more information on writing parametrized specs.

#### First-class support for the Kotlin language

Specnaz supports writing specs in idiomatic Kotlin:

```kotlin
import org.specnaz.kotlin.junit.SpecnazKotlinJUnit
import org.junit.Assert
import java.util.Stack

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
See [here](docs/reference-manual.md#kotlin) for more information.

#### TestNG support

Specnaz also supports [TestNG](https://testng.org) as the test execution engine. Example:

```java
import org.specnaz.testng.SpecnazFactoryTestNG;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.Stack;

@Test
public class StackSpec implements SpecnazFactoryTestNG {{
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
                Assert.assertEquals(stack.size(), 2);
            });

            it.should("have 20 as the top element", () -> {
                Assert.assertEquals((int) stack.peek(), 20);
            });
        });
    });
}}
```

Similarly like with JUnit, you can freely mix existing TestNG tests and Specnaz specs -
you don't have to migrate your entire test suite to start using Specnaz.

##### Further reading

Check out the [reference manual](docs/reference-manual.md) for more in-depth documentation.
There is also an [examples directory](src/examples) with code samples.

I've also written [a post on my blog](http://endoflineblog.com/specnaz-my-java-testing-library)
demonstrating how you can structure your tests with Specnaz.

### Getting Specnaz

Specnaz is available from the [JCenter](https://bintray.com/bintray/jcenter) Maven repository.

* Group ID: `org.specnaz`
* Latest version: `1.4.1`

The Artifact ID depends on the language and testing framework you want to use:

| Programming language | Testing framework | Artifact ID             |
|----------------------|-------------------|-------------------------|
| Java (or Groovy)     | JUnit             | `specnaz-junit`         |
| Kotlin               | JUnit             | `specnaz-kotlin-junit`  |
| Java (or Groovy)     | TestNG            | `specnaz-testng`        |
| Kotlin               | TestNG            | `specnaz-kotlin-testng` |

**Note**: the Specnaz libraries don't depend on their testing frameworks
(neither JUnit, nor TestNG), and also not on the Kotlin runtime in the case of the Kotlin libraries.
This is in order to prevent version conflicts.
Make sure to add the appropriate dependencies on JUnit or TestNG 
(and the Kotlin runtime if applicable)
if your project doesn't include them already.

###### Example Maven settings

```xml
<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>jcenter</id>
        <name>jcenter</name>
        <url>http://jcenter.bintray.com</url>
    </repository>
</repositories>

<!-- ... -->

<dependencies>
    <!-- ... -->

    <!-- For JUnit: -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    <!-- ...in Java (or Groovy): -->
    <dependency>
        <groupId>org.specnaz</groupId>
        <artifactId>specnaz-junit</artifactId>
        <version>1.4.1</version>
        <scope>test</scope>
    </dependency>
    <!-- ...in Kotlin: -->
    <dependency>
        <groupId>org.specnaz</groupId>
        <artifactId>specnaz-kotlin-junit</artifactId>
        <version>1.4.1</version>
        <scope>test</scope>
    </dependency>

    <!-- For TestNG: -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>6.14.3</version>
        <scope>test</scope>
    </dependency>
    <!-- ...in Java (or Groovy): -->
    <dependency>
        <groupId>org.specnaz</groupId>
        <artifactId>specnaz-testng</artifactId>
        <version>1.4.1</version>
        <scope>test</scope>
    </dependency>
    <!-- ...in Kotlin: -->
    <dependency>
        <groupId>org.specnaz</groupId>
        <artifactId>specnaz-kotlin-testng</artifactId>
        <version>1.4.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

###### Example Gradle settings

```groovy
repositories {
    jcenter()
}

dependencies {
    // ...

    // For JUnit:
    testCompile "junit:junit:4.12"
    // ...in Java (or Groovy):
    testCompile "org.specnaz:specnaz-junit:1.4.1"
    // ...in Kotlin:
    testCompile "org.specnaz:specnaz-kotlin-junit:1.4.1"

    // For TestNG:
    testCompile "org.testng:testng:6.14.3"
    // ...in Java (or Groovy):
    testCompile "org.specnaz:specnaz-testng:1.4.1"
    // ...in Kotlin:
    testCompile "org.specnaz:specnaz-kotlin-testng:1.4.1"
}
```

### License

Specnaz is open-source software, released under the Apache v2 license.
See [the License file](License.txt) for details.
