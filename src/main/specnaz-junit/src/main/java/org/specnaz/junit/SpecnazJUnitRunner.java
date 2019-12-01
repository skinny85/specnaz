package org.specnaz.junit;

import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.Specnaz;
import org.specnaz.junit.core.SpecnazCoreDslJUnitRunner;
import org.specnaz.junit.utils.Utils;
import org.specnaz.params.SpecnazParams;

import static java.lang.String.format;

/**
 * The JUnit 4 {@link Runner} used for running {@link Specnaz} specifications.
 * The shortest way to use it is to extend {@link SpecnazJUnit}.
 * If your test class needs to extend a different class,
 * you can always specify this runner with the {@link RunWith} annotation:
 *
 * <pre>
 * <code>
 * {@literal @}RunWith(SpecnazJUnitRunner.class)
 *     public class MySpec extends SomeCustomClass implements Specnaz {{
 *         describes("something", it -&gt; {
 *             // specification body here
 *         });
 *     }}
 * </code>
 * </pre>
 *
 * This Runner also supports parametrized tests, for classes implementing
 * {@link SpecnazParams} instead of {@link Specnaz}.
 */
public final class SpecnazJUnitRunner extends Runner {
    private final SpecnazCoreDslJUnitRunner coreDslRunner;

    /**
     * This is JUnit's entry point for {@link Runner}s.
     *
     * @param classs
     *     the class of the tests currently being run.
     *     It must implement and obey the contract of the
     *     {@link Specnaz} interface (or its parametrized equivalent,
     *     {@link SpecnazParams})
     * @throws IllegalStateException
     *     if the {@link Specnaz#describes} method was never called
     *     in the no-argument constructor of {@code classs}
     */
    public SpecnazJUnitRunner(Class<?> classs) throws IllegalStateException {
        Class<?> targetInterface = establishTargetInterface(classs);
        try {
            this.coreDslRunner = new SpecnazCoreDslJUnitRunner(classs,
                    Utils.instantiateTestClass(classs, targetInterface));
        } catch (IllegalStateException e) {
            throw new IllegalStateException(
                    format("%s.describes() was never called in the no-argument constructor of %s",
                            targetInterface.getSimpleName(), classs.getSimpleName()));
        }
    }

    @Override
    public Description getDescription() {
        return coreDslRunner.getDescription();
    }

    @Override
    public void run(RunNotifier runNotifier) {
        coreDslRunner.run(runNotifier);
    }

    private Class<?> establishTargetInterface(Class<?> classs) {
        return SpecnazParams.class.isAssignableFrom(classs)
                ? SpecnazParams.class
                : Specnaz.class;
    }
}
