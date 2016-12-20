
package lucka7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lucka7_2 {

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

//      System.out.println(supportABA("aba[bab]xyz"));
        System.out.println(supportSSL("aba[bab]xyz"));
        System.out.println(supportSSL("xyx[xyx]xyx"));
  //      System.out.println(supportABA("aaa[kek]eke"));
        System.out.println(supportSSL("aaa[kek]eke"));
    //    System.out.println(supportABA("zazbz[bzb]cdb"));
        System.out.println(supportSSL("zazbz[bzb]cdb"));
        System.out.println(solve(readData("c:/java/Christmas/src/lucka7/data.txt"))); // 209 too low

    }

    private static int solve(List<String> inputLines) {
        int countTLSsupporting = 0;
        for (String s : inputLines) {
            if (supportSSL(s)) {
                countTLSsupporting++;
            }
        }
        return countTLSsupporting;
    }

    private static boolean supportSSL(String s) {
        List<String> abaString_list = supportABA(s);
        return !abaString_list.isEmpty() && supportBAB(s, abaString_list);
    }

    static List<String> supportABA(String s) {
        List<String> toReturn = new ArrayList<>();
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) != s.charAt(i + 1) && s.charAt(i) == s.charAt(i + 2)) { 
                int indexLeftBracket = s.substring(0, i).lastIndexOf("[");
                int indexRightBracket = s.substring(0, i).lastIndexOf("]");
                if (!(indexLeftBracket > indexRightBracket)) {
                    toReturn.add(s.substring(i, i+3));
                }
            }
        }
        return toReturn;
    }

       private static boolean supportBAB(String input, List<String> abaString_list) {
          for (String s : abaString_list) {
              if (supportBAB(input, s)) return true;
          }
        return false;
    }
    
    private static boolean supportBAB(String input, String abaString) {
        if (abaString.length()!=3) throw new IllegalArgumentException();
        String babString = ""+abaString.charAt(1)+abaString.charAt(0)+abaString.charAt(1);
        for (int i = 1; i < input.length() - 3; i++) {
            if (input.substring(i, i+3).equals(babString)) {
                int indexLeftBracket = input.substring(0, i).lastIndexOf("[");
                int indexRightBracket = input.substring(0, i).lastIndexOf("]");
                if (indexLeftBracket > indexRightBracket) {
                    return true;
                }
            }
        }
        return false;
    }

}
