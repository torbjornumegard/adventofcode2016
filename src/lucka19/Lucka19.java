package lucka19;

import java.util.*;

public class Lucka19 {

    List<Integer> elvesWithGifts; // contains how mayn gift each elf has

    public int solve() {

        int currentElf = 1;
        int nextElf = 2;
        while (nextElf != currentElf) {

            if (elvesWithGifts.get(nextElf) > 0) {
                elvesWithGifts.set(currentElf, elvesWithGifts.get(currentElf) + elvesWithGifts.get(nextElf));
                elvesWithGifts.set(nextElf, 0);
            }

            do {
                currentElf = (currentElf + 1) % this.elvesWithGifts.size();
            } while (elvesWithGifts.get(currentElf) == 0);

            nextElf = currentElf;
            do {
                nextElf = (nextElf + 1) % elvesWithGifts.size();
            } while (elvesWithGifts.get(nextElf) == 0);
        }

        return currentElf;
    }

    public Lucka19(int nrOfElves) {
        elvesWithGifts = new ArrayList<>(nrOfElves);
        for (int i = 0; i < nrOfElves; i++) {
            elvesWithGifts.add(i, 1);
        }
    }

    public static void main(String[] args) {

        Lucka19 lucka = new Lucka19(5);
        System.out.println(lucka.solve());

        lucka = new Lucka19(3001330);
        System.out.println(lucka.solve()); // 1808357

    }


}
