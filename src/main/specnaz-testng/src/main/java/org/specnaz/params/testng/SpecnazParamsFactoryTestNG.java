package org.specnaz.params.testng;

import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.params.SpecnazParams;
import org.specnaz.testng.impl.SpecnazTestNgSpecFactory;
import org.testng.annotations.Factory;

public interface SpecnazParamsFactoryTestNG extends SpecnazParams {
    @Factory
    default Object[] specs() {
        try {
            return SpecnazTestNgSpecFactory.specs(this);
        } catch (SpecsRegistryViolation e) {
            throw new IllegalStateException("SpecnazParams.describes() was never called in the " +
                    "no-argument constructor of " + this.getClass().getSimpleName());
        }
    }
}
