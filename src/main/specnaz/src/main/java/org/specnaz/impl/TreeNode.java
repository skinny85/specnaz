package org.specnaz.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;

public final class TreeNode<T> {
    public final T value;
    private TreeNode<T> parent = null;
    private final List<TreeNode<T>> children = new LinkedList<>();

    public TreeNode(T value) {
        this.value = value;
    }

    public void attach(TreeNode<T> node) {
        if (node.parent != null) {
            throw new IllegalArgumentException(format("Node %s already has a parent", node));
        }
        node.parent = this;
        children.add(node);
    }

    public TreeNode<T> parent() {
        return parent;
    }

    public List<TreeNode<T>> children() {
        return Collections.unmodifiableList(children);
    }
}
