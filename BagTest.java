import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the Board class.
 */

public class BagTest {
    @Test
    public void testConstructorandToString(){
        Bag testBag = new Bag();
        String s = "Bag Size: 104\nBlank Tiles: 2\nA: 9\nB: 2\nC: 2\nD: 4\nE: 12\nF: 2\nG: 3\nH: 2\nI: 9\nJ: 1\nK: 5\nL: 4\nM: 2\nN: 6\nO: 8\nP: 2\nQ: 1\nR: 6\nS: 4\nT: 6\nU: 4\nV: 2\nW: 2\nX: 1\nY: 2\nZ: 1\n";
        assertEquals(s, testBag.toString());
    }

    @Test
    public void testGetLetter(){
        Bag testBag = new Bag();
        Letter letterA = testBag.getLetter('A');
        Letter letterK = testBag.getLetter('K');

        assertEquals(letterA.getLetter(), 'A');
        assertEquals(letterA.getPoints(), 1);
        assertEquals(letterK.getLetter(), 'K');
        assertEquals(letterK.getPoints(), 5);
    }

    //Test addLetter
    @Test
    public void testAddLetter(){
        Bag testBag = new Bag();
        Letter letterA = new Letter('A', 1);
        String s = "A: 10 (Already existed in bag)\n";

        testBag.addLetter(letterA);
        assertEquals(s, testBag.addLetter(letterA));
    }

    //Test findAmountOfLetter

    //Test getSize

    //Test isEmpty
}
