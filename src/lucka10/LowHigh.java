package lucka10;

import java.util.*;

public class LowHigh {

    int lowToNr;
    int highToNr;

    Entity lowWhat;
    Entity highWhat;

    public LowHigh(int low, int high, Entity lowWhat, Entity highWhat) {
        lowToNr = low;
        highToNr = high;
        this.lowWhat = lowWhat;
        this.highWhat = highWhat;
    }

}
