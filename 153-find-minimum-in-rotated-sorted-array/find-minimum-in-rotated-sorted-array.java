class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {   // ✅ IMPORTANT CHANGE
            int guess = (low + high) / 2;

            if (nums[guess] > nums[high]) {
                low = guess + 1;
            } else {
                high = guess;  // ✅ NOT guess - 1
            }
        }

        return nums[low];
    }
}