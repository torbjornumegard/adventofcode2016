package lucka9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lucka9 {

    static String decrypt(String input) {

        int posLeftParen = input.indexOf("(");

        if (posLeftParen >= 0) {

            String markerString = input.substring(posLeftParen, input.indexOf(")") + 1);

            int howManyCharacters = Integer.parseInt(markerString.substring(1, markerString.indexOf("x")));
            int times = Integer.parseInt(markerString.substring(markerString.indexOf("x") + 1, markerString.length() - 1));

            int posRightParen = input.indexOf(")");

            String stringToRepeat = input.substring(posRightParen + 1, Math.min(posRightParen + 1 + howManyCharacters, input.length()));

            String respeatedString = "";
            for (int i = 0; i < times; i++) {
                respeatedString += stringToRepeat;
            }

            String s_1 = input.substring(0, posLeftParen);
            String s_2 = decrypt(input.substring(posRightParen + howManyCharacters + 1));
            return s_1 + respeatedString + s_2;

        }

        return input;
    }

    static String decrypt2(String input) {

        int posLeftParen = input.indexOf("(");

        if (posLeftParen >= 0) {

            String markerString = input.substring(posLeftParen, input.indexOf(")") + 1);

            int howManyCharacters = Integer.parseInt(markerString.substring(1, markerString.indexOf("x")));
            int times = Integer.parseInt(markerString.substring(markerString.indexOf("x") + 1, markerString.length() - 1));

            int posRightParen = input.indexOf(")");

            String stringToRepeat = input.substring(posRightParen + 1, Math.min(posRightParen + 1 + howManyCharacters, input.length()));

            stringToRepeat = decrypt2(stringToRepeat);

            String respeatedString = "";
            for (int i = 0; i < times; i++) {
                respeatedString += stringToRepeat;
            }

            String s_1 = input.substring(0, posLeftParen);
            String s_2 = decrypt(input.substring(posRightParen + howManyCharacters + 1));
            return s_1 + respeatedString + s_2;

        }

        return input;
    }

    public static String readDataOneLine(String filePath) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("could not read " + filePath);
        }
        return scanner.nextLine().trim();
    }

    public static void main(String[] args) {

        // see test class also
        System.out.println(decrypt(readDataOneLine("c:/java/Christmas/src/lucka9/data.txt")).length());
        System.out.println(findDecryptLength(readDataOneLine("c:/java/Christmas/src/lucka9/data.txt"))); // 115536 too low // 11107527530 too high

    }

    static long findDecryptLength(String input) {

        if (input.contains("(")) {

            int posLeftParen = input.indexOf("(");

            String before = input.substring(0, posLeftParen);

            String markerString = input.substring(posLeftParen, input.indexOf(")") + 1);
            int howManyCharacters = Integer.parseInt(markerString.substring(1, markerString.indexOf("x")));
            int times = Integer.parseInt(markerString.substring(markerString.indexOf("x") + 1, markerString.length() - 1));

            String repeatedString = input.substring(input.indexOf(")") + 1, input.indexOf(")") + 1 + howManyCharacters);

            String tail = input.substring(input.indexOf(")") + 1 + howManyCharacters);
            return before.length() + findDecryptLength(repeatedString) * (times) + findDecryptLength(tail);
        }
        return input.length();
    }

}
