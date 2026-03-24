class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int ans = 0, sum = 0;
        HashMap<Integer,Integer> f = new HashMap<>();
        f.put(0 , 1);
        for(int i = 0; i<n; i++){
            sum += nums[i];
            int rem = sum%k;
            if(rem<0){
            rem = rem + k;
            }
            ans  += f.getOrDefault(rem, 0);
            f.put(rem, f.getOrDefault(rem,0)+1);
        }
            
    return ans;
        }
    }
