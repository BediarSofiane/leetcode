
// Definition for a binary tree node.

import java.util.HashMap;
import java.util.Map;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class L105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    Map<Integer, Integer> inorderPositions = new HashMap();
    // Intuition: Since In preorder we see the root then the left branch then the
    // right branch and in inorder we see the left branch than root than the right
    // branch, we can select the root from preorder then look for it in inorder, We
    // know that what's at its left is its left branch and what's at its right is
    // its right branch.
    // If it has a left (is not the left most index of the search
    // range in inorder), this means that its left will be its next in preorder,
    // otherwise its left is null.
    // If it has a right (is not the right most index of the search range in
    // inorder), this means that its right is the next element of preorder to its
    // position in inorder. Otherwise, its right is null.
    //Time: O(n)
    //Space: O(n)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderPositions.put(inorder[i], i);
        }
        TreeNode root = new TreeNode();
        root.val = preorder[0];
        compute(root, preorder, inorder, 0, preorder.length - 1, 0);
        return root;

    }

    private void compute(TreeNode node, int[] preorder, int[] inorder, int inorderStart, int inorderEnd,
            int preorderIndex) {
        int i = inorderPositions.get(node.val);
        if (i > inorderStart) {
            TreeNode left = new TreeNode();
            left.val = preorder[preorderIndex + 1];
            node.left = left;
            compute(left, preorder, inorder, inorderStart, i - 1, preorderIndex + 1);
        }
        if (i < inorderEnd) {
            TreeNode right = new TreeNode();
            right.val = preorder[preorderIndex + i - inorderStart + 1];
            node.right = right;
            compute(right, preorder, inorder, i + 1, inorderEnd, preorderIndex + i - inorderStart + 1);
        }

    }
}