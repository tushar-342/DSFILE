import java.util.*;

class Solution {
    static class Robot {
        int pos, health, index;
        char dir;

        Robot(int pos, int health, char dir, int index) {
            this.pos = pos;
            this.health = health;
            this.dir = dir;
            this.index = index;
        }
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Robot[] robots = new Robot[n];

        // Step 1: Create robot objects
        for (int i = 0; i < n; i++) {
            robots[i] = new Robot(positions[i], healths[i], directions.charAt(i), i);
        }

        // Step 2: Sort by position
        Arrays.sort(robots, (a, b) -> a.pos - b.pos);

        Stack<Robot> stack = new Stack<>();

        // Step 3: Process collisions
        for (Robot curr : robots) {
            if (curr.dir == 'R') {
                stack.push(curr);
            } else {
                // curr.dir == 'L'
                while (!stack.isEmpty() && stack.peek().dir == 'R') {
                    Robot top = stack.peek();

                    if (top.health < curr.health) {
                        stack.pop(); // top dies
                        curr.health--; // curr loses 1 health
                    } else if (top.health > curr.health) {
                        top.health--; // top loses 1 health
                        curr.health = 0; // curr dies
                        break;
                    } else {
                        // equal health
                        stack.pop();
                        curr.health = 0;
                        break;
                    }
                }

                if (curr.health > 0) {
                    stack.push(curr);
                }
            }
        }

        // Step 4: Collect survivors
        List<Robot> survivors = new ArrayList<>(stack);

        // Step 5: Sort back by original index
        Collections.sort(survivors, (a, b) -> a.index - b.index);

        List<Integer> result = new ArrayList<>();
        for (Robot r : survivors) {
            result.add(r.health);
        }

        return result;
    }
}