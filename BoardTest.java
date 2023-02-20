import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the Board class.
 */
public class BoardTest {

    // array of multipliers
    int[][] multipliers = { { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 } };

    // int[][] multipliers = { { 1, 2, 1 }, { 1, 2, 1 }, { 1, 2, 1 } };

    // tests constructor
    @Test
    public void testConstructor() {
        Board b = new Board(multipliers);

        assertEquals(1, b.getPointMult(0, 0));
        assertEquals(2, b.getPointMult(1, 1));
        assertEquals(1, b.getPointMult(2, 2));
        assertEquals(3, b.getPointMult(3, 3));
        assertEquals(1, b.getPointMult(4, 4));
        assertEquals(1, b.getPointMult(5, 5));
        assertEquals(1, b.getPointMult(6, 6));
        assertEquals(5, b.getPointMult(7, 7));
        assertEquals(1, b.getPointMult(8, 8));
        assertEquals(1, b.getPointMult(9, 9));
        assertEquals(1, b.getPointMult(10, 10));
        assertEquals(3, b.getPointMult(11, 11));
        assertEquals(1, b.getPointMult(12, 12));
        assertEquals(2, b.getPointMult(13, 13));
        assertEquals(1, b.getPointMult(14, 14));

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                assertNull(b.getLetter(i, j));
            }
        }
    }

    // tests if multiplier array is correctly copied into the Board's pointMult
    // array
    @Test
    public void testConstructorCopiesMultipliers() {
        Board b = new Board(multipliers);
        multipliers[0][0] = 100;
        assertEquals(1, b.getPointMult(0, 0));
        assertEquals(2, b.getPointMult(1, 1));
    }

    // tests illegal indexes given to the board
    @Test
    public void testGetLetterIllegalIndex() {
        int[][] mult2 = { { 1, 2 }, { 1, 2 } };

        Board b2 = new Board(mult2);
        int[][] mult10 = { { 1, 2, 1, 3, 1, 1, 5, 1, 7, 7 }, { 1, 2, 1, 3, 1, 1, 5, 1, 7, 7 } };
        Board b3 = new Board(mult10);

        assertNull(b2.getLetter(1, 2));
        assertNull(b3.getLetter(9, 7));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            b2.getLetter(-1, -1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            b3.getLetter(10, 15);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b3.getLetter(-1, 17);
        });
    }

    // tests illegal point multiplier index
    @Test
    public void testGetPointMultIllegalIndex() {
        int[][] mult2 = { { 1, 2 }, { 1, 2 } };
        Board b2 = new Board(mult2);
        int[][] mult10 = { { 1, 2, 1, 3, 1, 1, 5, 1, 7, 7 }, { 1, 2, 1, 3, 1, 1, 5, 1, 7, 7 } };
        Board b3 = new Board(mult10);

        assertEquals(2, b2.getPointMult(1, 1));
        assertEquals(2, b3.getPointMult(1, 1));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            b2.getPointMult(-1, 2);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b2.getPointMult(16, 2);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            b3.getPointMult(-1, 4);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b3.getPointMult(16, 3);
        });
    }

    // tests the get board score method
    // 1, 2, 1, 3, 1, 1 ,1 ,5, 1, 1, 1, 3, 1 ,2, 1
    @Test
    public void testGetBoardScore() {
        Board b = new Board(multipliers);

        Letter letterQ = new Letter('Q', 10);
        assertTrue(b.play(letterQ, 3, 1));
        Letter letterS = new Letter('S', 1);
        assertTrue(b.play(letterS, 5, 6));

        assertEquals(21, b.getBoardScore());

        assertTrue(b.play(letterQ, 0, 7));
        assertTrue(b.play(letterS, 7, 3));

        assertEquals(74, b.getBoardScore());
    }

    // tests the Board's play function
    @Test
    public void testPlayLetter() {
        Board b = new Board(multipliers);

        Letter letter1 = new Letter('Q', 10);
        assertTrue(b.play(letter1, 3, 1));
        Letter letter2 = new Letter('S', 1);
        assertTrue(b.play(letter2, 5, 6));

        assertEquals(letter1, b.getLetter(3, 1));
        assertEquals(letter2, b.getLetter(5, 6));

        Letter letter3 = new Letter('A', 3);
        assertFalse(b.play(letter3, 5, 6));
        assertEquals(letter2, b.getLetter(5, 6));
    }

    // tests playing letters at illegal indexes
    @Test
    public void testPlayLetterAtIllegalIndex() {
        Board b = new Board(multipliers);

        Letter letter1 = new Letter('Q', 10);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.play(letter1, -1, 2);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.play(letter1, 1, 16);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.play(letter1, 16, 2);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.play(letter1, 4, -2);
        });

    }

    // tests 'fits' function
    @Test
    public void testFitsEmptyBoard() {
        Board b = new Board(multipliers);

        assertTrue(b.fits("HELLO", 2, 5));
        assertTrue(b.fits("ABCDEFGH", 1, 6));
        assertTrue(b.fits("ABCDEFG", 7, 7));

        assertFalse(b.fits("ThisDoesNotFitOnTheBoard", 1, 1));
        assertFalse(b.fits("ABCDEFG", 10, 12));
    }

    // tests the 'fits' function when the board is not empty
    @Test
    public void testFitsNonEmptyBoard() {
        Board b = new Board(multipliers);
        b.play(new Letter('Q', 10), 4, 2);

        assertTrue(b.fits("ABCD", 0, 1));
        assertTrue(b.fits("ABC", 5, 6));

        assertFalse(b.fits("ABCD", 4, 2));

        b.play(new Letter('Q', 10), 7, 7);
        assertTrue(b.fits("AB", 5, 8));
        assertFalse(b.fits("AB", 7, 7));

    }

    // Illegal indexes for the 'fits' function
    @Test
    public void testFitsBadIndex() {
        Board b = new Board(multipliers);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.fits("HELLO", -1, 5);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.fits("HELLO", 8, 21);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.fits("HELLO", 21, 5);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.fits("HELLO", 2, -2);
        });

    }

    // tests the get letter score function
    // 1, 2, 1, 3, 1, 1 ,1 ,5, 1, 1, 1, 3, 1 ,2, 1
    @Test
    public void testGetLetterScore() {
        Board b = new Board(multipliers);

        Letter letter1 = new Letter('Q', 10);
        assertTrue(b.play(letter1, 1, 1));
        Letter letter2 = new Letter('S', 1);
        assertTrue(b.play(letter2, 5, 5));

        assertEquals(20, b.getLetterScore(1, 1));
        assertEquals(1, b.getLetterScore(5, 5));
        assertEquals(0, b.getLetterScore(4, 4));
    }

    // tests getting scores at illegal indexes
    @Test
    public void testGetLetterScoreAtIllegalIndex() {
        Board b = new Board(multipliers);

        Letter letter1 = new Letter('Q', 10);
        assertTrue(b.play(letter1, 3, 3));
        Letter letter2 = new Letter('S', 1);
        assertTrue(b.play(letter2, 5, 10));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.getLetterScore(-1, 2);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.getLetterScore(8, -1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.getLetterScore(17, 2);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.getLetterScore(2, 17);
        });

    }

}
