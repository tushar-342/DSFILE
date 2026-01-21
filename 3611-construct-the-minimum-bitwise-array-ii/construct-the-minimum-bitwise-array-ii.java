import java.util.List;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            
            // Special case for prime 2 as no x exists
            if (num == 2) {
                ans[i] = -1;
            } else {
                // Find the lowest zero bit to identify the rightmost block of ones
                int lowestZeroBit = 1;
                while ((num & lowestZeroBit) != 0) {
                    lowestZeroBit <<= 1;
                }
                
                // The bit to flip to 0 in nums[i] to get min ans[i] is 
                // the one just before the lowest zero bit.
                int bitToFlip = lowestZeroBit >> 1;
                ans[i] = num ^ bitToFlip;
            }
        }
        
        return ans;
    }
}
