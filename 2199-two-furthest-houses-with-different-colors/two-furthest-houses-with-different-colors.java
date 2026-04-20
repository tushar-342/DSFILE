class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int i=0, j=n-1;
        while(colors[i]==colors[j]){
            i++;
        }
        int res1 = (n-1-i);
         i=n-1;
         j=0; //RESET POINTER
         while(colors[j]==colors[i]){
            i--;
        }
        int res2 = i;
        return Math.max(res1,res2);
        
    }
}