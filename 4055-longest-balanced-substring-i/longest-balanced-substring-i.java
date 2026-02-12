class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLength = 0;

        // Try every starting index
        for (int i = 0; i < n; i++) {

            int[] freq = new int[26];  // frequency array
            int distinct = 0;          // number of distinct characters
            int maxFreq = 0;           // maximum frequency in current substring

            // Expand substring
            for (int j = i; j < n; j++) {
                int index = s.charAt(j) - 'a';

                freq[index]++;

                // If first time seeing this character
                if (freq[index] == 1) {
                    distinct++;
                }

                // Update max frequency
                maxFreq = Math.max(maxFreq, freq[index]);

                int length = j - i + 1;

                // Check if balanced
                if (maxFreq * distinct == length) {
                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        return maxLength;
    }
}
