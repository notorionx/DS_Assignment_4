/**
 * AVL Tree, a binary search tree which balances itself after the insertion of an element
 * in order to maintain a height of roughly log n, where n is the total number of nodes
 * in the tree
 */
class AVLTree {

    TreeNode root;

    /**
     * Default constructor
     */
    public AVLTree() {}

    /**
     * Constructor
     * @param r the root node of the AVL Tree
     */
    public AVLTree(TreeNode r) {
        this.root = r;
    }

    /**
     * Updates the height of a node by comparing the height of its left
     * and right subtrees and taking the max + 1. This method gets called
     * after insertions and rotations.
     * @param root the root of the subtree that we want to update the height of
     */
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

    /**
     * Getter method for the height of a given node
     * @param root the root of the subtree for which we want to find the height
     * @return the height of the given node
     */
    public int getHeight(TreeNode root) {
        if(root == null) {
            return -1;
        }
        return root.height;
    }

    /**
     * The balance factor is a number we use to determine what kind of rotation
     * needs to be made. We calculate this by subtracting the right subtree height
     * from the left subtree height. A balance factor between -1 and 1, inclusive,
     * means that there is no imbalance. < -1 balance factor means the right subtree
     * is too tall, and > 1 balance factor means the left subtree is too tall.
     * @param root the root node of the subtree we want to calculate the balance factor of
     * @return the balance factor of the given node
     */
    private int balanceFactor(TreeNode root) {
        return (getHeight(root.left) - getHeight(root.right));
    }

    /**
     * Wrapper method to insert a value into the tree. We automatically
     * pass this.root as the argument to the private insert method
     * which does the actual work of inserting the value starting from the root node.
     * @param val the value to be inserted
     */
    public void insert(int val) {
        this.root = insert(this.root, val);
    }

    /**
     * Inserts a new node with the given value at a new leaf node. After insertion,
     * update the height and then check for imbalances, and make corresponding rotations.
     * @param root the root node at which we make our first comparison
     * @param val the value to be inserted
     * @return the new root node
     */
    private TreeNode insert(TreeNode root, int val) {

        /**
         * Start comparing values from the root. If no root exists, create a new root and
         * return it (base case). Otherwise, compare values and make recursive call to insert
         * at left node if value is smaller than root value, and right if greater.
         */
        if(root == null) {
            return new TreeNode(val);
        } else if(val < root.value) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }

        /**
         * Update height before balancing, since balance calls balanceFactor which relies on
         * having accurate height values
         */
        updateHeight(root);

        /**
         * Balance after insertion and return the new root, since root may have changed
         * after rotations
         */
        return balance(root);
    }

    /**
     * Calls the rotateLeft and rotateRight methods to perform the correct sequence of
     * rotations if an imbalance is found. Otherwise, does nothing.
     * @param root the root node of the subtree we want to balance
     * @return the new root node
     */
    private TreeNode balance(TreeNode root) {
        if(balanceFactor(root) < -1) {
            //If tree is right-heavy
            if(balanceFactor(root.right) == -1) {
                //If right subtree is also right-heavy, perform LL rotation
                return rotateLeft(root);
            } else {
                //If right subtree is left-heavy, perform RL rotation
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
        } else if(balanceFactor(root) > 1) {
            //If tree is left-heavy
            if(balanceFactor(root.left) == 1) {
                //If left subtree is also left-heavy, perform RR rotation
                return rotateRight(root);
            } else {
                //If left subtree is right-heavy, perform LR rotation
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
        } else {
            //Do nothing if no imbalance is found
            return root;
        }
    }


    /**
     * Performs a right rotation on the given root node.
     * @param root the root node of the subtree we want to rotate
     * @return the new root node
     */
    public TreeNode rotateRight(TreeNode root) {
        TreeNode left = root.left;
        root.left = left.right;
        left.right = root;

        updateHeight(root);
        updateHeight(left);

        return left;
    }

    /**
     * Performs a left rotation on the given root node.
     * @param root the root node of the subtree we want to rotate
     * @return the new root node
     */
    public TreeNode rotateLeft(TreeNode root) {
        TreeNode right = root.right;
        root.right = right.left;
        right.left = root;

        updateHeight(root);
        updateHeight(right);

        return right;
    }

    /**
     * Wrapper for the print() method from TreeNode class taken from
     * the StackOverflow answer at https://stackoverflow.com/a/8948691.
     */
    public void print() {
        this.root.print("");
    }
}
