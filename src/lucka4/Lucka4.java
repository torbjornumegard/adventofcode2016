package lucka4;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author torbjornu
 */
public class Lucka4 {

    public static List<String> readData(String filePath) {

        List<String> toReturn = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("could not read " + filePath);
        }

        while (scanner.hasNext()) {
            toReturn.add(scanner.nextLine());
        }

        return toReturn;
    }

    public static int findIdFromRoomCode(String roomCode) {

        String[] split = roomCode.split("-");
        String temp = split[split.length - 1];
        String temp2 = temp.substring(0, temp.indexOf("["));
        return Integer.parseInt(temp2);

    }

    public static int solve(List<String> roomCodes) {

        int checkSum = 0;

        for (String rc : roomCodes) {
            String actualChecksum = findCheckSumFromRoomCode(rc);
            String genneratedCheckSum = generateRoomCodeCheckSum(rc);
            if (actualChecksum.equals(genneratedCheckSum)) {
                checkSum += findIdFromRoomCode(rc);
                System.out.println(decodeRoom(rc) + " " + findIdFromRoomCode(rc));
            }
        }

        System.out.println("Checksum: " + checkSum);

        return checkSum;
    }

    public static void main(String[] args) {

        List<String> test = new ArrayList<>();
        test.add("aaaaa-bbb-z-y-x-123[abxyz]");
        test.add("a-b-c-d-e-f-g-h-987[abcde]");
        test.add("not-a-real-room-404[oarel]");
        test.add("totally-real-room-200[decoy]");

        Lucka4.solve(test);

        Lucka4.solve(readData("c:/java/Christmas/src/lucka4_data.txt"));

    }

    private static String generateRoomCodeCheckSum(String rc) {

        List<CheckSumLetter> letterList = new ArrayList<>();

        for (char c : rc.toCharArray()) {
            if (c >= '0' && c <= '9') {
                break;
            }
            if (c == '-') {
                continue;
            }
            CheckSumLetter csl = new CheckSumLetter(c);

            boolean found = false;
            for (CheckSumLetter csl_in_list : letterList) {
                if (csl_in_list.c == csl.c) {
                    csl_in_list.add();
                    found = true;
                }
            }
            if (!found) {
                letterList.add(csl);
            }
        }

        Collections.sort(letterList);

        String toReturn = "";
        for (CheckSumLetter csl : letterList) {
            toReturn += csl.c;
        }

        return toReturn.substring(0, 5);
    }

    // a-b-c-d-e-f-g-h-987[abcde] 
    private static String findCheckSumFromRoomCode(String rc) {
        String temp = rc.substring(rc.indexOf("[") + 1);
        temp = temp.substring(0, temp.length() - 1);
        return temp;
    }

    private static int countCharInString(char c, String letters) {
        int count = 0;
        for (char x : letters.toCharArray()) {
            if (x == c) {
                count++;
            }
        }
        return count;
    }

    private static String decodeRoom(String roomCode) {
        int id = findIdFromRoomCode(roomCode);
        String roomName = roomCode.substring(0, roomCode.lastIndexOf("-"));
        String decodedRoomName = "";
        for (char c : roomName.toCharArray()) {
            char newChar = c;
            if (c == '-') {
                newChar = ' ';
            } else {
                for (int i = 0; i < id; i++) {

                    if (newChar == 'z') {
                        newChar = 'a';
                    } else if (newChar == 'Z') {
                        newChar = 'A';
                    } else {
                        newChar++;
                    }
                }
            }
            decodedRoomName += "" + newChar;
        }
        return decodedRoomName;
    }

}
