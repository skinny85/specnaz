package org.specnaz.java.junit;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.specnaz.SpecnazSuiteBuilder;
import org.specnaz.java.SpecnazJavaSpecs;
import org.specnaz.java.SpecnazJavaSuiteBuilder;
import org.specnaz.junit.SpecnazJUnitOld;

public abstract class SpecnazJunitJava extends SpecnazJUnitOld {
    private static Function1<SpecnazSuiteBuilder, Unit> convertToKotlin(SpecnazJavaSpecs specs) {
        return specnazSuiteBuilder -> {
            specs.apply(new SpecnazJavaSuiteBuilder(specnazSuiteBuilder));

            return Unit.INSTANCE;
        };
    }

    public SpecnazJunitJava(SpecnazJavaSpecs specs) {
        super(convertToKotlin(specs));
    }
}
