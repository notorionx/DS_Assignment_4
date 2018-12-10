class AVLTree {

    TreeNode root;

    public AVLTree() {}

    public AVLTree(TreeNode r) {
        this.root = r;
    }

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

    public boolean containsValue(TreeNode n, int val) {

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

    public boolean insert(int val) {

        if(!containsValue(this.root, val)) {
            this.root = insert(this.root, val);
            return true;
        }

        return false;
    }

    private TreeNode insert(TreeNode n, int val) {
        if(n == null) {
            return new TreeNode(val);
        } else if(val < n.value) {
            n.left = insert(n.left, val);
        } else {
            n.right = insert(n.right, val);
        }

        updateHeight(n);

        return balance(n);
    }

    private TreeNode balance(TreeNode n) {
        if(balanceFactor(n) < -1) {
            if(balanceFactor(n.right) < -1) {
                //LL
                return rotateLeft(n);
            } else {
                //RL
                n.right = rotateRight(n.right);
                return rotateLeft(n);
            }
        } else if(balanceFactor(n) > 1) {
            if(balanceFactor(n.left) > 1) {
                //RR
                return rotateRight(n);
            } else {
                //LR
                n.left = rotateLeft(n.left);
                return rotateRight(n);
            }
        } else {
            return n;
        }
    }


    // takes in a root, rotates the subtree, and returns a new root
    public TreeNode rotateRight(TreeNode root) {
        TreeNode left = root.left;
        root.left = left.right;
        left.right = root;

        updateHeight(root);
        updateHeight(left);

        return left;
    }

    public TreeNode rotateLeft(TreeNode root) {
        TreeNode right = root.right;
        root.right = right.left;
        right.left = root;

        updateHeight(root);
        updateHeight(right);

        return right;
    }

    public void print() {
        this.root.print("");
    }
}
