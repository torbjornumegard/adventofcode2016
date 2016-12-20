package lucka13;

import java.util.*;

public class Lucka13 {

    int[][] maze = new int[99][99];

    int designersNumber;
    int bestSolvSteps = 9999;
    // cache lookups 
    private Map<Integer, Boolean> isOpenSpace = new HashMap<>();

    public Lucka13(int n) {
        designersNumber = n;
    }

    public boolean isOpenSpace(int x, int y) {
        int key = (x + 1) * 10000 + y;
        Boolean isOpen = isOpenSpace.get(key);
        if (isOpen == null) {
            int sum = x * x + 3 * x + 2 * x * y + y + y * y + designersNumber;
            String binString = Integer.toBinaryString(sum);
            int count1bit = count1Bit(binString);
            boolean result = count1bit % 2 == 0;
            isOpenSpace.put(key, result);
            return result;
        } else {
            return isOpen;
        }
    }

    private int count1Bit(String binString) {
        int count = 0;
        for (char c : binString.toCharArray()) {
            if (c == '1') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        Lucka13 lucka13 = new Lucka13(10);
        lucka13.printMaze(10);
        System.out.println(lucka13.solve(7, 4, 1, 1));

        lucka13 = new Lucka13(1350);
        lucka13.printMaze(50);

        System.out.println(lucka13.solve(31, 39, 1, 1));

    }

    public void printMaze(int size) {

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                boolean isOpen = isOpenSpace(x, y);
                System.out.print(isOpen ? "." : "#");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int solve(int goal_x, int goal_y, int x, int y) {
        return solve(goal_x, goal_y, x, y, "");
    }

    /**
     * @return minimum number of steps to solve
     */
    private int solve(int goal_x, int goal_y, int x, int y, String visited) {

        if (goal_x == x && goal_y == y) {
            int count = countStepsFromVisited(visited);
            if (count < bestSolvSteps) {
                bestSolvSteps = count;
            }
            return count;
        }
        
        if (x < 0 || y < 0
                || x > 45 || y > 45
                || visited.contains("_" + x + ":" + y + "_")
                || !isOpenSpace(x, y)
                || countStepsFromVisited(visited) > bestSolvSteps) {
            return 99999;
        }

        visited += "_" + x + ":" + y + "_";

        int r1 = solve(goal_x, goal_y, x, y + 1, visited);
        int r3 = solve(goal_x, goal_y, x + 1, y, visited);
        int r2 = solve(goal_x, goal_y, x, y - 1, visited);
        int r4 = solve(goal_x, goal_y, x - 1, y, visited);

        int min = Math.min(Math.min(r1, r2), Math.min(r3, r4));
        if (min < bestSolvSteps) {
            bestSolvSteps = min;
        }
        return min;
    }

    private int countStepsFromVisited(String visited) {
        int count = 0;
        for (char c : visited.toCharArray()) {
            if (c == ':') {
                count++;
            }
        }
        return count;
    }

}
