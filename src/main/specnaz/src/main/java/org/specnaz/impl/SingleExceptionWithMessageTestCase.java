package org.specnaz.impl;

import org.specnaz.utils.TestClosure;

import static java.lang.String.format;

public final class SingleExceptionWithMessageTestCase extends SingleTestCase {
    private final Class<? extends Throwable> expectedException;
    private final TestClosure testBody;
    private final String expectedMessage;

    public SingleExceptionWithMessageTestCase(Class<? extends Throwable> expectedException,
                                              String expectedMessage,
                                              String description,
                                              TestClosure testBody) {
        super(description);
        this.expectedException = expectedException;
        this.expectedMessage = expectedMessage;
        this.testBody = testBody;
    }

    @Override
    public Throwable exercise() {
        Throwable resultingException = SingleTestCase.invokeCallback(testBody);
        if (resultingException == null)
            return new AssertionError("Expected exception: " + expectedException.getName());
        if (!expectedException.isAssignableFrom(resultingException.getClass()))
            return new AssertionError(format("Unexpected exception, expected: %s but was: %s",
                    expectedException.getName(), resultingException.getClass().getName()));
        if (!expectedMessage.equals(resultingException.getMessage())){
            return new AssertionError(format("Unexpected exception, expected: %s with message %s but was: %s with message %s",
                    expectedException.getName(), expectedMessage, resultingException.getClass().getName(), resultingException.getMessage()));
        }
        return null;
    }
}
