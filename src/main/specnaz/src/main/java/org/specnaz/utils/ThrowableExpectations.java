package org.specnaz.utils;

import org.specnaz.SpecBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static java.lang.String.format;

/**
 * The class returned from {@link SpecBuilder#shouldThrow}
 * that allows you specify assertions on the Exception thrown in the test.
 * <p>
 * By default, the only thing checked is that the class of the thrown Exception matches the class passed to
 * {@link SpecBuilder#shouldThrow}.
 * By calling methods on the instance of this class returned from
 * {@link SpecBuilder#shouldThrow},
 * you can refine the assertions further, to check, for example,
 * the message, or the cause, of the thrown Exception.
 * All methods of this class return either {@code this},
 * or a different instance of this class (they're an example of a
 * <a href="https://martinfowler.com/bliki/FluentInterface.html">Fluent Interface</a>),
 * so chaining them is easy.
 * <p>
 * Example:
 * <pre>
 * {@code
 * it.shouldThrow(RuntimeException.class, "when throwing some exceptions", () -> {
 *         try {
 *             throw new IOException("some message");
 *         } catch (IOException e) {
 *             throw new RuntimeException(e);
 *         }
 *     }).withMessageContaining("some message").withCauseOfType(IOException.class).withMessage("some message");
 * }
 * </pre>
 *
 * @see #withAnyMessage
 * @see #withMessage
 * @see #withMessageStartingWith
 * @see #withMessageContaining
 * @see #withoutMessage
 * @see #withCause
 * @see #withCauseOfType
 * @see #withoutCause
 * @see #satisfying
 */
public final class ThrowableExpectations {
    private final List<Consumer<Throwable>> assertions;
    private ThrowableExpectations causeExpectations;

    /**
     * The constructor used by {@link SpecBuilder#shouldThrow}.
     * You should never have a need to use it when writing tests.
     *
     * @param expectedException
     *     the type of Exception expected from the test,
     *     passed through the first argument of {@link SpecBuilder#shouldThrow}
     */
    public ThrowableExpectations(Class<? extends Throwable> expectedException) {
        this();
        satisfying(resultingException -> {
            if (resultingException == null)
                throw new AssertionError("Expected exception: " + expectedException.getName());
            if (!expectedException.isAssignableFrom(resultingException.getClass()))
                throw new AssertionError(format("Unexpected exception, expected: %s but was: %s",
                        expectedException.getName(), resultingException.getClass().getName()));
        });
    }

    private ThrowableExpectations() {
        assertions = new ArrayList<>();
    }

    /**
     * Adds an assertion that the thrown Exception has any (non-{@code null}) message.
     *
     * @return {@code this}
     */
    public ThrowableExpectations withAnyMessage() {
        return satisfying(throwable -> {
            String throwableMessage = throwable.getMessage();
            if (throwableMessage == null)
                throw new AssertionError(format(
                        "Expected '%s' to have a message", throwable));
        });
    }

    /**
     * Adds an assertion that the thrown Exception has message exactly equal to {@code message}.
     *
     * @param message
     *     the message that the expected Exception should have
     * @return {@code this}
     */
    public ThrowableExpectations withMessage(String message) {
        return satisfying(throwable -> {
            String throwableMessage = throwable.getMessage();
            if (!Objects.equals(message, throwableMessage))
                throw new AssertionError(format(
                        "Unexpected exception message, expected: '%s' but was: '%s'", message, throwableMessage));
        });
    }

    /**
     * Adds an assertion that the thrown Exception has message beginning with {@code messageStart}.
     *
     * @param messageStart
     *     the prefix that the expected Exception message should begin with
     * @return {@code this}
     */
    public ThrowableExpectations withMessageStartingWith(String messageStart) {
        withAnyMessage();
        return satisfying(throwable -> {
            String throwableMessage = throwable.getMessage();
            if (!throwableMessage.startsWith(messageStart))
                throw new AssertionError(format(
                        "Unexpected exception message, expected: '%s' to start with: '%s'", throwableMessage, messageStart));
        });
    }

    /**
     * Adds an assertion that the thrown Exception has message containing {@code messagePart}.
     *
     * @param messagePart
     *     the part that the expected Exception message should contain
     * @return {@code this}
     */
    public ThrowableExpectations withMessageContaining(String messagePart) {
        withAnyMessage();
        return satisfying(throwable -> {
            String throwableMessage = throwable.getMessage();
            if (!throwableMessage.contains(messagePart))
                throw new AssertionError(format(
                        "Unexpected exception message, expected: '%s' to contain: '%s'", throwableMessage, messagePart));
        });
    }


