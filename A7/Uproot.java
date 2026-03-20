import java.util.*;

public class Uproot {
    public static HashMap<Integer, Integer> treeToParentMap(BinaryTreeNode T) {
        HashMap<Integer, Integer> map = new HashMap<>();

        dfs(T, map);

        return map;
    }

    private static void dfs(BinaryTreeNode T, HashMap<Integer, Integer> map) {
        if (T == null) {
            return;
        }

        if (T.left != null) {
            map.put(T.left.key, T.key);
            dfs(T.left, map);
        }
        if (T.right != null) {
            map.put(T.right.key, T.key);
            dfs(T.right, map);
        }
    }

    // tests
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(20);
        root.right = new BinaryTreeNode(9);
        root.left.left = new BinaryTreeNode(14);
        root.right.left = new BinaryTreeNode(2);
        root.right.right = new BinaryTreeNode(18);

        HashMap<Integer, Integer> map = treeToParentMap(root);

        assert map.size() == 5;
        assert map.get(20) == 1;
        assert map.get(9) == 1;
        assert map.get(14) == 20;
        assert map.get(2) == 9;
        assert map.get(18) == 9;
    }
}
