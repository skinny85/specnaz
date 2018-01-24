package org.specnaz.impl;

import org.specnaz.TestSettings;
import org.specnaz.utils.TestClosure;

import java.util.LinkedList;
import java.util.List;

public final class TestsGroupNodeBuilder {
    private final String description;
    private final TestCaseType testCaseType;
    private final List<TestClosure> beforeAlls = new LinkedList<>(),
                                    befores    = new LinkedList<>(),
                                    afters     = new LinkedList<>(),
                                    afterAlls  = new LinkedList<>();
    private final List<SingleTestCase> testCases  = new LinkedList<>();
    private final List<TreeNode<TestsGroup>> subgroups = new LinkedList<>();
    private boolean containsFocusedTests = false;

    public TestsGroupNodeBuilder(String description, TestCaseType testCaseType) {
        this.description = description;
        this.testCaseType = testCaseType;
        if (testCaseType == TestCaseType.FOCUSED)
            containsFocusedTests = true;
    }

    public void addBeforeAll(TestClosure closure) {
        beforeAlls.add(closure);
    }

    public void addBeforeEach(TestClosure closure) {
        befores.add(closure);
    }

    public TestSettings addTestCase(SingleTestCase testCase) {
        SingleTestCase testCaseToAdd = null;
        switch (testCaseType) {
            case REGULAR:
                testCaseToAdd = testCase;
                if (testCase.type == TestCaseType.FOCUSED)
                    containsFocusedTests = true;
                break;
            case FOCUSED:
                testCaseToAdd = testCase.type == TestCaseType.REGULAR
                        ? testCase.type(TestCaseType.FOCUSED)
                        : testCase;
                break;
            case IGNORED:
                testCaseToAdd = testCase.type(TestCaseType.IGNORED);
                break;
        }
        testCases.add(testCaseToAdd);
        return testCaseToAdd.testSettings().unwrap();
    }

    public void addAfterEach(TestClosure closure) {
        afters.add(closure);
    }

    public void addAfterAll(TestClosure closure) {
        afterAlls.add(closure);
    }

    public TestsGroupNodeBuilder subgroupBuilder(String description, TestCaseType testCaseType) {
        return new TestsGroupNodeBuilder(description, subgroupTestType(testCaseType));
    }

    public void addSubgroup(TreeNode<TestsGroup> subgroupNode) {
        subgroups.add(subgroupNode);
    }

    public TreeNode<TestsGroup> build() {
        // count tests in subgroups
        int testsInSubgroups = subgroups.stream().mapToInt(node -> node.value.testsInTree).sum();
        // see if any subgroup contains focused tests
        for (TreeNode<TestsGroup> subgroupNode : subgroups) {
            containsFocusedTests |= subgroupNode.value.containsFocusedTests;
        }

        TestsGroup testsGroup = new TestsGroup(description, beforeAlls, befores,
                testCases, afters, afterAlls, testsInSubgroups, containsFocusedTests);
        TreeNode<TestsGroup> testsGroupTreeNode = new TreeNode<>(testsGroup);
        for (TreeNode<TestsGroup> subgroupNode: subgroups) {
            testsGroupTreeNode.attach(subgroupNode);
            incrementFixturesCount(subgroupNode);
        }
        return testsGroupTreeNode;
    }

    private void incrementFixturesCount(TreeNode<TestsGroup> testsGroupNode) {
        TestsGroup testsGroup = testsGroupNode.value;
        testsGroup.incrementBeforeAllsCount(beforeAlls.size());
        testsGroup.incrementAfterAllsCount(afterAlls.size());

        for (TreeNode<TestsGroup> child : testsGroupNode.children()) {
            incrementFixturesCount(child);
        }
    }

    private TestCaseType subgroupTestType(TestCaseType testCaseType) {
        switch (this.testCaseType) {
            case REGULAR:
                return testCaseType;
            case FOCUSED:
                return testCaseType == TestCaseType.IGNORED
                        ? TestCaseType.IGNORED
                        : TestCaseType.FOCUSED;
            case IGNORED:
            default:
                return TestCaseType.IGNORED;
        }
    }
}
