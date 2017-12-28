package org.specnaz.junit.rules;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.Specnaz;
import org.specnaz.junit.core.SpecnazCoreDslJUnitRunner2_Rules;
import org.specnaz.junit.utils.Utils;

public class SpecnazJUnitRunner2_Rules extends Runner {
    private final SpecnazCoreDslJUnitRunner2_Rules coreDslRunner;

    public SpecnazJUnitRunner2_Rules(Class<?> classs) throws IllegalStateException {
        String className = classs.getSimpleName();
        try {
            this.coreDslRunner = new SpecnazCoreDslJUnitRunner2_Rules(classs,
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
