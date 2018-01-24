package org.specnaz.junit.impl;

import org.junit.runner.Description;

public class JUnitDescUtils {
    public static Description makeTestDesc(String description, Description parentDescription) {
        return Description.createTestDescription(parentDescription.getDisplayName(), description);
    }
}
