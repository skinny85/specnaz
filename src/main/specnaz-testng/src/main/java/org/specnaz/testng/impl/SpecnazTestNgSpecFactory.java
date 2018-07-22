package org.specnaz.testng.impl;

import org.specnaz.impl.ExecutableTestCase;
import org.specnaz.impl.SpecParser;
import org.specnaz.impl.SpecsRegistryViolation;
import org.specnaz.impl.TestsGroupNodeExecutor;
import org.specnaz.testng.SpecnazTests;
import org.specnaz.utils.IntBox;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SpecnazTestNgSpecFactory {
    public static Object[] specs(Object specInstance) throws SpecsRegistryViolation {
        SpecParser specParser = new SpecParser(specInstance);
        Collection<TestsGroupNodeExecutor> testsGroupNodeExecutors = specParser.testsGroupNodeExecutors();
        IntBox counter = IntBox.boxWith(0);
        return testsGroupNodeExecutors.stream()
                .flatMap(e -> groupNodeExecutorsSpecnazTests(e, specInstance, ++counter.$))
                .toArray();
    }

    private static Stream<SpecnazTests> groupNodeExecutorsSpecnazTests(
            TestsGroupNodeExecutor testsGroupNodeExecutor, Object spec, int n) {
        Collection<ExecutableTestCase> executableTestCases = testsGroupNodeExecutor.executableTestCases(null);
        SharedAllsFixtures beforeAlls = new SharedAllsFixtures(testsGroupNodeExecutor.beforeAllsExecutable(), 0);
        SharedAllsFixtures afterAlls = new SharedAllsFixtures(testsGroupNodeExecutor.afterAllsExecutable(),
                executableTestCases.stream()
                        .filter(e -> !e.isIgnored())
                        .count());
        String signature = calculateSignature(spec, n);
        String descriptionsPrefix = testsGroupNodeExecutor.descriptionsPath().stream()
                .collect(Collectors.joining(" ")) + " ";
        return executableTestCases.stream()
                .map(e -> new SpecnazTests(signature, descriptionsPrefix, beforeAlls, e, afterAlls));
    }

    private static String calculateSignature(Object spec, int n) {
        StringBuilder sb = new StringBuilder(spec.getClass().getName());
        sb.append('_');
        int digits = (int) Math.log10(n) + 1;
        for (int i = 0; i < 4 - digits; i++) {
            sb.append('0');
        }
        sb.append(n);
        return sb.toString();
    }

    SpecnazTestNgSpecFactory() {
        throw new UnsupportedOperationException("cannot instantiate SpecnazTestNgSpecFactory");
    }
}