    /**
     * Adds an assertion that the thrown Exception has no (meaning, {@code null}) message.
     *
     * @return {@code this}
     */
    public ThrowableExpectations withoutMessage() {
        return satisfying(throwable -> {
            String throwableMessage = throwable.getMessage();
            if (throwableMessage != null)
                throw new AssertionError(format(
                        "Expected '%s' to not have a message", throwable));
        });
    }

    /**
     * Adds an assertion that the thrown Exception has a non-{@code null} cause,
     * and then returns a new instance of {@link ThrowableExpectations}
     * that can be used to add further assertions on the cause itself.
     *
     * @return
     *     a new instance of {@link ThrowableExpectations} that can be used to
     *     further refine assertions on the cause
     */
    public ThrowableExpectations withCause() {
        satisfying(throwable -> {
            if (throwable.getCause() == null)
                throw new AssertionError(format("Expected '%s' to have a cause", throwable));
        });
        causeExpectations = new ThrowableExpectations();
        return causeExpectations;
    }

    /**
     * Adds an assertion that the thrown Exception has a non-{@code null} cause
     * of type {@code expectedException}, and then returns a new instance of {@link ThrowableExpectations}
     * that can be used to add further assertions on the cause itself.
     *
     * @param expectedException
     *     the expected type of the cause of the thrown Exception
     * @return
     *     a new instance of {@link ThrowableExpectations} that can be used to
     *     further refine assertions on the cause
     */
    public ThrowableExpectations withCauseOfType(Class<? extends Throwable> expectedException) {
        withCause();
        causeExpectations = new ThrowableExpectations(expectedException);
        return causeExpectations;
    }

    /**
     * Adds an assertion that the thrown Exception has no (meaning, {@code null}) cause.
     *
     * @return {@code this}
     */
    public ThrowableExpectations withoutCause() {
        return satisfying(throwable -> {
            if (throwable.getCause() != null)
                throw new AssertionError(format("Expected '%s' to not have a cause", throwable));
        });
    }

    /**
     * Adds a custom assertion. This is the most general method of the {@link ThrowableExpectations} class,
     * and can be used to add arbitrary assertions on the thrown Exception.
     * The {@link Consumer} passed in {@code assertion} should throw an Exception (or an {@link AssertionError})
     * with the appropriate message if the thrown Exception does not satisfy the predicate.
     * <p>
     * Example:
     * <pre>
     * {@code
     * it.shouldThrow(RuntimeException.class, "when throwing an Exception", () -> {
     *         throw new RuntimeException("some message");
     *     }).satisfying(e -> {
     *         String msg = e.getMessage();
     *         if (msg == null || !msg.endsWith("message"))
     *             throw new AssertionError(String.format(
     *                 "Expected '%s' to end with 'message'", msg));
     *     });
     * }
     * </pre>
     * <b>Note</b>: the {@link ThrowableExpectations} class is designed in such a way that you're guaranteed
     * that the thrown Exception passed to your {@link Consumer} is always non-{@code null}
     * (even when writing assertions for the cause, so after calling {@link #withCause} or {@link #withCauseOfType}),
     * so you don't have to check for {@code null} in your assertion.
     *
     * @param assertion
     *     an instance of {@link Consumer} that is called with the thrown Exception passed as the first argument of
     *     {@link Consumer#accept}
     * @return {@code this}
     */
    public ThrowableExpectations satisfying(Consumer<Throwable> assertion) {
        assertions.add(assertion);
        return this;
    }

    private void verify(Throwable throwable) {
        for (Consumer<Throwable> assertion : assertions)
            assertion.accept(throwable);

        if (causeExpectations != null)
            causeExpectations.verify(throwable.getCause());
    }

    /**
     * A wrapper class around {@link ThrowableExpectations} used in the implementation of
     * {@link SpecBuilder#shouldThrow}.
     * <p>
     * <b>Note</b>: you should never need this class when writing tests,
     * it's just an implementation detail.
     */
    public class Wrapper {
        public void verify(Throwable throwable) {
            ThrowableExpectations.this.verify(throwable);
        }

        public ThrowableExpectations inner() {
            return ThrowableExpectations.this;
        }
    }
}
