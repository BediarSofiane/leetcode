import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class L106ConstructBinaryTreeFromInorderAndPostorderTraversal {
    Map<Integer, Integer> inorderPositions = new HashMap();
    // Intuition: Start by initializing the root with the last element of the array.
    // then recursively try to compute the indice of the current nodes left and
    // right elements, using the inorder array. This should be the rules to find
    // these indices:
    // By computing the positon in inorder, we have an idea of the size of his left branch and the size of his right branch.
    // If the size of his right branch is > 0, then his right is postOrder[indexInPostOrder - 1], otherwise null.
    // If the size of his left branch is > 0, then left position is postOrder[indexInPostOrder - lengthOfRightBranch - 1].
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderPositions.put(inorder[i], i);
        }
        TreeNode root = new TreeNode();
        root.val = postorder[postorder.length - 1];
        compute(root, inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
        return root;
    }

    private void compute(TreeNode node, int[] inorder, int[] postorder, int postOrderIndex, int inorderStart, int inorderEnd) {
        int inorderPosition = inorderPositions.get(node.val);
        if (inorderPosition < inorderEnd) {
            int leftIndex = postOrderIndex - 1;
            TreeNode right = new TreeNode();
            right.val = postorder[leftIndex];
            node.right = right;
            compute(right, inorder, postorder, leftIndex, inorderPosition + 1, inorderEnd);
        }
        if (inorderPosition > inorderStart) {
            int rightIndex = postOrderIndex - (inorderEnd - inorderPosition) - 1;
            TreeNode left = new TreeNode();
            left.val = postorder[rightIndex];
            node.left = left;
            compute(left, inorder, postorder, rightIndex, inorderStart, inorderPosition - 1);
        }
    }
}