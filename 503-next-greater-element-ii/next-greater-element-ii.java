import java.util.*;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);   // default = -1

        Stack<Integer> st = new Stack<>();

        // Traverse twice (circular)
        for (int i = 2 * n - 1; i >= 0; i--) {
            int idx = i % n;

            // Remove smaller elements
            while (!st.isEmpty() && st.peek() <= nums[idx]) {
                st.pop();
            }

            // Fill result only in first pass
            if (i < n) {
                if (!st.isEmpty()) {
                    res[idx] = st.peek();
                }
            }

            // Push current element
            st.push(nums[idx]);
        }

        return res;
    }
}