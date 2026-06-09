class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        int minEl = Integer.MAX_VALUE;
        int maxEl = Integer.MIN_VALUE;

        for(int num : nums){
            maxEl = Math.max(maxEl , num);
            minEl = Math.min(minEl, num);
        } 
         return (long) k * (maxEl - minEl);
    }
}