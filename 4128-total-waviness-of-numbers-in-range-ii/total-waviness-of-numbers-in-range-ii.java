import java.util.*;

class Solution {

    private String s;
    private HashMap<String, long[]> memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long x) {
        if (x <= 0) return 0;

        s = String.valueOf(x);
        memo = new HashMap<>();

        long[] ans = dfs(0, true, false, -1, -1);
        return ans[1];
    }

    private long[] dfs(int pos, boolean tight, boolean started,
                       int prev2, int prev1) {

        if (pos == s.length()) {
            return new long[]{1, 0}; // {count, waviness}
        }

        String key = pos + "|" + tight + "|" + started +
                     "|" + prev2 + "|" + prev1;

        if (!tight && memo.containsKey(key)) {
            return memo.get(key);
        }

        int limit = tight ? s.charAt(pos) - '0' : 9;

        long count = 0;
        long waviness = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nTight = tight && (d == limit);

            if (!started && d == 0) {
                long[] nxt = dfs(pos + 1, nTight, false, -1, -1);

                count += nxt[0];
                waviness += nxt[1];
                continue;
            }

            if (!started) {
                long[] nxt = dfs(pos + 1, nTight, true, -1, d);

                count += nxt[0];
                waviness += nxt[1];
            }
            else if (prev2 == -1) {
                long[] nxt = dfs(pos + 1, nTight, true, prev1, d);

                count += nxt[0];
                waviness += nxt[1];
            }
            else {

                long add = 0;

                if ((prev1 > prev2 && prev1 > d) ||
                    (prev1 < prev2 && prev1 < d)) {
                    add = 1;
                }

                long[] nxt = dfs(pos + 1, nTight, true, prev1, d);

                count += nxt[0];
                waviness += nxt[1] + add * nxt[0];
            }
        }

        long[] res = new long[]{count, waviness};

        if (!tight) {
            memo.put(key, res);
        }

        return res;
    }
}