
import java.util.*;

public class Lucka1 {

    int x = 0;
    int y = 0;
    Facing facing = Facing.UP;

    public int calcDistance(String instructions) {

        for (String s : instructions.split(",")) {
            String instruction = s.trim();
            String rotate = instruction.substring(0, 1);

            if (rotate.equals("L")) {
                facing = rotateLeft(facing);
            } else if (rotate.equals("R")) {
                facing = rotateRight(facing);
            } else {
                throw new IllegalArgumentException("strange! " + instruction);
            }

            int count = Integer.parseInt(instruction.substring(1));

            if (facing == Facing.UP) {
                y += count;
            } else if (facing == Facing.DOWN) {
                y -= count;
            } else if (facing == Facing.RIGHT) {
                x += count;
            } else if (facing == Facing.LEFT) {
                x -= count;
            }

        }

        return Math.abs(x) + Math.abs(y);
    }

    public int visitTwiceCheck(String instructions) {

        Set<String> visited = new HashSet<String>();
        visited.add("0_0");

        for (String s : instructions.split(",")) {
            String instruction = s.trim();
            String rotate = instruction.substring(0, 1);

            if (rotate.equals("L")) {
                facing = rotateLeft(facing);
            } else if (rotate.equals("R")) {
                facing = rotateRight(facing);
            } else {
                throw new IllegalArgumentException("strange! " + instruction);
            }

            int count = Integer.parseInt(instruction.substring(1));

            for (int i = 0; i < count; i++) {
                if (facing == Facing.UP) {
                    y ++;
                } else if (facing == Facing.DOWN) {
                    y --;
                } else if (facing == Facing.RIGHT) {
                    x ++;
                } else if (facing == Facing.LEFT) {
                    x --;
                }

                if (visited.contains(x + "_" + y)) {
                    System.out.println(x + "_" + y);
                    return Math.abs(x) + Math.abs(y);
                } else {
                    visited.add(x + "_" + y);
                    System.out.println(x + "_" + y);
                }
            }

        }

        return -1; // not found;
    }

    public static void main(String[] args) {

        System.out.println((new Lucka1()).calcDistance("R2, L3"));
        System.out.println((new Lucka1()).calcDistance("R2, R2, R2"));
        System.out.println((new Lucka1()).calcDistance("R5, L5, R5, R3"));
        System.out.println((new Lucka1()).calcDistance("R2, L1, R2, R1, R1, L3, R3, L5, L5, L2, L1, R4, R1, R3, L5, L5, R3, L4, L4, R5, R4, R3, L1, L2, R5, R4, L2, R1, R4, R4, L2, L1, L1, R190, R3, L4, R52, R5, R3, L5, R3, R2, R1, L5, L5, L4, R2, L3, R3, L1, L3, R5, L3, L4, R3, R77, R3, L2, R189, R4, R2, L2, R2, L1, R5, R4, R4, R2, L2, L2, L5, L1, R1, R2, L3, L4, L5, R1, L1, L2, L2, R2, L3, R3, L4, L1, L5, L4, L4, R3, R5, L2, R4, R5, R3, L2, L2, L4, L2, R2, L5, L4, R3, R1, L2, R2, R4, L1, L4, L4, L2, R2, L4, L1, L1, R4, L1, L3, L2, L2, L5, R5, R2, R5, L1, L5, R2, R4, R4, L2, R5, L5, R5, R5, L4, R2, R1, R1, R3, L3, L3, L4, L3, L2, L2, L2, R2, L1, L3, R2, R5, R5, L4, R3, L3, L4, R2, L5, R5"));

        System.out.println((new Lucka1()).visitTwiceCheck("R8, R4, R4, R8"));
        System.out.println((new Lucka1()).visitTwiceCheck("R2, L1, R2, R1, R1, L3, R3, L5, L5, L2, L1, R4, R1, R3, L5, L5, R3, L4, L4, R5, R4, R3, L1, L2, R5, R4, L2, R1, R4, R4, L2, L1, L1, R190, R3, L4, R52, R5, R3, L5, R3, R2, R1, L5, L5, L4, R2, L3, R3, L1, L3, R5, L3, L4, R3, R77, R3, L2, R189, R4, R2, L2, R2, L1, R5, R4, R4, R2, L2, L2, L5, L1, R1, R2, L3, L4, L5, R1, L1, L2, L2, R2, L3, R3, L4, L1, L5, L4, L4, R3, R5, L2, R4, R5, R3, L2, L2, L4, L2, R2, L5, L4, R3, R1, L2, R2, R4, L1, L4, L4, L2, R2, L4, L1, L1, R4, L1, L3, L2, L2, L5, R5, R2, R5, L1, L5, R2, R4, R4, L2, R5, L5, R5, R5, L4, R2, R1, R1, R3, L3, L3, L4, L3, L2, L2, L2, R2, L1, L3, R2, R5, R5, L4, R3, L3, L4, R2, L5, R5"));
    }

    Facing rotateLeft(Facing currentState) {
        if (currentState == Facing.UP) {
            return Facing.LEFT;
        }
        if (currentState == Facing.LEFT) {
            return Facing.DOWN;
        }
        if (currentState == Facing.DOWN) {
            return Facing.RIGHT;
        }
        if (currentState == Facing.RIGHT) {
            return Facing.UP;
        }
        throw new IllegalStateException();
    }

    Facing rotateRight(Facing currentState) {
        if (currentState == Facing.UP) {
            return Facing.RIGHT;
        }
        if (currentState == Facing.LEFT) {
            return Facing.UP;
        }
        if (currentState == Facing.DOWN) {
            return Facing.LEFT;
        }
        if (currentState == Facing.RIGHT) {
            return Facing.DOWN;
        }
        throw new IllegalStateException();
    }

    enum Facing {
        UP, DOWN, LEFT, RIGHT;
    }
}
