import java.util.*;

class Solution {
    public long minimumCost(
            String source,
            String target,
            String[] original,
            String[] changed,
            int[] cost
    ) {
        int n = source.length();
        long INF = (long) 1e18;

        // Group rules by length
        Map<Integer, Map<String, Map<String, Long>>> map = new HashMap<>();

        for (int i = 0; i < original.length; i++) {
            int len = original[i].length();
            map.putIfAbsent(len, new HashMap<>());
            map.get(len)
                .computeIfAbsent(original[i], k -> new HashMap<>())
                .merge(changed[i], (long) cost[i], Math::min);
        }

        // Floyd-Warshall on each length group
        Map<Integer, Map<String, Map<String, Long>>> dist = new HashMap<>();

        for (int len : map.keySet()) {
            Set<String> nodes = new HashSet<>();
            for (String s : map.get(len).keySet()) {
                nodes.add(s);
                nodes.addAll(map.get(len).get(s).keySet());
            }

            Map<String, Map<String, Long>> d = new HashMap<>();
            for (String a : nodes) {
                d.put(a, new HashMap<>());
                for (String b : nodes) {
                    d.get(a).put(b, a.equals(b) ? 0L : INF);
                }
            }

            for (String a : map.get(len).keySet()) {
                for (String b : map.get(len).get(a).keySet()) {
                    d.get(a).put(b, map.get(len).get(a).get(b));
                }
            }

            for (String k : nodes)
                for (String i : nodes)
                    for (String j : nodes)
                        d.get(i).put(
                            j,
                            Math.min(d.get(i).get(j),
                                     d.get(i).get(k) + d.get(k).get(j))
                        );

            dist.put(len, d);
        }

        // DP
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {

            // char match
            if (source.charAt(i) == target.charAt(i)) {
                dp[i] = dp[i + 1];
            }

            // substring operations
            for (int len : dist.keySet()) {
                if (i + len > n) continue;

                String s = source.substring(i, i + len);
                String t = target.substring(i, i + len);

                if (dist.get(len).containsKey(s)) {
                    long c = dist.get(len).get(s).getOrDefault(t, INF);
                    if (c < INF) {
                        dp[i] = Math.min(dp[i], c + dp[i + len]);
                    }
                }
            }
        }

        return dp[0] >= INF ? -1 : dp[0];
    }
}
