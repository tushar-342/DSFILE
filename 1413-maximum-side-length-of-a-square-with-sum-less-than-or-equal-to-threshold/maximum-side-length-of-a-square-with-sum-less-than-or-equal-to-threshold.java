class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        // Prefix sum matrix
        int[][] prefix = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = mat[i - 1][j - 1]
                             + prefix[i - 1][j]
                             + prefix[i][j - 1]
                             - prefix[i - 1][j - 1];
            }
        }

        int left = 0, right = Math.min(m, n);
        int ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (existsSquare(prefix, mid, threshold)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean existsSquare(int[][] prefix, int size, int threshold) {
        for (int i = size; i < prefix.length; i++) {
            for (int j = size; j < prefix[0].length; j++) {
                int sum = prefix[i][j]
                        - prefix[i - size][j]
                        - prefix[i][j - size]
                        + prefix[i - size][j - size];

                if (sum <= threshold) return true;
            }
        }
        return false;
    }
}