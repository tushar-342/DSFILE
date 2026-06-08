class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] res = new int[n];

        int i = 0, j = n-1; //for original array
        int i_ = 0, j_ = n-1; // for res array

        while(i<n && j>=0){
            if(nums[i] < pivot){
                res[i_] = nums[i];
                i_++;
            }
            
            if(nums[j] > pivot){
                res[j_] = nums[j];
                j_--;
            }

            i++;
            j--;
        }

        while(i_ <= j_){
            res[i_] = pivot;
            i_++;
        }
        return res;
        
    }
}