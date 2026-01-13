class Solution {
    public double separateSquares(int[][] squares) {

        double totalArea = 0.0;
        for (int[] sq : squares) {
            double l = sq[2];
            totalArea += l * l;
        }
        double half = totalArea / 2.0;

        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;

        for (int[] sq : squares) {
            double y = sq[1];
            double l = sq[2];
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        // Binary search for the answer
        for (int i = 0; i < 60; i++) {   // enough for 1e-5 precision
            double mid = (low + high) / 2.0;
            double areaBelow = 0.0;

            for (int[] sq : squares) {
                double y = sq[1];
                double l = sq[2];

                if (mid <= y) {
                    continue;
                } else if (mid >= y + l) {
                    areaBelow += l * l;
                } else {
                    areaBelow += l * (mid - y);
                }
            }

            if (areaBelow < half) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
