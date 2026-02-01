class Solution {
    public int minimumCost(int[] nums) {
        int n = nums.length;

        int firstCost = nums[0];
        int minSecond = nums[1];
        int ans = Integer.MAX_VALUE;

        for (int j = 2; j < n; j++) {
            ans = Math.min(ans, firstCost + minSecond + nums[j]);
            minSecond = Math.min(minSecond, nums[j]);
        }

        return ans;
    }
}
