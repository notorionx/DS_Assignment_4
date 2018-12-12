/**
 * TreeNode class representing one node in a tree. Used to implement BST and AVLTree.
 */
class TreeNode {
    TreeNode left;
    TreeNode right;
    int value;
    int height;

    /**
     * Constructor for TreeNode
     * @param v the value to be stored at the node
     */
    public TreeNode(int v) {
        this.value = v;
        this.height = 0;
    }

    /**
     * The two following methods are a wrapper method, and a method that prints
     * a binary tree diagram in the console.
     *
     * Taken from a StackOverflow answer at https://stackoverflow.com/a/8948691
     * in order to help debug the program, since I find it hard to debug problems with
     * trees unless I can visualize it.
     *
     * These two methods are copied purely for debugging purposes and have no impact
     * on my actual implementation of the answers. Every other line of code in this
     * program was written by myself.
     *
     * @param prefix
     */
    public void print(String prefix)
    {
        print(prefix, true);
    }

    private void print(String prefix, boolean isTail)
    {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + this.value);

        if(right != null)
        {
            right.print(prefix + (isTail ? "    " : "│   "), (left == null));
        }

        if(left != null)
        {
            left.print(prefix + (isTail ?"    " : "│   "), true);
        }

    }
}
