class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        
        int[] freq = new int[128];
        
        // Store frequency of characters in t
        for (char c : t.toCharArray()) {
            freq[c]++;
        }
        
        int left = 0, right = 0;
        int count = t.length();
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        
        while (right < s.length()) {
            
            // If character needed, decrease count
            if (freq[s.charAt(right)] > 0) {
                count--;
            }
            
            freq[s.charAt(right)]--;
            right++;
            
            // When all characters matched
            while (count == 0) {
                
                // Update minimum window
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                
                freq[s.charAt(left)]++;
                
                // If removing breaks condition
                if (freq[s.charAt(left)] > 0) {
                    count++;
                }
                
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE 
               ? "" 
               : s.substring(start, start + minLen);
    }
}
