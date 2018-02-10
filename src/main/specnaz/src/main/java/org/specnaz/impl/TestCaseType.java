package org.specnaz.impl;

public enum TestCaseType {
    REGULAR,
    FOCUSED,
    IGNORED;

    public TestCaseType descendantTestType(TestCaseType testCaseType) {
        switch (this) {
            case REGULAR:
                return testCaseType;
            case FOCUSED:
                return testCaseType == REGULAR
                        ? FOCUSED
                        : testCaseType;
            case IGNORED:
            default:
                return IGNORED;
        }
    }
}
