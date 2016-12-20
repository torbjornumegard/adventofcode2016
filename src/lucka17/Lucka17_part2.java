package lucka17;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.Assert;

public class Lucka17_part2 {

    String passcode;

    public Lucka17_part2(String code) {
        System.out.print(code + ":");
        passcode = code;
    }

    public static void main(String[] args) {

        Assert.assertEquals("check hash", "f2bc", md5("hijklD").substring(0, 4));
        Assert.assertEquals("check hash", "5745", md5("hijklDR").substring(0, 4));
        Assert.assertEquals("check hash", "528e", md5("hijklDU").substring(0, 4));
        
        Lucka17_part2 lucka = new Lucka17_part2("ihgpwlah");
        Assert.assertEquals(370, lucka.solve().length());

/*        
        lucka = new Lucka17_part2("kglvqrro");
        Assert.assertEquals("DDUDRLRRUDRD", lucka.solve());
    
        lucka = new Lucka17_part2("ulqzkmiv");
        Assert.assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", lucka.solve());
  */      
        lucka = new Lucka17_part2("qzthpkfp");
        String result = lucka.solve();
        System.out.println("--->" + result.length()); 
    }

    public String solve() {
        String s = solve(1, 1, "");
        System.out.println(s);
        return s;
    }

    private String solve(int x, int y, String stepsDone) {

        
        if (x == 4 && y == 4) {
            return stepsDone;
        }

        if (x < 1 || y < 1 || x > 4 || y > 4) {
            return null; // not possible
        }
        String hashstring = md5(this.passcode + stepsDone).substring(0, 4);
        boolean upPossible = hashstring.charAt(0) >= 'b' && hashstring.charAt(0) <= 'f';
        boolean downPossible = hashstring.charAt(1) >= 'b' && hashstring.charAt(1) <= 'f';
        boolean leftPossible = hashstring.charAt(2) >= 'b' && hashstring.charAt(2) <= 'f';
        boolean rightPossible = hashstring.charAt(3) >= 'b' && hashstring.charAt(3) <= 'f';

        String s1 = rightPossible ? solve(x + 1, y, stepsDone + "R") : null;
        String s2 = downPossible ? solve(x, y + 1, stepsDone + "D") : null;
        String s3 = leftPossible ? solve(x - 1, y, stepsDone + "L") : null;
        String s4 = upPossible ? solve(x, y - 1, stepsDone + "U") : null;
        
        String best = s1;
        if (best == null || s2 != null && s2.length() > best.length()) {
            best = s2;
        }
        if (best == null || s3 != null && s3.length() > best.length()) {
            best = s3;
        }
        if (best == null || s4 != null && s4.length() > best.length()) {
            best = s4;
        }

        return best; // the shortest string-path
    }

    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
