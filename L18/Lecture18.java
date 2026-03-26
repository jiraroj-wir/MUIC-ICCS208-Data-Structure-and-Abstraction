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

    /*
    Problem 2: Write a static method public static String concatPreorder(TreeNode<String> tree) that returns the
    concatenation of the keys in the tree visited using a pre-order traversal. As an example, consider the tree below

        A
       / \
      C   B
     /   / \
    D   E   F

    which has a pre-traversal of A C D B E F. Concatenating these strings yields "ACDBEF", which is the expected return
    value from this method.

    Performance Expectations: String concatenation turns out to be expensive. Writing x + y takes time O(len(x) +
    len(y)). For this problem, use StringBuilder appropriately to make sure that the total running time of the method is
    linear in the length of the returned string.
     */
    public static String concatPreorder(TreeNode<String> tree) {
        StringBuilder sb = new StringBuilder();

        preorder(tree, sb);

        return sb.toString();
    }

    private static void preorder(TreeNode<String> tree, StringBuilder sb) {
        if (tree == null) {
            return;
        }

        sb.append(tree.key);

        preorder(tree.left, sb);
        preorder(tree.right, sb);
    }

    // tests
    public static void main(String[] args) {
        // count
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

        // concatPreorder
        TreeNode<String> t1 = null;
        assert concatPreorder(t1).equals("");

        TreeNode<String> t2 = new TreeNode<>("A");
        assert concatPreorder(t2).equals("A");

        TreeNode<String> t3 = new TreeNode<>("A");
        t3.left = new TreeNode<>("C");
        t3.right = new TreeNode<>("B");
        t3.left.left = new TreeNode<>("D");
        t3.right.left = new TreeNode<>("E");
        t3.right.right = new TreeNode<>("F");
        assert concatPreorder(t3).equals("ACDBEF");
    }
}
