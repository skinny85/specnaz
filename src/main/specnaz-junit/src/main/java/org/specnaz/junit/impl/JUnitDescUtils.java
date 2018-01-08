package org.specnaz.junit.impl;

import org.junit.runner.Description;

public class JUnitDescUtils {
    public static void addChildDescription(String description, Description parentDescription) {
        parentDescription.addChild(makeTestDesc(description, parentDescription));
    }

    public static Description makeTestDesc(String description, Description parentDescription) {
        return Description.createTestDescription(parentDescription.getDisplayName(), description);
    }
}
