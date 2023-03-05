import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the Bag class.
 */
public class BagTest {
    @Test
    public void testbagconstructor(){
        Bag b = new Bag();
        assertEquals(104,b.getSize());
    }
    @Test
    public void testisEmpty(){
        Bag b = new Bag();
        assertFalse(b.isEmpty());
    }
    @Test
    public void testFindAmountOfLetter(){
        Bag b = new Bag();
        assertEquals(12,b.findAmountOfLetter('E'));
        assertEquals(9,b.findAmountOfLetter('A'));
        assertEquals(9,b.findAmountOfLetter('I'));
        assertEquals(8,b.findAmountOfLetter('O'));
        assertEquals(6,b.findAmountOfLetter('N'));
        assertEquals(6,b.findAmountOfLetter('R'));
        assertEquals(6,b.findAmountOfLetter('T'));
        assertEquals(4,b.findAmountOfLetter('L'));
        assertEquals(4,b.findAmountOfLetter('S'));
        assertEquals(4,b.findAmountOfLetter('U'));
        assertEquals(4,b.findAmountOfLetter('D'));
        assertEquals(3,b.findAmountOfLetter('G'));
        assertEquals(2,b.findAmountOfLetter('B'));
        assertEquals(2,b.findAmountOfLetter('C'));
        assertEquals(2,b.findAmountOfLetter('M'));
        assertEquals(2,b.findAmountOfLetter('P'));
        assertEquals(2,b.findAmountOfLetter('F'));
        assertEquals(2,b.findAmountOfLetter('H'));
        assertEquals(2,b.findAmountOfLetter('V'));
        assertEquals(2,b.findAmountOfLetter('W'));
        assertEquals(2,b.findAmountOfLetter('Y'));
        assertEquals(5,b.findAmountOfLetter('K'));
        assertEquals(1,b.findAmountOfLetter('J'));
        assertEquals(1,b.findAmountOfLetter('X'));
        assertEquals(1,b.findAmountOfLetter('Q'));
        assertEquals(1,b.findAmountOfLetter('Z'));
    }
    @Test
    public void testCheckLetter(){
        Bag b = new Bag();
        Letter letter = b.getLetter('Z');
        assertEquals("Letter: Z Points: 10",letter.toString());
        assertEquals(103,b.getSize());
        Letter letter1 = b.getLetter('D');
        assertEquals("Letter: D Points: 2",letter1.toString());
        assertEquals(102,b.getSize());
        Letter letter2 = b.getLetter('F');
        assertEquals("Letter: F Points: 4",letter2.toString());
        assertEquals(101,b.getSize());
        Letter letter3 = b.getLetter('A');
        assertEquals("Letter: A Points: 1",letter3.toString());
        assertEquals(100,b.getSize());
        Letter letter4 = b.getLetter('B');
        assertEquals("Letter: B Points: 3",letter4.toString());
        assertEquals(99,b.getSize());
        Letter letter5 = b.getLetter('K');
        assertEquals("Letter: K Points: 5",letter5.toString());
        assertEquals(98,b.getSize());
        Letter letter6 = b.getLetter('X');
        assertEquals("Letter: X Points: 8",letter6.toString());
        assertEquals(97,b.getSize());

        //testing exception error if the letter doesn't exist in the bag anymore
        assertThrows(IllegalArgumentException.class, () -> {
        b.getLetter('Z');
        });

        //testing if the object is valid
        assertThrows(IllegalArgumentException.class, () -> {
        b.getLetter(')');
        });
       
    }
    
}
