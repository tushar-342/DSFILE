
class Solution {

    void bfs(int root, List<Integer>[] adj, int[] depth, int[][] up) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{root, 1, 0});
        depth[root] = 0;
        up[root][0] = 1;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0];
            int parent = curr[1];
            int d = curr[2];

            for (int child : adj[node]) {

                if (child != parent) {

                    depth[child] = d + 1;
                    up[child][0] = node;
                    q.offer(new int[]{child, node, d + 1});
                }

            }
        }
    }

    int jump(int node, int steps, int[][] up) {
        for (int j = 19; j >= 0; j--) {
            if (((steps >> j) & 1) == 1) {
                node = up[node][j];
            }
        }
        return node;
    }

    int getLCA(int u, int v, int[] depth, int[][] up) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        
        u = jump(u, depth[u] - depth[v], up);

        if (u == v) return u;

        int low = 1, high = depth[u];
        int lcaNode = 1; 

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            int parentU = jump(u, mid, up);
            int parentV = jump(v, mid, up);

            if (parentU == parentV) {
                lcaNode = parentU;
                high = mid - 1;
            } else {
                low = mid + 1; 
            }
        }

        return lcaNode;
    }

    long power(long base, long exp, int mod) {
        if (exp < 0) return 0;
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp % 2) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;

        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int[] depth = new int[n + 1];
        int[][] up = new int[n + 1][20];

        bfs(1, adj, depth, up);

        for (int j = 1; j < 20; j++) { // 2^j jumps
            for (int i = 1; i <= n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        int qLen = queries.length;
        int[] ans = new int[qLen];
        int MOD = 1000000007;

        for (int i = 0; i < qLen; i++) {

            int u = queries[i][0];
            int v = queries[i][1];

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            int lca = getLCA(u, v, depth, up);
            int L = depth[u] + depth[v] - 2 * depth[lca];

            ans[i] = (int) power(2, L - 1, MOD);
        }

        return ans;
    }
}
