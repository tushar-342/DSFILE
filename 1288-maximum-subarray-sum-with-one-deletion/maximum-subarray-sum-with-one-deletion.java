class Solution {
    public int maximumSum(int[] arr) {
        int nodelete = arr[0];                 // max sum without deletion
        int onedelete = 0;                     // max sum with one deletion
        int res = arr[0];

        for (int i = 1; i < arr.length; i++) {
            onedelete = Math.max(onedelete + arr[i], nodelete);
            nodelete = Math.max(nodelete + arr[i], arr[i]);

            res = Math.max(res, Math.max(nodelete, onedelete));
        }

        return res;
    }
}