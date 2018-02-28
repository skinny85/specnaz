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
import org.junit.runner.RunWith;

import org.specnaz.junit.SpecnazJUnitRunner;
import org.specnaz.params.SpecnazParams;
import org.specnaz.params.junit.SpecnazParamsJUnit;
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
See [here](docs/reference-manual.md#kotlin) for more information.

##### Further reading

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
