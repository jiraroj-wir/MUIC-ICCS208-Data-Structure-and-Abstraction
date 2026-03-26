import java.util.*;

public class Lecture18 {
    static class TreeNode<E> {
        E key;
        TreeNode<E> left, right;

        // constructors omitted
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
}
