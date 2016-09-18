Extending Specnaz
=================

Specnaz was built with extensibility in mind.
This means that you can create your own Domain-Specific Languages
(DSLs) for writing tests if the default one does not fit your needs.

This project shows a simple example of implementing a Given-When-Then test DSL.
The code is in `src/main/java/org/specnaz/examples/custom_dsl/given_when_then`,
and `src/test/java` shows a sample usage.

The process of extending Specnaz consists of several easy steps:

### Step 1: create an interface

The first thing that you need to do is to create your core interface.
This will determine what will be the entry method of your DSL.

This interface needs to extends `org.specnaz.core.SpecnazCoreDsl`,
and call its `specification` method in the entry method body.
For our Given-When-Then example, it looks like this:

```
public interface SpecnazGivenWhenThen extends SpecnazCoreDsl {
    default void given(String description, Consumer<GivenBuilder> closure) {
        specification("Given " + description, coreDslBuilder -> {
            closure.accept(new GivenWhenThenBuilder(coreDslBuilder));
        });
    }
}
```

(You have to use `SpecnazCoreDsl` and not `Specnaz`, because the latter
adds the word 'should' to each test description, which is probably not what you want
in your custom DSL.)

If you want to dissuade your clients from using the inherited `specification`
method, and use your DSL entry one instead, you might want to override
that method and mark it with the `@Deprecated` annotation.
You can even go as far as making your override always throw an exception:

```
public interface SpecnazGivenWhenThen extends SpecnazCoreDsl {
    @Override
    @Deprecated
    default void specification(String description, Consumer<CoreDslBuilder> specClosure) {
        throw new UnsupportedOperationException("Use given(description, closure) instead");
    }
}
```

However, note that if you decide to do that, you need to use the `super`
syntax when calling it in your entry method:

```
public interface SpecnazGivenWhenThen extends SpecnazCoreDsl {
    default void given(String description, Consumer<GivenBuilder> closure) {
        SpecnazCoreDsl.super.specification("Given " + description, coreDslBuilder -> {
            closure.accept(new GivenWhenThenBuilder(coreDslBuilder));
        });
    }
}
```

### Step 2: define your Builder API

Now, you need to decide what is the Builder that is the parameter for the
specification closure in your entry method
(`GivenBuilder` in the example code above).
This will determine the shape of your DSL.

As an example, in the Given-When-Then DSL, we decided on an API like this:

```
public interface GivenBuilder {
    default void given(String description, Runnable closure) {
        given(description, () -> {}, closure);
    }

    void given(String description, TestClosure action, Runnable closure);

    void when(String description, TestClosure action, Consumer<ThensBuilder> thens);
}

public interface ThensBuilder {
    void then(String description, TestClosure testBody);
}
```

Of course, this is just an example - you can design your DSL any way you want.

### Step 3: write your Builder wrapper

After the API is created, you need to actually implement it.
The implementation should delegate all of the logic to the
`org.specnaz.core.CoreDSlBuilder` interface
(it's basically the same as `org.specnaz.SpecBuilder`, except the names
are a little different).
In our `GivenWhenThenBuilder` from above, this looks like this:

```
public class GivenWhenThenBuilder implements GivenBuilder, ThensBuilder {
    private final CoreDslBuilder coreDslBuilder;

    public GivenWhenThenBuilder(CoreDslBuilder coreDslBuilder) {
        this.coreDslBuilder = coreDslBuilder;
    }

    @Override
    public void given(String description, TestClosure action, Runnable closure) {
        coreDslBuilder.subSpecification(description, () -> {
            coreDslBuilder.beforeAll(action);

            closure.run();
        });
    }

    @Override
    public void when(String description, TestClosure action, Consumer<ThensBuilder> thens) {
        coreDslBuilder.subSpecification("when " + description, () -> {
            coreDslBuilder.beforeAll(action);

            thens.accept(this);
        });
    }

    @Override
    public void then(String description, TestClosure testBody) {
        coreDslBuilder.test("then " + description, testBody);
    }
}
```

### Step 4 (optional): provide a JUnit helper

You can provide a helper class for your clients, similar to
`org.specnaz.junit.SpecnazJUnit`, which specifies your interface and the
JUnit Runner with the `@RunWith` annotation:

```
@RunWith(SpecnazCoreDslJUnitRunner.class)
public abstract class MyDslJUnitHelper implements MyDslInterface {
}
```

### Step 5: write your tests!

And... that's it! You can now use your DSL to write specifications.
For example, using our Given-When-Then DSL looks something like this:

```
@RunWith(SpecnazCoreDslJUnitRunner.class)
public class GivenWhenThenStackSpec implements SpecnazGivenWhenThen {
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
    }
}
```

If you did Step 4 above, you can use your JUnit helper to avoid specifying
the Runner every time.

Doing a standalone extension
----------------------------

While the above instructions allow you to create your own DSL,
the result has one flaw.
Your clients will still see the `specification` method from the
`SpecnazCoreDsl` interface you are extending.
You can mark it as `@Deprecated` and override the implementation to always
throw an exception, however it will still be present.

There is a way to get rid of that problem, but it requires a little bit of extra code.
If you can't stand the extra method in your interface, read on.

We will be using the same Given-When-Then DSL that we developed previously.
The example code is in
[here](src/main/java/org.specnaz/examples/custom_dsl/given_when_then/standalone),
and [here](src/test/java/standalone) is an example test using the standalone extension.

### Step 1: create an interface

Similarly to before, the first thing that you need to do is define your
entry point interface.
In the case of our Given-When-Then example, it will be the same as before,
except it won't extend `SpecnazCoreDsl` anymore:

