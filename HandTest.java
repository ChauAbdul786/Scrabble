import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the Hand class.
 */
public class HandTest {

    @Test
    public void testConstructors() {
        Hand h = new Hand();
        assertEquals(Hand.MAX_SIZE, h.getSize());
        h = new Hand(4);
        assertEquals(4, h.getSize());
        h = new Hand(-1);
        assertEquals(0, h.getSize());
        h = new Hand(9);
        assertEquals(8, h.getSize());
    }

    @Test
    public void testInsert() {
        Hand h = new Hand();
        Letter letter1 = new Letter('A', 2);
        assertTrue(h.insert(letter1, 0));
        assertEquals(letter1, h.getLetter(0));
        assertTrue(h.insert(letter1, 7));
        assertEquals(letter1, h.getLetter(7));
        assertNull(h.getLetter(1));
    }

    @Test
    public void testInsertBadIndex() {

        Hand h = new Hand();
        Letter letter1 = new Letter('A', 2);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            h.insert(letter1, -1);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            h.insert(letter1, 8);
        });

        Hand hSmall = new Hand(4);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            hSmall.insert(letter1, -1);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            hSmall.insert(letter1, 4);
        });

    }

    @Test
    public void testInsertFullSlot() {
        Hand h = new Hand();
        Letter letter1 = new Letter('A', 2);
        assertTrue(h.insert(letter1, 1));
        Letter letter2 = new Letter('B', 4);
        assertFalse(h.insert(letter2, 1));
        assertEquals(letter1, h.getLetter(1));
    }

    @Test
    public void testRemove() {
        Hand h = new Hand();
        Letter letter1 = new Letter('R', 5);

        assertNull(h.getLetter(0));

        h.insert(letter1, 5);
        Letter removed = h.remove(5);

        assertNull(h.getLetter(5));
        assertEquals(letter1, removed);
    }

    @Test
    public void testRemoveBadIndex() {
        Hand h = new Hand();

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            h.remove(-1);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            h.remove(8);
        });

        Hand hSmall = new Hand(4);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            hSmall.remove(-1);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            hSmall.remove(4);
        });

    }

    @Test
    public void testIndexOfPresent() {
        Hand h = new Hand();

        h.insert(new Letter('R', 2), 3);
        h.insert(new Letter('R', 2), 5);
        h.insert(new Letter('A', 5), 1);

        assertEquals(3, h.indexOf('R'));
        assertEquals(1, h.indexOf('A'));
    }

    @Test
    public void testIndexOfMissing() {
        Hand h = new Hand();

        assertEquals(-1, h.indexOf('Z'));

        h.insert(new Letter('R', 2), 3);
        h.insert(new Letter('R', 2), 5);
        h.insert(new Letter('A', 5), 1);

        assertEquals(-1, h.indexOf('Q'));
    }

    @Test
    public void testCanForm() {
        Hand h = new Hand();
        h.insert(new Letter('C', 1), 1);
        h.insert(new Letter('A', 2), 3);
        h.insert(new Letter('T', 3), 5);
        h.insert(new Letter('S', 4), 7);
        assertTrue(h.canForm("CATS"));
        assertTrue(h.canForm("SAT"));
        assertTrue(h.canForm(""));
        assertFalse(h.canForm("DOGS"));
        assertFalse(h.canForm("CATSZ"));
    }

    @Test
    public void testCanFormRepeats() {
        Hand h = new Hand();
        h.insert(new Letter('C', 1), 1);
        h.insert(new Letter('A', 2), 3);
        h.insert(new Letter('T', 3), 5);
        h.insert(new Letter('S', 4), 7);
        assertFalse(h.canForm("CATTSS"));

        h.insert(new Letter('T', 1), 0);
        h.insert(new Letter('S', 2), 2);
        assertTrue(h.canForm("CATTSS"));
    }

    @Test
    public void testCanFormNull() {
        Hand h = new Hand();
        assertThrows(NullPointerException.class, () -> {
            h.canForm(null);
        });
    }

    @Test
    public void testToString() {
        Hand h = new Hand(2);
        Hand h1 = new Hand(3);

        assertEquals("0: -\n1: -\n", h.toString());

        h.insert(new Letter('X', 2), 0);
        assertEquals("0: Letter: X Points: 2\n1: -\n", h.toString());

        h1.insert(new Letter('X', 2), 1);
        h1.insert(new Letter('A', 3), 2);
        assertEquals("0: -\n1: Letter: X Points: 2\n2: Letter: A Points: 3\n",
                h1.toString());
    }

}
