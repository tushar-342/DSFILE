class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int r, int c, int k) {

        int top = r;
        int bottom = r + k - 1;

        while (top < bottom) {
            for (int j = 0; j < k; j++) {
                int temp = grid[top][c + j];
                grid[top][c + j] = grid[bottom][c + j];
                grid[bottom][c + j] = temp;
            }
            top++;
            bottom--;
        }

        return grid;
    }
}
