package org.specnaz.testng;

import org.specnaz.impl.ExecutableTestCase;
import org.specnaz.testng.impl.SharedAllsFixtures;
import org.testng.ITest;
import org.testng.SkipException;
import org.testng.annotations.Test;

public final class SpecnazTests implements ITest {
    private final String signature;
    private final String descriptionsPrefix;
    private final SharedAllsFixtures beforeAlls;
    private final ExecutableTestCase testCase;
    private final SharedAllsFixtures afterAlls;

    public SpecnazTests(String signature, String descriptionsPrefix,
            SharedAllsFixtures beforeAlls, ExecutableTestCase testCase, SharedAllsFixtures afterAlls) {
        this.signature = signature;
        this.descriptionsPrefix = descriptionsPrefix;
        this.beforeAlls = beforeAlls;
        this.testCase = testCase;
        this.afterAlls = afterAlls;
    }

    @Override
    public String getTestName() {
        return this.descriptionsPrefix + this.testCase.testCase.description;
    }

    @Test
    public void executeTest() throws Throwable {
        if (testCase.isIgnored())
            throw new SkipException("spec marked as ignored");

        Throwable resultException = beforeAlls.execute();

        if (resultException == null)
            resultException = testCase.execute();

        Throwable afterAllsException = afterAlls.execute();

        if (resultException != null)
            throw resultException;
        else if (afterAllsException != null)
            throw afterAllsException;
    }

    public String getSignature() {
        return signature;
    }
}
