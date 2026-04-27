import java.util.*;

class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // directions: {row, col}
        int[][][] dirs = {
            {},
            {{0,-1},{0,1}},      // type 1
            {{-1,0},{1,0}},      // type 2
            {{0,-1},{1,0}},      // type 3
            {{0,1},{1,0}},       // type 4
            {{0,-1},{-1,0}},     // type 5
            {{0,1},{-1,0}}       // type 6
        };

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0,0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];

            // reached destination
            if (r == m-1 && c == n-1) return true;

            for (int[] d : dirs[grid[r][c]]) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[nr][nc])
                    continue;

                // check reverse connection
                for (int[] back : dirs[grid[nr][nc]]) {
                    if (nr + back[0] == r && nc + back[1] == c) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                        break;
                    }
                }
            }
        }
        return false;
    }
}