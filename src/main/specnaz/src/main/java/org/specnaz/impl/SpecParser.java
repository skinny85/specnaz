package org.specnaz.impl;

import java.util.Collection;

public final class SpecParser {
    private final SpecDescriptor specDescriptor;
    private TreeNode<TestsGroup> rootTestsGroupNode;

    public SpecParser(Object specInstance) throws SpecsRegistryViolation {
        this.specDescriptor = SpecsRegistry.specFor(specInstance);
    }

    public String name() {
        return specDescriptor.description;
    }

    public TreeNode<TestsGroup> testsPlan() {
        if (rootTestsGroupNode == null)
            rootTestsGroupNode = formulateTestPlan();
        return rootTestsGroupNode;
    }

    public Collection<ExecutableTestGroup> executableTestGroups() {
        TreeNode<TestsGroup> testsPlan = testsPlan();
        return new TestsGroupNodeExecutor(testsPlan, testsPlan.value.containsFocusedTests)
                .executableTestGroups();
    }

    private TreeNode<TestsGroup> formulateTestPlan() {
        TestsTreeCoreDslBuilder testsTreeCoreDslBuilder = new TestsTreeCoreDslBuilder(name(), specDescriptor.ignoredTestGroup
                ? TestCaseType.IGNORED
                : TestCaseType.REGULAR);
        specDescriptor.specClosure.accept(testsTreeCoreDslBuilder);
        return testsTreeCoreDslBuilder.spec();
    }
}
