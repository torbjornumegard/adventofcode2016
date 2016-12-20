
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lucka3 {

    public static List<Integer> threeIntegersFromLine(String line) {
        List<Integer> sides = new ArrayList<>();
        for (String side : line.split(" ")) {
            if (side.trim().isEmpty()) {
                continue;
            }
            sides.add(Integer.parseInt(side.trim()));
        }
        return sides;
    }

    public static int countBadTriangles(List<String> triangles) {
        int count = 0;
        for (String triangle : triangles) {
            List<Integer> sides = threeIntegersFromLine(triangle);

            if (sides.size() != 3) {
                throw new IllegalStateException("bad Triangle " + triangle);
            }

            if (sides.get(0) < sides.get(1) + sides.get(2)
                    && sides.get(1) < sides.get(0) + sides.get(2)
                    && sides.get(2) < sides.get(0) + sides.get(1)) {
                count++;
                System.out.println(triangle);
            }
        }
        return count;
    }

    public static List<String> readData(String filePath) {

        List<String> toReturn = new ArrayList<>();

        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("file not found " + filePath);
        }
        while (scanner.hasNext()) {
            toReturn.add(scanner.nextLine());
        }

        return toReturn;
    }

    public static List<String> readData2(String filePath) {

        List<String> toReturn = new ArrayList<>();

        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("file not found " + filePath);
        }
        while (scanner.hasNext()) {
            List<Integer> line1Numbers = threeIntegersFromLine(scanner.nextLine());
            List<Integer> line2Numbers = threeIntegersFromLine(scanner.nextLine());
            List<Integer> line3Numbers = threeIntegersFromLine(scanner.nextLine());
            toReturn.add(line1Numbers.get(0) + " " + line2Numbers.get(0) + " " + line3Numbers.get(0));
            toReturn.add(line1Numbers.get(1) + " " + line2Numbers.get(1) + " " + line3Numbers.get(1));
            toReturn.add(line1Numbers.get(2) + " " + line2Numbers.get(2) + " " + line3Numbers.get(2));
        }

        return toReturn;
    }

    public static void main(String[] args) {

        List<String> triangleStrings = readData2("c:/java/Christmas/src/lucka3data.txt");

        System.out.println(Lucka3.countBadTriangles(triangleStrings));
    }
}
