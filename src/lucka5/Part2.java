package lucka5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part2 {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        //System.out.println(solve("abc"));
        System.out.println(solve("uqwqemis"));

    }

    static String md5Encrypt(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hash = messageDigest.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte h : hash) {
                String hex = Integer.toHexString(0xFF & h);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    private static String solve(String input) {

        String passcode = "--------";

        int n = 0;
        while (passcode.contains("-")) {

            String candidate = md5Encrypt(input + n);
            if (candidate.substring(0, 5).equals("00000")) {
                char whichNumberChar = candidate.charAt(5);

                if (whichNumberChar >= '0' && whichNumberChar < '8') {
                    int c_pos = Integer.parseInt("" + whichNumberChar);
                    if (passcode.charAt(c_pos) == '-') {
                        passcode = passcode.substring(0, c_pos) + candidate.charAt(6) + passcode.substring(c_pos + 1);
                        System.out.println(input + n + " " + candidate + " " + passcode);
                    }
                }

            }
            n++;

        }

        return passcode;

    }

}
