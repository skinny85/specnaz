package org.specnaz.params.junit;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.specnaz.junit.core.SpecnazCoreDslJUnitRunner;
import org.specnaz.junit.utils.Utils;
import org.specnaz.params.SpecnazParams;

public final class SpecnazParamsJUnitRunner extends Runner {
    private final SpecnazCoreDslJUnitRunner coreDslJUnitRunner;

    public SpecnazParamsJUnitRunner(Class<?> classs) {
        this.coreDslJUnitRunner = new SpecnazCoreDslJUnitRunner(classs,
                Utils.instantiateTestClass(classs, SpecnazParams.class));
    }

    @Override
    public Description getDescription() {
        return coreDslJUnitRunner.getDescription();
    }

    @Override
    public void run(RunNotifier notifier) {
        coreDslJUnitRunner.run(notifier);
    }
}
