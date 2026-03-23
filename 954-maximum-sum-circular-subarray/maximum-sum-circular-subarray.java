class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum=0;
        int maxSum=0;
        int maxRes=Integer.MIN_VALUE;

         int minSum=0;
        int minRes=Integer.MAX_VALUE;
        
        for(int i=0;i<nums.length;i++){
            totalSum+=nums[i];
        
            maxSum=Math.max(maxSum+nums[i],nums[i]);
            maxRes=Math.max(maxRes,maxSum);
        
            minSum=Math.min(minSum+nums[i],nums[i]);
            minRes=Math.min(minRes,minSum);
        }

        if(totalSum==minRes){
            return maxRes;
        }
        return Math.max(maxRes,totalSum-minRes);

        
    }
}
