class TreeNode {
    TreeNode left;
    TreeNode right;
    int value;
    int height;

    public TreeNode(int v) {
        this.value = v;
        this.height = 0;
    }

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
