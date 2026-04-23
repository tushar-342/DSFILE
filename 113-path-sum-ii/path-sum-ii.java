class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> diary = new ArrayList<>();
        fun(root, 0, targetSum, diary);
        return res;
    }

    List<List<Integer>> res = new ArrayList<>();

    void fun(TreeNode root, int sum, int targetSum, List<Integer> diary) {
        if (root == null) {
            return;
        }

        sum += root.val;
        diary.add(root.val);  // ✅ correct add

        // LEAF NODE
        if (root.left == null && root.right == null) {
            if (sum == targetSum) {
                res.add(new ArrayList<>(diary)); // ✅ copy list
            }
        } else {
            fun(root.left, sum, targetSum, diary);   // ✅ pass diary
            fun(root.right, sum, targetSum, diary);
        }

        // ✅ BACKTRACK (only once, at the end)
        diary.remove(diary.size() - 1);
    }
}