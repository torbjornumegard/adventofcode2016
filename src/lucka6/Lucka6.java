/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lucka6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lucka6 {

    public static List<String> readData(String filePath) {

        List<String> toReturn = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("could not read " + filePath);
        }

        while (scanner.hasNext()) {
            toReturn.add(scanner.nextLine().trim());
        }

        return toReturn;
    }

    public static void main(String[] args) {

       System.out.println(solve(readData("c:/java/Christmas/src/lucka6/data.txt")));
      // System.out.println(solve(readData("c:/java/Christmas/src/lucka6/test_data.txt")));

    }

    private static String solve(List<String> inputLines) {

        String toReturn = "";

        Map<Integer, List<Character>> letterNumberToList = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            letterNumberToList.put(i, new ArrayList<Character>());
        }

        for (String line : inputLines) {
            for (int i = 0; i < 8; i++) {
                Character c = line.charAt(i);
                List list = letterNumberToList.get(i);
                list.add(c);
            }
        }

        for (int i = 0; i < 8; i++) {
           // toReturn += mostCommonChar(letterNumberToList.get(i));
            toReturn += leastCommonChar(letterNumberToList.get(i));
        }

        return toReturn;
    }

    static Character mostCommonChar(List<Character> list) {

        int record = 0;
        Character best = list.get(0);

        for (int i = 0; i < list.size(); i++) {
            Character candidate = list.get(i);
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).equals(candidate)) {
                    count++;
                }
            }
            if (count>record) {
                best = candidate;
                record = count;
            }
        }

        return best;
    }
    
    
    static Character leastCommonChar(List<Character> list) {

        int record = 999999;
        Character best = list.get(0);

        for (int i = 0; i < list.size(); i++) {
            Character candidate = list.get(i);
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).equals(candidate)) {
                    count++;
                }
            }
            if (count<record) {
                best = candidate;
                record = count;
            }
        }

        return best;
    }

}
