package org.specnaz.testng;

import org.specnaz.Specnaz;
import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.testng.impl.SpecnazTestNgSpecFactory;
import org.testng.annotations.Factory;

public interface SpecnazFactoryTestNG extends Specnaz {
    @Factory
    default Object[] specs() {
        try {
            return SpecnazTestNgSpecFactory.specs(this);
        } catch (SpecsRegistryViolation e) {
            throw new IllegalStateException("Specnaz.describes() was never called in the " +
                    "no-argument constructor of " + this.getClass().getSimpleName());
        }
    }
}
