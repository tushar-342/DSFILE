import java.util.*;

class Solution {

    static class Edge {
        int to, cost;
        Edge(int t, int c) {
            to = t;
            cost = c;
        }
    }

    public int minCost(int n, int[][] edges) {
        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        // Build graph
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new Edge(v, w));       // normal
            graph[v].add(new Edge(u, 2 * w));   // reversed
        }

        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        dist[0] = 0;
        pq.offer(new long[]{0, 0}); // {cost, node}

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];

            if (d > dist[u]) continue;

            for (Edge e : graph[u]) {
                int v = e.to;
                long nd = d + e.cost;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new long[]{nd, v});
                }
            }
        }

        return dist[n - 1] == INF ? -1 : (int) dist[n - 1];
    }
}
