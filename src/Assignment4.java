import java.util.Arrays;

public class Assignment4 {

    public static TreeNode offlineBinarySearchTree(int[] inputs) {

        Arrays.sort(inputs);

        int median = inputs[inputs.length/2];
        TreeNode root = new TreeNode(median);

        int[] left = Arrays.copyOfRange(inputs, 0, inputs.length/2);
        int[] right = Arrays.copyOfRange(inputs, inputs.length/2 + 1, inputs.length);

        if(left.length != 0) {
            root.left = offlineBinarySearchTree(left);
        }

        if(right.length != 0) {
            root.right = offlineBinarySearchTree(right);
        }

        return root;
    }

    public static AVLTree onlineBinarySearchTree(int[] inputs) {

        AVLTree t = new AVLTree();

        for(int input : inputs) {
            /**
             * Inserting new elements is O(log n) where n is the number of nodes in the tree.
             * This is because an AVL Tree balances itself after each insertion to maintain
             * a height of roughly log n, and we are always going to be inserting new elements
             * at leaf nodes, so we need to make log n comparisons to find where to insert.
             */
            t.insert(input);
            t.print();
        }



        return t;

    }


    public static void main(String[] args) {
        int[] arr = {40, 20, 10, 25, 30, 22, 50, 2, 6, 13, 5, 99, 75};

//        TreeNode n = offlineBinarySearchTree(arr);
//        n.print("");

        AVLTree t = onlineBinarySearchTree(arr);
//        t.print();


    }
}