package org.specnaz.junit;

import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.Specnaz;
import org.specnaz.junit.core.SpecnazCoreDslJUnitRunner;
import org.specnaz.junit.utils.Utils;

/**
 * The JUnit {@link Runner} used for running {@link Specnaz} specifications.
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
 */
public final class SpecnazJUnitRunner extends Runner {
    private final SpecnazCoreDslJUnitRunner coreDslRunner;

    /**
     * This is JUnit's entry point for {@link Runner}s.
     *
     * @param classs
     *     the class of the tests currently being run.
     *     It must implement and obey the contract of the
     *     {@link Specnaz} interface
     */
    public SpecnazJUnitRunner(Class<?> classs) throws IllegalStateException {
        String className = classs.getSimpleName();
        try {
            this.coreDslRunner = new SpecnazCoreDslJUnitRunner(className,
                    Utils.instantiateTestClass(classs, Specnaz.class));
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Specnaz.describes() was never called in the no-argument constructor of " +
                    className);
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
}
