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

    public static BinaryTreeNode parentMapToTree(Map<Integer, Integer> map) {
        Set<Integer> keys = map.keySet();

        // find root first
        Integer root = null;
        for (Integer val : map.values()) {
            if (!keys.contains(val)) {
                root = val;
                break;
            }
        }

        // maps unique `Integer` to it's own `BinaryTreeNode`
        HashMap<Integer, BinaryTreeNode> intNodeMap = new HashMap<>();

        // register every single unique integer (node) to the map
        for (Integer k : keys) {
            intNodeMap.put(k, new BinaryTreeNode(k));
        }
        for (Integer v : map.values()) {
            intNodeMap.put(v, new BinaryTreeNode(v));
        }

        for (Integer k : keys) {
            Integer val = map.get(k);

            if (intNodeMap.get(val).left == null) {
                intNodeMap.get(val).left = intNodeMap.get(k);
            } else { // as promised that the binary tree is valid
                intNodeMap.get(val).right = intNodeMap.get(k);
            }
        }

        return intNodeMap.get(root);
    }

    // tests
    public static void main(String[] args) {
        // treeToParentMap
        BinaryTreeNode root1 = new BinaryTreeNode(1);
        root1.left = new BinaryTreeNode(20);
        root1.right = new BinaryTreeNode(9);
        root1.left.left = new BinaryTreeNode(14);
        root1.right.left = new BinaryTreeNode(2);
        root1.right.right = new BinaryTreeNode(18);

        HashMap<Integer, Integer> map1 = treeToParentMap(root1);

        assert map1.size() == 5;
        assert map1.get(20) == 1;
        assert map1.get(9) == 1;
        assert map1.get(14) == 20;
        assert map1.get(2) == 9;
        assert map1.get(18) == 9;

        // parentMapToTree
        Map<Integer, Integer> map2 = new HashMap<>();
        map2.put(20, 1);
        map2.put(9, 1);
        map2.put(14, 20);
        map2.put(2, 9);
        map2.put(18, 9);

        BinaryTreeNode root2 = parentMapToTree(map2);

        Map<Integer, Integer> rebuilt = new HashMap<>();
        collect(root2, rebuilt);

        assert rebuilt.equals(map2);
    }

    static void collect(BinaryTreeNode node, Map<Integer, Integer> map) {
        if (node == null)
            return;

        if (node.left != null) {
            map.put(node.left.key, node.key);
            collect(node.left, map);
        }
        if (node.right != null) {
            map.put(node.right.key, node.key);
            collect(node.right, map);
        }
    }
}
