class Solution {
    int[] x = {-1,1,0,0};
        int[] y = {0,0,-1,1};
        boolean valid(int i, int j, int n, int m){
            if(i<0 || i>=n || j<0 || j>=m){
            return false;
            }
           return true;
        }
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0, time = 0, i=0, j=0;
        for(i=0;i<n;i++){
            for(j=0;j<m;j++){
                if(grid[i][j]==2){
                    q.offer(new int[]{i,j});
                    // grid[i][j]= -2;
                }
                if(grid[i][j]==1){
                    fresh++;
                }
            }
        } 
        while(!q.isEmpty() && fresh>0){
            time++;
            int s = q.size();
            while(s-- >0){
                int [] p = q.poll();
                int r = p[0];
                int c = p[1];
                for(int k=0; k<4; k++){
                    int row = r + x[k];
                    int col = c + y[k];
                    if(valid(row,col,n,m) && grid[row][col]==1){
                        q.offer(new int[]{row,col});
                        grid[row][col]=2;
                        fresh--;
                    }
                }
            }
        }
        if(fresh>0){
            return -1;
        }
        return time;
        

        
    }
}