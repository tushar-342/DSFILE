class Solution {

    class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }

            int mid = start + (end - start) / 2;

            if (idx <= mid) {
                update(2 * node, start, mid, idx, val);
            } else {
                update(2 * node + 1, mid + 1, end, idx, val);
            }

            tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
        }

        int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return 0;
            }

            if (l <= start && end <= r) {
                return tree[node];
            }

            int mid = start + (end - start) / 2;

            return Math.max(
                query(2 * node, start, mid, l, r),
                query(2 * node + 1, mid + 1, end, l, r)
            );
        }
    }

    public List<Boolean> getResults(int[][] queries) {

        int maxX = 0;

        for (int[] q : queries) {
            maxX = Math.max(maxX, q[1]);
        }

        TreeSet<Integer> obstacles = new TreeSet<>();

        obstacles.add(0);
        obstacles.add(maxX + 1);

        SegmentTree seg = new SegmentTree(maxX + 2);

        seg.update(1, 0, maxX + 1, maxX + 1, maxX + 1);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {

            if (q[0] == 1) {

                int x = q[1];

                int left = obstacles.lower(x);
                int right = obstacles.higher(x);

                obstacles.add(x);

                seg.update(
                    1,
                    0,
                    maxX + 1,
                    x,
                    x - left
                );

                seg.update(
                    1,
                    0,
                    maxX + 1,
                    right,
                    right - x
                );

            } else {

                int x = q[1];
                int sz = q[2];

                int prev = obstacles.floor(x);

                int bestGap = seg.query(
                    1,
                    0,
                    maxX + 1,
                    0,
                    prev
                );

                bestGap = Math.max(
                    bestGap,
                    x - prev
                );

                ans.add(bestGap >= sz);
            }
        }

        return ans;
    }
}