class Solution {
    public boolean check(int[] nums) {
        int peek=0, n=nums.length;
        for(int i=0; i<n; i++){
            if(nums[i] > nums[(i + 1) % n]){
                peek++;
            }
        }
      return peek<=1;
    }
}