
import java.util.*;

public class Lucka2 {

    private static int state = 5;

    public static String roomcode(String... inputLines) {

        state = 5;
        String toReturn = "";

        for (String s : inputLines) {
            toReturn += "" + roomCodeInternal(s);
        }

        return toReturn;
    }
    
    
    public static String roomcode2(String... inputLines) {

        state = 5;
        String toReturn = "";

        for (String s : inputLines) {
            toReturn += Integer.toHexString(roomCodeInternal2(s));
        }

        return toReturn;
    }

    
    private static int roomCodeInternal(String line) {

        for (char c : line.toCharArray()) {

            if (c == 'U') {
                state -= 3;
                if (state < 1) {
                    state += 3;
                }
            } else if (c == 'D') {
                state += 3;
                if (state > 9) {
                    state -= 3;
                }
            } else if (c == 'L') {
                state--;
                if (state == 0 || state == 3 || state == 6) {
                    state++;
                }
            } else if (c == 'R') {
                state++;
                if (state == 4 || state == 7 || state == 10) {
                    state--;
                }
            } else {
                throw new IllegalArgumentException("What?! " + c + " " + line);
            }
        }

        return state;
    }


    private static int roomCodeInternal2(String line) {

        for (char c : line.toCharArray()) {

            if (c == 'U') {
                if (state ==3) state = 1;
                else if (state >=6 && state <=8) state -=4;
                else if (state >=10 && state <=12) state -=4;
                else if (state ==13) state = 11; 
            } else if (c == 'D') {
                if (state ==1) state = 3;
                else if (state >=2 && state <=4) state += 4;
                else if (state >=6 && state <=8) state += 4;
                else if (state ==11) state =13;
            } else if (c == 'L') {
                if (state >=3 && state <=4 ||
                    state >=6 && state <=9 ||
                    state >=11 && state<=12) state--;
            } else if (c == 'R') {
                if (state >=2 && state <=3 ||
                    state >=5 && state <=8 ||
                    state >=10 && state<=11) state++;
            } else {
                throw new IllegalArgumentException("What?! " + c + " " + line);
            }
        }

        return state;
    }

