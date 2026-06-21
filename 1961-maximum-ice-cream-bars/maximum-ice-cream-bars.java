class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int count = 0;
        Arrays.sort(costs);
        for(int c : costs) {
            if(coins < c) {
                break;
            }
                coins -= c;
                count++;
        }
        return count;
    }
}