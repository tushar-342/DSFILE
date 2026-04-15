class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDist = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(words[i].equals(target)){
               int dist = Math.abs(i-startIndex);  //direct dist
               int currMin = Math.min(dist,n-dist); //wrapping min dist
                minDist = Math.min(minDist,currMin);
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
        
    }
}