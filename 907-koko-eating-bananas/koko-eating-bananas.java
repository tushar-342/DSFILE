class Solution {
    public long fun(int[] piles, int n, int speed){
        long h=0;
        for(int i=0; i<n; i++){
            h= h + piles[i]/speed;
            if(piles[i]%speed != 0){
                h++;
            }
        }
             return h;
    }
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int low=1, high=0;
        for(int i=0;i<n;i++){
            high=Math.max(high,piles[i]);
        }
        int res = high;
        
        while(low<=high){
            int guess = low+(high-low)/2;
            long hours = fun(piles,n,guess);
            if(hours>h){
                low=guess+1;
            }else{
                res = guess;
                high = guess-1;
            }
        }
        return res;
    }
}