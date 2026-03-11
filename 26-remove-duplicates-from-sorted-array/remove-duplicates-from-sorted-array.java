class Solution {
    public int removeDuplicates(int[] nums) {

        int officer = 0;   // pointer for unique elements
        int cm = 1;        // current pointer
        int res = 1;
        int n = nums.length;

        while (cm < n) {

            if (nums[cm] == nums[officer]) {
                cm++;
                continue;
            }

            nums[officer + 1] = nums[cm];
            officer++;
            cm++;
            res++;
        }

        return res;
    }
}



