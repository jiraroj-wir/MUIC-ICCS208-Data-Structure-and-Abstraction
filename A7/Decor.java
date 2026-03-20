import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decor {

    public static BinaryTreeNode mkTree(List<Integer> postOrder, List<Integer> inOrder) {
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < inOrder.size(); i++) {
            indexMap.put(inOrder.get(i), i);
        }

        return helper(postOrder, 0, postOrder.size() - 1, inOrder, 0, inOrder.size() - 1, indexMap);
    }

    private static BinaryTreeNode helper(List<Integer> postOrder, int postLeft, int postRight, List<Integer> inOrder,
                                         int inLeft, int inRight, Map<Integer, Integer> indexMap) {
        if (postLeft > postRight || inLeft > inRight) {
            return null;
        }

        Integer rootVal = postOrder.get(postRight);
        BinaryTreeNode root = new BinaryTreeNode(rootVal);

        int rootIdx = indexMap.get(rootVal);
        int leftTreeSize = rootIdx - inLeft;

        root.left = helper(postOrder, postLeft, postLeft + leftTreeSize - 1, inOrder, inLeft, rootIdx - 1, indexMap);
        root.right = helper(postOrder, postLeft + leftTreeSize, postRight - 1, inOrder, rootIdx + 1, inRight, indexMap);

        return root;
    }

    public static void main(String[] args) {}
}
