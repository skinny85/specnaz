package org.specnaz.junit;

import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.specnaz.Specnaz;

/**
 * A utility class that implements the {@link Specnaz} interface
 * and declares {@link SpecnazJUnitRunner} as the JUnit test {@link Runner}
 * with the {@link RunWith} annotation.
 * Useful in cases where your test class doesn't have to extend any particular class.
 */
@RunWith(SpecnazJUnitRunner.class)
public abstract class SpecnazJUnit implements Specnaz {
}
