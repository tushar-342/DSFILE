import java.util.*;

class Solution {
    public int[][] merge(int[][] a) {
        int n = a.length;
        List<int[]> res = new ArrayList<>();

        // Sort based on start time
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));

        int start = a[0][0];
        int end = a[0][1];

        for (int i = 1; i < n; i++) {
            int s = a[i][0];
            int e = a[i][1];

            if (end >= s) { // overlap
                end = Math.max(end, e);
            } else {
                res.add(new int[]{start, end});
                start = s;
                end = e;
            }
        }

        // push last interval
        res.add(new int[]{start, end});

        // Convert List to array
        return res.toArray(new int[res.size()][]);
    }
}
