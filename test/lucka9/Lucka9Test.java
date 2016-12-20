package lucka9;

import java.util.*;
import lucka9.Lucka9;
import static lucka9.Lucka9.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class Lucka9Test {

    @Test
    public void testPart1() {

        Lucka9 lucka9 = new Lucka9();

        assertEquals("ADVENT", lucka9.decrypt("ADVENT"));
        assertEquals("ABBBBBC", lucka9.decrypt("A(1x5)BC"));

    }

    @Test
    public void testPart1_2() {

        Lucka9 lucka9 = new Lucka9();

        assertEquals("XYZXYZXYZ", lucka9.decrypt2("(3x3)XYZ"));
        assertEquals("ABCBCDEFEFG", lucka9.decrypt2("A(2x2)BCD(2x2)EFG"));

        assertEquals(115118, decrypt(readDataOneLine("c:/java/Christmas/src/lucka9/data.txt")).length());

    }

    @Test
    public void testPart2() {

        assertEquals("ADVENT", decrypt2("ADVENT"));
        assertEquals("ABBBBBC", decrypt2("A(1x5)BC"));
        assertEquals("X(3x3)ABC(3x3)ABCY", decrypt("X(8x2)(3x3)ABCY"));

    }

    @Test
    public void testPart2_2() {

        assertEquals("XYZXYZXYZ", decrypt2("(3x3)XYZ"));
        assertEquals("ABCBCDEFEFG", decrypt2("A(2x2)BCD(2x2)EFG"));
        assertEquals("XABCABCABCABCABCABCY", decrypt2("X(8x2)(3x3)ABCY"));
        assertEquals(241920, decrypt2("(27x12)(20x12)(13x14)(7x10)(1x12)A").length());
    }
    
    @Test
    public void testPart2_5() {
        assertEquals("XABCABCABCABCABCABCY".length(), Lucka9.findDecryptLength("X(8x2)(3x3)ABCY"));
        
    }
  
    
       @Test
    public void testPart2_3() {
        assertEquals(241920, Lucka9.findDecryptLength("(27x12)(20x12)(13x14)(7x10)(1x12)A"));
        
    }
  
       @Test
    public void testPart2_4() {
        assertEquals(35, Lucka9.findDecryptLength("(5x7)SEVEN"));
        assertEquals(41, Lucka9.findDecryptLength("(3x2)TWO(5x7)SEVEN"));
        assertEquals(445, Lucka9.findDecryptLength("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN"));
        
    }
}
