class Solution {
    boolean res = true;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        for(int i=0; i<n; i++){
            if(colors[i] == -1){
                dfs(graph, i, 0, colors);
                if(!res) return false;
            }
        }
        return true;
}
    void dfs(int[][] graph, int node, int c, int[] colors){
        colors[node] = c;
        for(int j=0; j<graph[node].length; j++){
            int neigh = graph[node][j];
            if(colors[neigh] != -1 && colors[neigh] == c){
                res = false;
                return;
            }
            else if(colors[neigh] == -1){
                dfs(graph, neigh, 1-c, colors);
            }
        }
    }
}