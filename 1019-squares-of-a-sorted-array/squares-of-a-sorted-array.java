class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;

        int[] res = new int[n];

        int left = 0;
        int right = n - 1;

        int idx = n - 1;

        while (left <= right) {

            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                res[idx] = leftSquare;
                left++;
            } else {
                res[idx] = rightSquare;
                right--;
            }

            idx--;
        }

        return res;
    }

}