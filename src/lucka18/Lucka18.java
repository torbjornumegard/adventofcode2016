package lucka18;

import java.util.*;

public class Lucka18 {

    List<String> tileRows;

    public Lucka18(String firstRow) {
        tileRows = new ArrayList<>();
        tileRows.add(firstRow);
    }

    public static void main(String[] args) {

        // test 1
        Lucka18 lucka = new Lucka18("..^^.");
        lucka.generateMoreRow(2);
        lucka.print(true);

        // test 2
        lucka = new Lucka18(".^^.^.^^^^");
        lucka.generateMoreRow(2);
        lucka.print(true);

        // solve I
        lucka = new Lucka18(".^^^^^.^^^..^^^^^...^.^..^^^.^^....^.^...^^^...^^^^..^...^...^^.^.^.......^..^^...^.^.^^..^^^^^...^.");
        lucka.generateMoreRow(39);
        lucka.print(true);

        // solve I
        lucka = new Lucka18(".^^^^^.^^^..^^^^^...^.^..^^^.^^....^.^...^^^...^^^^..^...^...^^.^.^.......^..^^...^.^.^^..^^^^^...^.");
        lucka.generateMoreRow(400000 - 1); // to low : 1995624
        lucka.print(false);
    }

    public void generateMoreRow(int numberOfNewRows) {

        for (int i = 0; i < numberOfNewRows; i++) {

            String prevRow = this.tileRows.get(tileRows.size() - 1);

            String newRow = "";
            for (int pos = 0; pos < prevRow.length(); pos++) {
                newRow += createChar(prevRow, pos);
            }
            tileRows.add(newRow);
        }
    }

    private String createChar(String prevRow, int pos) {

        boolean trap = isTrap(prevRow, pos - 1) && isTrap(prevRow, pos) && !isTrap(prevRow, pos + 1)
                || !isTrap(prevRow, pos - 1) && isTrap(prevRow, pos) && isTrap(prevRow, pos + 1)
                || isTrap(prevRow, pos - 1) && !isTrap(prevRow, pos) && !isTrap(prevRow, pos + 1)
                || !isTrap(prevRow, pos - 1) && !isTrap(prevRow, pos) && isTrap(prevRow, pos + 1);

        return trap ? "^" : ".";
    }

    private boolean isTrap(String prevRow, int i) {
        if (i < 0 || i >= prevRow.length()) {
            return false;
        }
        return '^' == prevRow.charAt(i);
    }

    private void print(boolean printEachRow) {
        int countSafe = 0;
        for (String s : this.tileRows) {
            if (printEachRow) {
                System.out.println(s);
            }
            for (char c : s.toCharArray()) {
                if (c == '.') {
                    countSafe++;
                }
            }
        }
        System.out.println("# safe:" + countSafe);
    }

}
