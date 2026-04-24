class Solution {
    public void recoverTree(TreeNode root) {
        fun(root);

        if (galat == 1) {
            int tmp = g1First.val;
            g1First.val = g1Second.val;
            g1Second.val = tmp;
        } else {
            int tmp = g1First.val;
            g1First.val = g2Second.val;
            g2Second.val = tmp;
        }
    }

    int galat = 0;
    TreeNode prev = null;
    TreeNode g1First = null;
    TreeNode g1Second = null;
    TreeNode g2First = null;   // kept as in your code (not really needed)
    TreeNode g2Second = null;

    void fun(TreeNode root){
        if(root == null){
            return;
        }

        fun(root.left);   // ✅ fixed syntax

        // INORDER CHECK
        if(prev != null){
            if(root.val < prev.val){
                if(galat == 0){
                    g1First = prev;
                    g1Second = root;
                } else {
                    g2First = prev;     // kept as per your structure
                    g2Second = root;
                }
                galat++;
            }
        }

        prev = root;   // ✅ correct placement

        fun(root.right);
    }
}