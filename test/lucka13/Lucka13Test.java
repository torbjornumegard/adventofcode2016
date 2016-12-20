package lucka13;

import org.junit.Test;
import static org.junit.Assert.*;

public class Lucka13Test {

     @Test
    public void test1() {
        Lucka13 lucka13 = new Lucka13(10);

        assertEquals(true, lucka13.isOpenSpace(0, 0));
        assertEquals(false, lucka13.isOpenSpace(1, 0));
        lucka13.printMaze(5);
        assertEquals(1, lucka13.solve(1, 2, 1, 1));
    }

    @Test
    public void test2() {
        Lucka13 lucka13 = new Lucka13(10);
        lucka13.printMaze(5);
        assertEquals(2, lucka13.solve(2, 2, 1, 1));
    }

    @Test
    public void test3() {
        Lucka13 lucka13 = new Lucka13(10);
        lucka13.printMaze(5);
        assertEquals(true, lucka13.isOpenSpace(0, 0));
        assertEquals(false, lucka13.isOpenSpace(1, 0));
        assertEquals(2, lucka13.solve(2, 2, 1, 1));
    }
    
     @Test
    public void test4() {
        Lucka13 lucka13 = new Lucka13(10);
        lucka13.printMaze(5);
        assertEquals(false, lucka13.isOpenSpace(2, 1));
        assertEquals(4, lucka13.solve(3, 1, 1, 1));
    }
}
