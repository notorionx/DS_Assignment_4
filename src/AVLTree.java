import com.sun.source.tree.Tree;

class AVLTree {

    TreeNode root;

    public AVLTree(TreeNode r) {
        this.root = r;
    }

    // This function simply is a 'pass through' function that passes our root to the
    // function that actually does the work
    public int calculateHeight() {
        return calculateHeight(this.root);
    }

    private int calculateHeight(TreeNode root) {
        //TODO
        return -1;
    }

    // O(1) updateHeight function used to update only after rotations
    public void updateHeight(TreeNode root) {
        if(root == null) {
            return;
        }
        int leftHeight = -1;
        if(root.left != null) {
            leftHeight = root.left.height;
        }
        int rightHeight = -1;
        if(root.right != null) {
            rightHeight = root.right.height;
        }
        root.height = Math.max(leftHeight, rightHeight) + 1;
    }

    public int getHeight(TreeNode root) {
        if(root == null) {
            return -1;
        }
        return root.height;
    }

    public boolean isBalanced() {
        return isBalanced(this.root);
    }

    private boolean isBalanced(TreeNode root) {
        return (Math.abs(balanceFactor(root)) <= 1);
    }

    private int balanceFactor(TreeNode root) {
        return (getHeight(root.left) - getHeight(root.right));
    }

    public boolean containsValue(int val) {
        TreeNode n = this.root;

        while(n != null) {
            if(n.value > val) {
                n = n.left;
            } else if(n.value < val) {
                n = n.right;
            } else{
                return true;
            }
        }

        return false;
    }

    // takes in a root, rotates the subtree, and returns a new root
    public TreeNode rotateRight(TreeNode root) {
        TreeNode left = root.left;
        root.left = left.right;
        left.right = root;
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        left.height = 1 + Math.max(getHeight(left.left), getHeight(left.right));
        return left;
    }

    public TreeNode rotateLeft(TreeNode root) {
        TreeNode right = root.right;
        root.right = right.left;
        right.left = root;
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        right.height = 1 + Math.max(getHeight(right.left), getHeight(right.right));
        return right;
    }

    public void print() {
        this.root.print("");
    }
}
