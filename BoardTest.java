import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the Board class.
 *
 * @author JMU Faculty
 * @version 01/25/2021
 */
public class BoardTest {

    /**
     * Creates the default board for testing.
     * 
     * @return board with eight multipliers
     */
    private Board createBoard() {
        int[] multipliers = {1, 2, 1, 3, 1, 1, 5, 1};
        return new Board(multipliers);
    }

    @Test
    public void testConstructor() {
        Board b = createBoard();

        assertEquals(1, b.getPointMult(0));
        assertEquals(2, b.getPointMult(1));
        assertEquals(1, b.getPointMult(2));
        assertEquals(3, b.getPointMult(3));
        assertEquals(1, b.getPointMult(4));
        assertEquals(1, b.getPointMult(5));
        assertEquals(5, b.getPointMult(6));
        assertEquals(1, b.getPointMult(7));

        for (int i = 0; i < 8; i++) {
            assertNull(b.getLetter(i));
        }
    }

    @Test
    public void testConstructorCopiesMultipliers() {
        int[] multipliers = {1, 2, 1, 3, 1, 1, 5, 1};
        Board b = new Board(multipliers);
        multipliers[0] = 100;
        assertEquals(1, b.getPointMult(0));
    }

    @Test
    public void testGetLetterIllegalIndex() {
        int[] mult2 = {1, 2};
        Board b2 = new Board(mult2);
        int[] mult10 = {1, 2, 1, 3, 1, 1, 5, 1, 7, 7};
        Board b10 = new Board(mult10);

        assertNull(b2.getLetter(1));
        assertNull(b10.getLetter(9));

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b2.getLetter(2);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b2.getLetter(-1);
        });

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b10.getLetter(10);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b10.getLetter(-1);
        });
    }

    @Test
    public void testGetPointMultIllegalIndex() {
        int[] mult2 = {1, 2};
        Board b2 = new Board(mult2);
        int[] mult10 = {1, 2, 1, 3, 1, 1, 5, 1, 7, 7};
        Board b10 = new Board(mult10);

        assertEquals(2, b2.getPointMult(1));
        assertEquals(7, b10.getPointMult(9));

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b2.getPointMult(2);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b2.getPointMult(-1);
        });

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b10.getPointMult(10);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b10.getPointMult(-1);
        });
    }

    @Test
    public void testGetBoardScore() {
        Board b = createBoard();

        Letter letterQ = new Letter('Q', 10);
        assertTrue(b.play(letterQ, 3));
        Letter letterS = new Letter('S', 1);
        assertTrue(b.play(letterS, 5));

        assertEquals(31, b.getBoardScore());

        assertTrue(b.play(letterQ, 0));
        assertTrue(b.play(letterS, 7));

        assertEquals(42, b.getBoardScore());
    }

    @Test
    public void testPlayLetter() {
        Board b = createBoard();

        Letter letter1 = new Letter('Q', 10);
        assertTrue(b.play(letter1, 3));
        Letter letter2 = new Letter('S', 1);
        assertTrue(b.play(letter2, 5));

        assertEquals(letter1, b.getLetter(3));
        assertEquals(letter2, b.getLetter(5));

        Letter letter3 = new Letter('A', 3);
        assertFalse(b.play(letter3, 5));
        assertEquals(letter2, b.getLetter(5));
    }

    @Test
    public void testPlayLetterAtIllegalIndex() {
        Board b = createBoard();

        Letter letter1 = new Letter('Q', 10);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b.play(letter1, -1);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b.play(letter1, 8);
        });

        assertNull(b.getLetter(3));
        assertNull(b.getLetter(5));
    }

    @Test
    public void testFitsEmptyBoard() {
        Board b = createBoard();

        assertTrue(b.fits("HELLO", 2));
        assertTrue(b.fits("ABCDEFGH", 0));
        assertTrue(b.fits("ABCDEFG", 1));

        assertFalse(b.fits("ABCDEFGH", 1));
        assertFalse(b.fits("ABCDEFG", 2));
    }

    @Test
    public void testFitsNonEmptyBoard() {
        Board b = createBoard();
        b.play(new Letter('Q', 10), 4);

        assertTrue(b.fits("ABCD", 0));
        assertTrue(b.fits("ABC", 5));

        assertFalse(b.fits("ABCDE", 0));
        assertFalse(b.fits("ABCD", 4));

        assertFalse(b.fits("HELLO", 2));

        b.play(new Letter('Q', 10), 7);
        assertTrue(b.fits("AB", 5));
        assertFalse(b.fits("AB", 6));

    }

    @Test
    public void testFitsBadIndex() {
        Board b = createBoard();

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b.fits("HELLO", -1);
        });

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b.fits("HELLO", 8);
        });

    }

    @Test
    public void testGetLetterScore() {
        Board b = createBoard();

        Letter letter1 = new Letter('Q', 10);
        assertTrue(b.play(letter1, 3));
        Letter letter2 = new Letter('S', 1);
        assertTrue(b.play(letter2, 5));

        assertEquals(30, b.getLetterScore(3));
        assertEquals(1, b.getLetterScore(5));
        assertEquals(0, b.getLetterScore(4));
    }

    @Test
    public void testGetLetterScoreAtIllegalIndex() {
        Board b = createBoard();

        Letter letter1 = new Letter('Q', 10);
        assertTrue(b.play(letter1, 3));
        Letter letter2 = new Letter('S', 1);
        assertTrue(b.play(letter2, 5));

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b.getLetterScore(-1);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            b.getLetterScore(8);
        });

    }

    @Test
    public void testToString() {
        Board b = createBoard();

        Letter letter1 = new Letter('Q', 10);
        assertTrue(b.play(letter1, 3));
        Letter letter2 = new Letter('S', 1);
        assertTrue(b.play(letter2, 5));

        String expected = "0: -\n" + "Multiplier: 1\n" + "1: -\n"
                + "Multiplier: 2\n" + "2: -\n" + "Multiplier: 1\n"
                + "3: Letter: Q Points: 10\n" + "Multiplier: 3\n" + "4: -\n"
                + "Multiplier: 1\n" + "5: Letter: S Points: 1\n"
                + "Multiplier: 1\n" + "6: -\n" + "Multiplier: 5\n" + "7: -\n"
                + "Multiplier: 1\n";
        assertEquals(expected, b.toString());
    }

}
