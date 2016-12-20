package lucka4;


public class CheckSumLetter implements Comparable<CheckSumLetter>{
    public int count =0;
    public char c;
    
    public CheckSumLetter(Character c_input) {
        count = 1;
        c = c_input;
    }
    
    public void add() {
        this.count++;
    }

    @Override
    public int compareTo(CheckSumLetter t) {
        if (this.count<t.count) return 1;
        if (this.count>t.count) return -1;
        return Character.compare(this.c, t.c);
    }
    
    public boolean equals(CheckSumLetter other) {
        return c==other.c && count == other.count;
    }
}
