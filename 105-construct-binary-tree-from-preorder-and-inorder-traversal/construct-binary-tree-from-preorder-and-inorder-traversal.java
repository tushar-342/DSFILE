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
      HashMap<Integer,Integer> in = new HashMap<>();
      int idx=0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    for(int i=0; i<inorder.length; i++){
        in.put(inorder[i],i);
    }
     return fun(preorder,0,inorder.length-1);
        
    }
    TreeNode fun(int[] preorder,int low, int high){
        if(low>high) return null; //BASE CASE

        TreeNode node = new TreeNode(preorder[idx]);
        idx++;

        int id = in.get(node.val);

        node.left = fun(preorder, low, id - 1); //LEFT
        node.right = fun(preorder, id + 1, high);

        return node;
    }
}