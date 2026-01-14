import java.util.*;

class Solution {

    static class Event {
        double y;
        int type;   // +1 = square start, -1 = square end
        double x1, x2;

        Event(double y, int type, double x1, double x2) {
            this.y = y;
            this.type = type;
            this.x1 = x1;
            this.x2 = x2;
        }
    }

    public double separateSquares(int[][] squares) {

        List<Event> events = new ArrayList<>();

        // Step 1: Build sweep events
        for (int[] s : squares) {
            double x = s[0], y = s[1], l = s[2];
            events.add(new Event(y, 1, x, x + l));
            events.add(new Event(y + l, -1, x, x + l));
        }

        // Step 2: Sort events by y
        events.sort(Comparator.comparingDouble(e -> e.y));

        List<double[]> active = new ArrayList<>();
        List<double[]> slabs = new ArrayList<>();

        double totalArea = 0;
        double prevY = events.get(0).y;
        int i = 0;

        // Step 3: Sweep line
        while (i < events.size()) {
            double currY = events.get(i).y;
            double height = currY - prevY;

            if (height > 0 && !active.isEmpty()) {
                double width = unionXLength(active);
                double area = width * height;
                slabs.add(new double[]{prevY, currY, width, area});
                totalArea += area;
            }

            // Process all events at same y
            while (i < events.size() && events.get(i).y == currY) {
                Event e = events.get(i);
                if (e.type == 1) {
                    active.add(new double[]{e.x1, e.x2});
                } else {
                    removeInterval(active, e.x1, e.x2);
                }
                i++;
            }
            prevY = currY;
        }

        // Step 4: Find y where area below = area above
        double half = totalArea / 2.0;
        double accumulated = 0;

        for (double[] slab : slabs) {
            if (accumulated + slab[3] >= half) {
                return slab[0] + (half - accumulated) / slab[2];
            }
            accumulated += slab[3];
        }

        return prevY;
    }

    // Union length of x-intervals
    private double unionXLength(List<double[]> intervals) {
        intervals.sort(Comparator.comparingDouble(a -> a[0]));

        double length = 0;
        double start = intervals.get(0)[0];
        double end = intervals.get(0)[1];

        for (int i = 1; i < intervals.size(); i++) {
            double s = intervals.get(i)[0];
            double e = intervals.get(i)[1];

            if (s > end) {
                length += end - start;
                start = s;
                end = e;
            } else {
                end = Math.max(end, e);
            }
        }
        length += end - start;
        return length;
    }

    private void removeInterval(List<double[]> active, double x1, double x2) {
        for (int i = 0; i < active.size(); i++) {
            if (active.get(i)[0] == x1 && active.get(i)[1] == x2) {
                active.remove(i);
                return;
            }
        }
    }
}
