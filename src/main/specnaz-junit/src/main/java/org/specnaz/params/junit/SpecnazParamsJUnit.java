package org.specnaz.params.junit;

import org.junit.runner.RunWith;
import org.specnaz.junit.SpecnazJUnitRunner;
import org.specnaz.params.SpecnazParams;

/**
 * The parametrized equivalent of {@link org.specnaz.junit.SpecnazJUnit}.
 * Implements the {@link SpecnazParams} interface and declares
 * {@link SpecnazJUnitRunner} as the JUnit 4 {@link org.junit.runner.Runner}
 * with the {@link RunWith} annotation.
 * Useful in cases when your test class doesn't need to extend any particular class.
 */
@RunWith(SpecnazJUnitRunner.class)
public abstract class SpecnazParamsJUnit implements SpecnazParams {
}
