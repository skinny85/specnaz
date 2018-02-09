package org.specnaz.impl;

import org.specnaz.TestSettings;
import org.specnaz.utils.TestClosure;

public final class SinglePositiveTestCase extends SingleTestCase {
    public SinglePositiveTestCase(TestSettings testSettings, String description, TestClosure testBody,
            TestCaseType testCaseType) {
        super(testSettings, description, testBody, testCaseType);
    }
}
