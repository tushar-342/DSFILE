class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int low=0, high=n-1, res= -1;
        while(low<=high){
            int guess = (low+high)/2;
            if(arr[guess]<arr[guess+1]){
                low=guess+1;
            }else{
                res = guess;
                high = guess-1;
            }
        }
        return res;
    }
}