import java.util.*;

public class Lecture18 {
    static class TreeNode<E> {
        E key;
        TreeNode<E> left, right;

        // constructors omitted
        TreeNode(E k) { this.key = k; }
    }

    /*
     * Problem 1: Write a static method public static int count(TreeNode<?> tree) that returns the number of nodes in
     * the tree. (Hint: count(null) is a valid call and should return 0.)
     */
    public static int count(TreeNode<?> tree) {
        if (tree == null) {
            return 0;
        }

        return 1 + count(tree.left) + count(tree.right);
    }

    // tests
    public static void main(String[] args) {
        assert count(null) == 0;

        TreeNode<Integer> single = new TreeNode<Integer>(1);
        assert count(single) == 1;

        TreeNode<Integer> root = new TreeNode<Integer>(1);
        root.left = new TreeNode<Integer>(2);
        root.right = new TreeNode<Integer>(3);
        assert count(root) == 3;

        root.left.left = new TreeNode<Integer>(4);
        root.left.right = new TreeNode<Integer>(5);
        assert count(root) == 5;
    }
}
