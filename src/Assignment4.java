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
            t.insert(input);
        }

        t.print();

        return t;

    }


    public static void main(String[] args) {
        int[] arr = {3, 6, 1, 7, 5, 2, 4, 0, 8, 9};

        TreeNode n = offlineBinarySearchTree(arr);
        n.print("");

        AVLTree t = onlineBinarySearchTree(arr);
        t.print();


    }
}