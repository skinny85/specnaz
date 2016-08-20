package org.specnaz.junit;

import org.junit.runner.RunWith;
import org.specnaz.SpecBuilder;
import org.specnaz.Specnaz;

import java.util.function.Consumer;

@RunWith(SpecnazJUnitRunner.class)
public abstract class SpecnazJUnit implements Specnaz {
    public SpecnazJUnit(String description, Consumer<SpecBuilder> specClosure) {
        describes(description, specClosure);
    }

    public SpecnazJUnit() {
    }
}
