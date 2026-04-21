/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
         boolean LeftToRight = true;
        while(!q.isEmpty()){
            int lvlsize = q.size();
            List<Integer> tmp = new ArrayList<>(lvlsize);
            while(lvlsize-->0){
                TreeNode t = q.poll();
                if(LeftToRight){
                    tmp.add(t.val);
                }else{
                   tmp.add(0,t.val);
                }
                if(t.left!=null) q.offer(t.left);
                if(t.right!=null) q.offer(t.right);
            }
            res.add(tmp);
            LeftToRight =! LeftToRight; //TOGGLE
        }
        return res;
        
    }
}