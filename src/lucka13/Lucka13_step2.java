package lucka13;

import java.util.*;

public class Lucka13_step2 {

    int[][] maze = new int[99][99];

    int designersNumber;
    int maxSteps = 50;
    
    // cache lookups 
    private Map<Integer, Boolean> isOpenSpace = new HashMap<>();

    Set<Integer> global_vivisted = new HashSet<>();

    public Lucka13_step2(int n) {
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

        Lucka13_step2 lucka13 = new Lucka13_step2(1350);

        System.out.println(lucka13.solve(50,1,1));

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

    public int solve(int max, int x, int y) {
        global_vivisted.clear();
        this.maxSteps = max;
        solve(x, y, "");
        return global_vivisted.size();
    }

    /**
     * @return minimum number of steps to solve
     */
    private void solve(int x, int y, String visited) {

        if (x < 0 || y < 0
                || x > 45 || y > 45
                || visited.contains("_" + x + ":" + y + "_")
                || !isOpenSpace(x, y)
                || countStepsFromVisited(visited) > this.maxSteps) {
            return;
        }

        visited += "_" + x + ":" + y + "_";

        this.global_vivisted.add((x+1)*10000 + y);

        solve(x, y + 1, visited);
        solve(x + 1, y, visited);
        solve(x, y - 1, visited);
        solve(x - 1, y, visited);

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
