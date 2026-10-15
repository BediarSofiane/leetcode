//Definition for a binary tree node.
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

class L98ValidateBinarySearchTree {
    // Approach:
    // Intuition: compute recursively 3 numbers at the same time, whether the tree
    // is valid or not, tree max value and tree min value.
    // Time: O(n)
    // Space:O(logn) depth of the tree, for recursion method stack
    public boolean isValidBST(TreeNode root) {
        return validate(root)[0] == 1;

    }

    private int[] validate(TreeNode node) {
        // The result is an array that contains 3 things:
        // result[0] =1 if tree is valid, and 0 if tree is not valid,
        // result[1] = max key of the tree,
        // result[2]= min key of the tree
        int[] result = new int[3];
        if (node == null) {
            result[0] = 1;
            return result;
        }
        // Recursion relation is: valid(node) = valid(node.left) && node.key >
        // max(node.left) && valide(node.right) && node.key< min(node.right)
        int[] leftResult = validate(node.left);
        int[] rightResult = validate(node.right);
        if (leftResult[0] == 1 && rightResult[0] == 1
                && (node.right == null || node.val < rightResult[2])
                && (node.left == null || node.val > leftResult[1])) {
            result[0] = 1;
            result[1] = node.right == null ? node.val : rightResult[1];
            result[2] = node.left == null ? node.val : leftResult[2];
        }
        return result;
    }
}