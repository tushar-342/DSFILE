class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
       int[] prefix = new int[n];
       int[] suffix = new int[n];
       int[] ans = new int[n];
       //PREFIX SUM
       prefix[0] = nums[0];
       for(int i=1; i<n; i++){
        prefix[i] = prefix[i-1] + nums[i];
       }
       //SUFFIX SUM
       suffix[n-1] = nums[n-1];
       for(int i=n-2; i>=0; i--){
        suffix[i] = suffix[i+1] + nums[i];
       }
       //ans
       for(int i=0; i<n; i++){
        int leftSum = (i == 0) ? 0 : prefix[i-1];
        int rightSum = (i == n-1) ? 0 : suffix[i+1];
            ans[i] = Math.abs(leftSum - rightSum);
       }
        return ans;
    }
}