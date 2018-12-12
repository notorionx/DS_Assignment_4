import java.util.Arrays;

public class Assignment4 {
    /**
     * Problems 1 and 2
     * @param inputs the array to be constructed into a BST
     * @return the root node of resulting BST
     */
    public static TreeNode offlineBinarySearchTree(int[] inputs) {
        /**
         * The first two steps of this algorithm creates one node and takes O(n(log n)) time,
         * where n is the size of the input array. This is because
         * the bottleneck is the merge sort which takes O(n(log n)) time, and
         * the actual creation of the node only takes O(1) time.
         *
         * Now, we need to consider the recursive calls.
         *
         * For the whole algorithm, each call for an input of length n makes two
         * recursive calls with input of length n/2, each taking (n/2)(log(n/2)) time.
         * Therefore, the time complexity is
         *
         * O(n(log n) + 2 * (n/2)(log(n/2)) + 4 * (n/4)(log(n/4)) + ...)
         *
         * which is equivalent to
         *
         * O(n(log n) + n(log(n/2)) + n(log(n/4)) + ...)
         *
         * which is equivalent to
         *
         * O(n * (log(n) + log(n/2) + log(n/4) + ...))
         *
         * which is equivalent to
         *
         * O(n * log(n)^2)
         *
         * which is our answer.
         */

        /**
         * Return null if no inputs
         */
        if(inputs.length == 0) return null;

        /**
         * Initial merge sort on the input array runs in O(n log n) time
         */
        Arrays.sort(inputs);

        /**
         * Extract median from sorted array and create root node for this iteration
         * of offlineBinarySearchTree. Runs in O(1) time.
         */
        int median = inputs[inputs.length/2];
        TreeNode root = new TreeNode(median);

        /**
         * Create copies of left and right sides of initial input array which we
         * pass to recursive calls for left and right children
         */
        int[] left = Arrays.copyOfRange(inputs, 0, inputs.length/2);
        int[] right = Arrays.copyOfRange(inputs, inputs.length/2 + 1, inputs.length);

        if(left.length != 0) {
            //Create left child
            root.left = offlineBinarySearchTree(left);
        }

        if(right.length != 0) {
            //Create right child
            root.right = offlineBinarySearchTree(right);
        }

        //Return root of tree
        return root;
    }

    /**
     * Problems 3 and 4
     * @param inputs the array of values to be inserted into the AVL Tree
     * @return the resulting AVL Tree
     */
    public static AVLTree onlineBinarySearchTree(int[] inputs) {
        /**
         * The time it takes to insert one element into an AVL Tree is O(log n),
         * where n is the total number of nodes in the tree. This is because we
         * always start comparing at the root node and insert at a leaf, so we need
         * to make a number of comparisons equal to the height of the tree,
         * which, due to the nature of an AVL Tree, is approximately log n.
         *
         * When we consider the time it takes to insert the whole array of n elements
         * into the tree, we need to note that the size of the tree changes with each
         * insertion we make. Therefore, the total time it takes is going to be
         * O(log 1 + log 2 + ... + log n), which is equivalent to O(log(n!)), and
         * O(log(n!)) is equivalent to O(n log n), which is our answer.
         */


        //Initialize AVL Tree
        AVLTree t = new AVLTree();

        for(int input : inputs) {
            //Input values one at a time
            t.insert(input);
        }



        return t;

    }

    /**
     * For testing
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {40, 20, 10, 25, 30, 40, 22, 50, 2, 2, 6, 13, 5, 99, 75};

        TreeNode n = offlineBinarySearchTree(arr);
        n.print("");

        AVLTree t = onlineBinarySearchTree(arr);
        t.print();


    }
}