    public static void main(String[] args) {

        System.out.println(Lucka2.roomcode2("ULL",
                "RRDDD",
                "LURDL",
                "UUUUD"));

        System.out.println(Lucka2.roomcode2("RRLLDDRLLDURLDUUDULDLRLDDDRLDULLRDDRDLUUDLLRRDURRLUDUDULLUUDRUURRDDDDURUULLDULRLRRRDLLLRDLRDULDLRUUDRURLULURUUDRLUUDRDURUUDDDRDLLRDLUDRLUUUUUULDURDRDDURLDDRLUUDLRURRDRLDRDLRRRLURULDLUUURDRLUULRDUDLDRRRDDLDDDRLRLLDRDUURDULUURRRRUDLLUDLDRLLRRULLLRDRDRRDRDRDUULUDLULRLLDRULURLURDLLDDRRLUDRDUULLDRULLLRLULUDDLURLUULDRUDRLDUUDDLLLRURDRLDRLUUUUUUDRUDLDLULULRRURRDDDUDRRRDDDLDDLRLLDDUULLUDRURDDDRDDLURRURULULUUDRLLUUDDDRUULRDLDLLRUUDRRLRRRULLRRURUDDUUDULDUDUUUDURUDUUDUDRULUDULRDUDUUUUDLLURDLRRUDURDDUULLDLLRDUDULRLRDURLRDRDLRDLRURUDURLULDDDLDRLULLRLURRLLDLRLLULLDUURUUDRULDDUDLDDR",
                "LUURULURUURRRDLRDRDDDUDULRDDLUUDUUULRLRRLRUDDLDLURLRULUUDUUDLDRLLUURLUUURDUDLUULLUUDUUDRDUDUDURLLURDUDLDLDDLDUDRLLUULDDRUDDDRLRUDRDUDULLRRDLLDDLRLLLRLRURURLLDULUDDUULULDRRLUURDULRULUDULDULDULRULLLRRDRLDRLDUULLDDULRLUDLLLULDDLULLUULUURRULDLUULDRRUDDDLRDLULDULDRRDLRRRLRUDURUDDDUDDURRRLDUULRDDLRRRLRUULDDURDRDUULDLLULULDRDRUULDLULRUUDUDLUDRLRDURRRRLULURDRLLLUDRRRDRURRLRLLUURDLURLURDULURUDDULLDUUDDLRLUULRDUUDRDRUDRLUUUDURLLRDRRDRURDDDDULLDDUDLDUUDLRLURURLDRRDRDUDLRRDRUDRDLURRLLLULULULRUDDDULULULDDRRLULUUUURDDURURLDLDDDDDRULUUUULLUDDDRRLUULDUULRUUDUURRLLRDDRUURL",
                "RRRLLLLUULLRRLLDRULULLLRDLLDLDDLURUDLULUULLRURLDULLUDULDRURDURLULLDUDDRLLRUURDLLULUURLULLDLRRDDDULUURRUDRDRDURULDLLURUDLLLDDUDLLLLRLDRDRDDRRDLUUDLLLULLLLLDDRDLULLLLRURRRUUUULLDLRDLDLRRRULRRRRLDLLRDURULDDLURURUULULDRDDDURLRDDRDULLUURUDUUUDRDRRLURULULRLUUDDRDULDRLULULUULRLDRLUDRRDDDRUDDRDDRDDRRLRDLRURDULULRRUUURDRRRDURDDRUDULUUDRDDLDRDDDULDLRDUULDUULRUDLRRDDDLLDDLLLRRDLDUULUULULURRULLRRUDUDRUDRRRLDLRLURRLUDLLLUUDDUDRURUUDDURDURULRLDUDRDLULDUDRUDDDR",
                "DRDRRUUUUURRLUDLDLRUUULRLDLRRRDDUDLUUDUDDLRDUDLRRLLURUUDULLUDLULLDLLDDULUDDUDUULURLDLDDUUDDRDDRLUURLUUDUDUDURULLDRLDDRUDLURRRLDRLRULDDLDDLDDDULDUDDLLDULUUDUDDUULDRLDRLRURDULUDDRRLRRLRRDULDRRDUDUDRLDURRLRLRDLRLRRLRDDDRULLULULDUDDLDLULRLLURRRRULUULRUDLDLRDLLURURUUURDRRRLDDRLRLURDDURDDUURUUUDDLRUURRRDLLUULUDRLDDRDDDDUUDRLRRDULDULRDLLLRULULLRDRULLRDLRUUURRRURLRRDLDRDDLURLDLULLDUURRDULUUURRLLDDUUUULDDDDLRDDDRDLDLLUURLDDRULUDDRDDDLRDU",
                "DRRRLURUDUDUULDLLURLUUDDRRRDUDLURLLDRRLLDDURULUDUURURULLRLDLLUURDLLDLLDLDLRUDLLLLDRLLUDLLDULDDRRURDRRLRLRRUURRUDURRLDRDLDURUULUDRLLURLUDURDULLRLLDLURLRRDLLLLUUDRDULLDLURDULDRDURRRLDRLURULULURLLLRRRUULRLRRDRDDDLULRLRRDLUDDUUUUUDRRDLDUDUURLDRRRLRUDRULDRLURUULRDLDDLRURDLURULRRLDURLDUURURULRDUDRRUDUDDLRLUURURULDDLULULULDULRRLRRURUURLRLLDRRLUDLUURDRRURRUUDULRLURRLRLRDDRURDDLRRDUDRLLUUUDULRDRULUURRLRLRDUDULDRDLLUDRDLLDRULDLUUURDDRDDUDDULLLDRRDDUDDDDLDDRLRULRRRURRDRULLDDDURDDLURRDDDUDLURRUDUDLLDDDLRUUDRLRRLRDUUUDDL"));
    }
}
