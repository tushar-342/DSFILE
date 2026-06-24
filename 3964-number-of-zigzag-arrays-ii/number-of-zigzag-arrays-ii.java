class Solution {
    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        // n = 1
        if (n == 1) {
            return m;
        }

        long[][] trans = new long[size][size];

        // up[i] -> down[j] where j > i
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                trans[m + j][i] = 1;
            }
        }

        // down[i] -> up[j] where j < i
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                trans[j][m + i] = 1;
            }
        }

        long[][] power = matrixPower(trans, n - 1);

        long[] start = new long[size];

        // length = 1
        for (int i = 0; i < m; i++) {
            start[i] = 1;       // up state
            start[m + i] = 1;   // down state
        }

        long ans = 0;

        for (int i = 0; i < size; i++) {
            long ways = 0;

            for (int j = 0; j < size; j++) {
                ways = (ways + power[i][j] * start[j]) % MOD;
            }

            ans = (ans + ways) % MOD;
        }

        return (int) ans;
    }

    private long[][] matrixPower(long[][] mat, long exp) {
        int n = mat.length;

        long[][] result = new long[n][n];

        for (int i = 0; i < n; i++) {
            result[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, mat);
            }

            mat = multiply(mat, mat);
            exp >>= 1;
        }

        return result;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;

        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;

                for (int j = 0; j < n; j++) {
                    if (B[k][j] == 0) continue;

                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }

        return C;
    }
}