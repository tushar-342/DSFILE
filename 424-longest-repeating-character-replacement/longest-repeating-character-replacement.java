class Solution {
    public int characterReplacement(String s, int k) {
        int[] f = new int[26];
        int low = 0, res=0, maxCount=0;
        for(int high=0; high<s.length(); high++){
            char ch = s.charAt(high);
            f[ch - 'A']++;
            maxCount = Math.max(maxCount, f[ch - 'A']);
            while((high-low+1)-maxCount > k){
                f[s.charAt(low) - 'A']--;
                low++;
            }
            res = Math.max(res, high-low+1);
        }        
        return res;
    }
}