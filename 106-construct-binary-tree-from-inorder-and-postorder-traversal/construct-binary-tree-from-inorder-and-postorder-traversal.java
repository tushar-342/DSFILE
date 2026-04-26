class Solution {

    HashMap<Integer, Integer> in = new HashMap<>();
    int idx;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        for (int i = 0; i < inorder.length; i++) {
            in.put(inorder[i], i);
        }

        idx = postorder.length - 1;

        return fun(postorder, 0, inorder.length - 1);
    }

    public TreeNode fun(int[] postorder, int low, int high) {
        if (low > high) return null;

        TreeNode node = new TreeNode(postorder[idx]);
        idx--;

        int id = in.get(node.val);

        // IMPORTANT: right first, then left
        node.right = fun(postorder, id + 1, high);
        node.left = fun(postorder, low, id - 1);

        return node;
    }
}