class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;

        while (low <= high) {
            int guess = (low + high) / 2;

            if (nums[guess] == target) return guess;

            // FIXED LINE
            if (nums[low] <= nums[guess]) { // left sorted
                if (target >= nums[low] && target < nums[guess]) {
                    high = guess - 1;
                } else {
                    low = guess + 1;
                }
            } else { // right sorted
                if (target > nums[guess] && target <= nums[high]) {
                    low = guess + 1;
                } else {
                    high = guess - 1;
                }
            }
        }

        return -1;
    }
}