package org.specnaz.junit.impl;

import org.junit.runner.Description;

import java.lang.annotation.Annotation;

public class JUnitDescUtils {
    public static Description makeTestDesc(String description, Description parentDescription,
            Annotation... annotations) {
        return Description.createTestDescription(parentDescription.getDisplayName(), description, annotations);
    }
}