```java
public interface SpecnazGivenWhenThenStandalone {
    default void given(String description, Consumer<GivenBuilder> specClosure) {
        // implementation will be done in Step 2 below...
    }
}
```

### Step 2: implement the entry method

Now, we need to implement our entry method.
The implementation has to to pass the arguments it receives
(the description and the spec-defining closure, in our case)
somehow outside of the method, so that they're accessible to the JUnit runner
(which is the next step).
There are multiple ways to achieve that, but probably the simplest is to
store it in a static field of a dedicated class.
Here is an example implementation:

```java
public class GivenWhenThenRegistry {
    private static final Map<SpecnazGivenWhenThenStandalone, GivenWhenThenCoreWrapper> STORE =
        new IdentityHashMap<>();

    public static void add(SpecnazGivenWhenThenStandalone specInstance, String description,
                           Consumer<GivenBuilder> specClosure) throws IllegalArgumentException {
        GivenWhenThenCoreWrapper prev = STORE.putIfAbsent(specInstance,
                new GivenWhenThenCoreWrapper(description, specClosure));
        if (prev != null)
            throw new IllegalArgumentException("Instance '" + specInstance +
                "' already registered");
    }

    static GivenWhenThenCoreWrapper get(SpecnazGivenWhenThenStandalone specInstance)
            throws IllegalArgumentException {
        GivenWhenThenCoreWrapper ret = STORE.get(specInstance);
        if (ret == null)
            throw new IllegalArgumentException("Instance '" + specInstance +
                "' was never registered");
        return ret;
    }
}
```

The `GivenWhenThenCoreWrapper` is a simple class that serves 2 purposes:

- it gives us a container to store all of the arguments passed to our entry method
- it implements `SpecnazCoreDsl`, so we can express our custom DSL in terms of the core one

Here is the implementation for the Given-When-Then example:

```java
class GivenWhenThenCoreWrapper implements SpecnazCoreDsl {
    private final String description;
    private final Consumer<GivenBuilder> specClosure;

    public GivenWhenThenCoreWrapper(String description, Consumer<GivenBuilder> specClosure) {
        this.description = description;
        this.specClosure = specClosure;
    }

    public void callSpecification() {
        specification(description, coreDslBuilder -> {
            specClosure.accept(new GivenWhenThenBuilder(coreDslBuilder));
        });
    }
}
```

Using this kind of store, our entry method might look something like the following:

```java
public interface SpecnazGivenWhenThenStandalone {
    default void given(String description, Consumer<GivenBuilder> specClosure) {
        try {
            GivenWhenThenRegistry.add(this, "Given " + description, specClosure);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("SpecnazGivenWhenThenStandalone.given() " +
                    "was called multiple times in the no-argument constructor of " +
                     this.getClass().getSimpleName());
        }
    }
}
```

### Step 3: implement a JUnit runner

Now, in order to run the tests implementing your custom interface,
you can't just use `SpecnazCoreDslJUnitRunner` -
it has no idea about your new DSL!
For that reason, you need to create your own JUnit runner.
Fortunately, it's very simple:

- you declare a class extending `org.junit.runner.Runner`
- the entry point is a `public` constructor accepting a `Class<?>`
as the only parameter. The implementation should:
    * instantiate the class implementing your custom interface through reflection,
    using its public no-argument constructor.
    Specnaz has a helper, `org.specnaz.junit.utils.Utils#instantiateTestClass`,
    that might be useful in this case
    * the class should call your entry method in the constructor, so now,
    we retrieve the arguments saved in the store
    (or through whatever means you decided to save them in your entry method)
    * using the retrieved arguments, we translate our DSL to the
    `SpecnazCoreDsl` concepts, so that Specnaz can execute our tests
    (we can use a wrapper class for this, as shown above)
    * finally, we instantiate `SpecnazCoreDslJUnitRunner` using its
    `(String, Object)` constructor, passing the name of the test class
    and whatever object instance that we called `SpecnazCoreDsl#specification` on
- we delegate to the instance of `SpecnazCoreDslJUnitRunner`
created in the constructor to implement the required `Runner` methods,
`getDescription` and `run`

Here's the code for our Given-When-Then example:

```java
public class GivenWhenThenStandaloneJUnitRunner extends Runner {
    private final SpecnazCoreDslJUnitRunner coreDslJUnitRunner;

    public GivenWhenThenStandaloneJUnitRunner(Class<?> classs) {
        SpecnazGivenWhenThenStandalone specInstance = Utils.instantiateTestClass(
                classs, SpecnazGivenWhenThenStandalone.class);
        GivenWhenThenCoreWrapper coreWrapper;
        try {
            coreWrapper = GivenWhenThenRegistry.get(specInstance);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("SpecnazGivenWhenThenStandalone.given() " +
                    "was not called in the no-argument constructor of " +
                     classs.getSimpleName());
        }
        coreWrapper.callSpecification();
        coreDslJUnitRunner = new SpecnazCoreDslJUnitRunner(classs.getSimpleName(),
            coreWrapper);
    }

    @Override
    public Description getDescription() {
        return coreDslJUnitRunner.getDescription();
    }

    @Override
    public void run(RunNotifier notifier) {
        coreDslJUnitRunner.run(notifier);
    }
}
```

### Step 4: write your Builder wrapper
### Step 5 (optional): provide a JUnit helper

These steps are exactly the same as the equally named Steps 3 and 4
from the non-standalone instructions above.
The Give-When-Then example uses exactly the same code for the `GivenWhenThenBuilder`
for both variants (see above for the listing).

### Step 6: write your tests!

And... that's it! You can now use your custom DSL to write specifications,
and the clients will have no knowledge that it's implemented in terms of
`SpecnazCoreDsl#specification`.
Here is an example of using the Given-When-Then DSL:

```java
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
    }
}
```
