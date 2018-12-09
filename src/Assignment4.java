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

//    public AVLTree onlineBinarySearchTree(int[] inputs) {
//
//        // Problem 3
//        // The output should be an AVL-Tree
//        return null;
//
//    }


    public static void main(String[] args) {
        int[] arr = {3, 6, 1, 7, 5, 2, 4, 0, 8, 9};
        TreeNode root = offlineBinarySearchTree(arr);

        root.print("");

        AVLTree a = new AVLTree(root);
        System.out.println(a.isBalanced());
        a.print();
    }
}