package lucka19;

import java.util.*;

public class Lucka19_2 {

    List<Integer> cirleOfElves; // list contains original seating position

    public Lucka19_2(int nrOfElves) {
        cirleOfElves = new ArrayList<>(nrOfElves);
        for (int i = 1; i < nrOfElves; i++) {
            cirleOfElves.add(i);
        }
        cirleOfElves.add(0, nrOfElves);
    }
  
    public int solve2() {
        int currentElf = 1;
        while (cirleOfElves.size() > 1) {
            int stealFrom = (cirleOfElves.size()/2+currentElf)%cirleOfElves.size();
         //   System.out.println("Elf " + cirleOfElves.get(currentElf) + " steals from " + cirleOfElves.get(stealFrom));
            cirleOfElves.remove(stealFrom);
            if (stealFrom<currentElf) {
                currentElf = (currentElf)%cirleOfElves.size();
            } else {
                currentElf = (currentElf+1)%cirleOfElves.size();
            }
            
            if (cirleOfElves.size()%1000==0) System.out.println(cirleOfElves.size());
        }
        
        return cirleOfElves.get(0);
    }



    public static void main(String[] args) {


        Lucka19_2 lucka  = new Lucka19_2(5);
        System.out.println(lucka.solve2());

        lucka  = new Lucka19_2(3001330);
        System.out.println(lucka.solve2()); // too low 20977
        


    }

}
