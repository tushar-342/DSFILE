class Solution {
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return dfs(arr,start,visited);
        
    }
    public boolean dfs(int[] arr, int i,boolean[] visited){
        if(i<0 || i>=arr.length || visited[i]) return false;
        if(arr[i]==0) return true;
        visited[i] =true;
        boolean left = dfs(arr, i-arr[i],visited);
        boolean right = dfs(arr, i+arr[i],visited);
        return left||right;
    }
}