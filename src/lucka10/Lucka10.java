package lucka10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lucka10 {

    private long outPutProd() {
        return binNrToChipNr.get(0)*binNrToChipNr.get(1)*binNrToChipNr.get(2);
    }

    Map<Integer, List<Integer>> robotNrToListOfChips;
    Map<Integer, LowHigh> robotNrToLowHigh;
    
    Map<Integer, Integer> binNrToChipNr = new HashMap<>();

    public Lucka10() {
        robotNrToListOfChips = new HashMap<>();
        for (int i = 0; i < 2000; i++) {
            List<Integer> list = new ArrayList<>();
            robotNrToListOfChips.put(i, list);
        }
        

        robotNrToLowHigh = new HashMap<>();
    }

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
        Lucka10 lucka10 = new Lucka10();
     //   lucka10.intruct(readData("c:/java/Christmas/src/lucka10/data_test.txt"));

        System.out.println("--------------------------");
        lucka10 = new Lucka10();
        lucka10.intruct(readData("c:/java/Christmas/src/lucka10/data.txt"));
        
        System.out.println("outout prod " + lucka10.outPutProd()); // 412595806 is too high 54722766
    }
    
    

    /*
    value 23 goes to bot 76
    bot 16 gives low to bot 203 and high to bot 32
     */
    private void intruct(List<String> instructions) {

        // first, learn "high-low instructions
        for (String s : instructions) {
            if (s.startsWith("bot ")) {
                int botNr = Integer.parseInt(s.substring(s.indexOf("bot ") + 4, s.indexOf(" gives")));

                String temp_1 = s.substring(0, s.indexOf(" and high"));
                String temp_2 = s.substring(s.indexOf(" and high"));
                int lowTo = Integer.parseInt(temp_1.substring(temp_1.lastIndexOf(" ") + 1));
                int hightTo = Integer.parseInt(s.substring(s.lastIndexOf(" ") + 1));
                this.robotNrToLowHigh.put(botNr,
                        new LowHigh(lowTo, hightTo,
                                temp_1.contains("output") ? Entity.Output : Entity.Bot,
                                temp_2.contains("output") ? Entity.Output : Entity.Bot));
            }
        }

        for (String s : instructions) {
            //value 23 goes to bot 76
            if (s.startsWith("value")) {
                int botNr = Integer.parseInt(s.substring(s.indexOf("to bot") + 7));
                int value = Integer.parseInt(s.substring(6, s.indexOf(" goes to")));
                giveChipToRobot(botNr, value);
                checkIfRobotsHaveMoreThan1Chip();
            }
        }
    }

    private void checkIfRobotsHaveMoreThan1Chip() {

        for (Integer botNr : this.robotNrToListOfChips.keySet()) {

            List<Integer> chipsList = robotNrToListOfChips.get(botNr);

            if (chipsList.size() > 1) {
                
                LowHigh lowHigh = this.robotNrToLowHigh.get(botNr);
                //  if (chipsList.contains(2) && chipsList.contains(5)) {
                int low = Math.min(chipsList.get(0), chipsList.get(1));
                int high = Math.max(chipsList.get(0), chipsList.get(1));
                System.out.println("Bot " + botNr + " examines " + low + " and " + high);
                if (lowHigh.highWhat == Entity.Bot) {
                    giveChipToRobot(this.robotNrToLowHigh.get(botNr).highToNr, high);
                } else {
                    System.out.println("output " + this.robotNrToLowHigh.get(botNr).highToNr + " " + high);
                    this.binNrToChipNr.put(this.robotNrToLowHigh.get(botNr).highToNr, high);
                }
                if (lowHigh.lowWhat == Entity.Bot) {
                    giveChipToRobot(this.robotNrToLowHigh.get(botNr).lowToNr, low);
                } else {
                    System.out.println("output " + this.robotNrToLowHigh.get(botNr).lowToNr +" "+ low);
                    this.binNrToChipNr.put(this.robotNrToLowHigh.get(botNr).lowToNr, low);
                }
                this.robotNrToListOfChips.get(botNr).clear();
                checkIfRobotsHaveMoreThan1Chip();
            }
        }
    }

    public void giveChipToRobot(int robotNr, int value) {
        System.out.println("robot " + robotNr + " get chip " + value);
        this.robotNrToListOfChips.get(robotNr).add(value);
    }
}
