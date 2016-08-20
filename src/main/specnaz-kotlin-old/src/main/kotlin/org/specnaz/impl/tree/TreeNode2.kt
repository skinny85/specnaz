package org.specnaz.impl.tree

data class TreeNode2<T>(val value: T) {
    var parent: TreeNode2<T>? = null
        private set
    var children: List<TreeNode2<T>> = emptyList()
        private set

    fun attach(node: TreeNode2<T>) {
        if (node.parent != null)
            throw IllegalArgumentException("Cannot attach an already attached node $node")
        node.parent = this
        children += node
    }
}
