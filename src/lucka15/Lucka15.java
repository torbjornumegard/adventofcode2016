package lucka15;

import java.util.*;

public class Lucka15 {
    
    


    public static void main(String[] args) {
    
        boolean found = false;
        int nr = 15;
        while (!found) {
            nr+=209; // 11*19
            if (nr%13 != 1) continue;
            if (nr%5 != 3) continue;
            if (nr%17 != 3) continue;
            if (nr%3 != 2) continue;
            if (nr%7 != 0) continue;
          //  if (nr%19 != 15) continue;
          //  if (nr%11 != 15) continue;
            found = true;
            if (nr%1000==0) System.out.println(nr);
        }
        System.out.println("--->" + nr);
        
    }

}
