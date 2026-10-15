
//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


class Solution {
    //Intuition: Go over the tree, for each node, compare bothe nodes values to current node value. multiple situations are possible:
    //1. both are smaller than current value => select current as the temporary result and go left.
    //2. both are greater than current value => select current as the temporary result and go right.
    //3. One is smaller and the other is greater => no way to go deeper, return current node as the final result.
    //4. at least one of them is equal to current value => no way to go deeper, return current node as the final result.    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;
        int min = (int) Math.min(q.val, p.val);
        int max = (int) Math.max(q.val, p.val);
        while (current != null) {
            if (current.val == p.val || current.val == q.val || current.val < max && current.val > min) {
                return current;
            } else {
                if (current.val > p.val) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }
        return root;
    }
}