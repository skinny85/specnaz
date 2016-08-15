package org.specnaz.impl.tree

data class TreeNode<T>(val value: T) {
    var parent: TreeNode<T>? = null
        private set
    var children: List<TreeNode<T>> = emptyList()
        private set

    fun attach(node: TreeNode<T>) {
        if (node.parent != null)
            throw IllegalArgumentException("Cannot attach an already attached node $node")
        node.parent = this
        children += node
    }
}
