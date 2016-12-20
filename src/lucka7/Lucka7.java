/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lucka7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lucka7 {

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

        System.out.println(supportTls("abba[mnop]qrst"));
        System.out.println(supportTls("abcd[bddb]xyyx"));
        System.out.println(supportTls("aaaa[qwer]tyui"));
        System.out.println(supportTls("ioxxoj[asdfgh]zxcvbn"));
        System.out.println(solve(readData("c:/java/Christmas/src/lucka7/test_data.txt")));
        System.out.println(solve(readData("c:/java/Christmas/src/lucka7/data.txt"))); // 112 too high

    }

    private static int solve(List<String> inputLines) {

        int countTLSsupporting = 0;

        for (String s : inputLines) {
            if (supportTls(s)) {
                countTLSsupporting++;
            }
        }

        return countTLSsupporting;
    }

    private static boolean supportTls(String s) {

        boolean foundTLS = false;
        for (int i = 0; i < s.length() - 3; i++) {

            if (s.charAt(i)!=s.charAt(i + 1) && 
                    s.charAt(i) == s.charAt(i + 3) && s.charAt(i + 1) == s.charAt(i + 2)) {
                foundTLS = true;
                if (i > 0) {
                    int indexLeftBracket = s.substring(0, i ).lastIndexOf("[");
                    int indexRightBracket = s.substring(0, i ).lastIndexOf("]");
                    if (indexLeftBracket > indexRightBracket) {
                        return false;
                    }
                }
                System.out.println(s + " " + s.substring(i, i+4));
            }
        }

        
        return foundTLS;
    }

}
