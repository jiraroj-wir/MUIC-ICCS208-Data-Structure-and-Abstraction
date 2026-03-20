import java.util.*;

public class MakeTree {
    public static BinaryTreeNode buildBST(int[] keys) {
        int mid = keys.length >> 1;

        return new BinaryTreeNode(helper(keys, 0, mid - 1), keys[mid], helper(keys, mid + 1, keys.length - 1));
    }

    private static BinaryTreeNode helper(int[] keys, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) >> 1;

        return new BinaryTreeNode(helper(keys, left, mid - 1), keys[mid], helper(keys, mid + 1, right));
    }

    // tests
    public static void main(String[] args) {
        int[] k1 = {1};
        BinaryTreeNode r1 = MakeTree.buildBST(k1);
        assert isSorted(r1, new int[] {1}, new int[] {0});

        int[] k2 = {1, 2};
        BinaryTreeNode r2 = MakeTree.buildBST(k2);
        assert isSorted(r2, new int[] {1, 2}, new int[] {0});

        int[] k3 = {1, 2, 3};
        BinaryTreeNode r3 = MakeTree.buildBST(k3);
        assert isSorted(r3, new int[] {1, 2, 3}, new int[] {0});

        int[] k4 = {1, 2, 3, 4, 5, 6, 7};
        BinaryTreeNode r4 = MakeTree.buildBST(k4);
        assert isSorted(r4, new int[] {1, 2, 3, 4, 5, 6, 7}, new int[] {0});

        int[] k5 = {10, 20, 30, 40, 50, 60};
        BinaryTreeNode r5 = MakeTree.buildBST(k5);
        assert isSorted(r5, new int[] {10, 20, 30, 40, 50, 60}, new int[] {0});
    }

    private static boolean isSorted(BinaryTreeNode node, int[] expected, int[] idx) {
        if (node == null) {
            return true;
        }
        if (!isSorted(node.left, expected, idx)) {
            return false;
        }
        if (node.key != expected[idx[0]++]) {
            return false;
        }

        return isSorted(node.right, expected, idx);
    }
}
