import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the Letter class.
 */
public class LetterTest {

    @Test
    public void testConstructorBasic() {
        Letter x = new Letter('X', 7);
        assertEquals('X', x.getLetter());
        assertEquals(7, x.getPoints());
        Letter y = new Letter('Y', 8);
        assertEquals('Y', y.getLetter());
        assertEquals(8, y.getPoints());
    }

    @Test
    void testConstructorArgCheck() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Letter('0', 7);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Letter('x', 7);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Letter('X', -7);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Letter('@', 1); // ASCII 64 (One below 'A')
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Letter('\\', 1); // ASCII 92 (One above 'Z')
        });

    }

    @Test
    public void testEquals() {
        Letter x = new Letter('X', 7);
        Letter y = new Letter('Y', 2);
        Letter z = new Letter('Z', 7);
        Letter x2 = new Letter('X', 3);
        Letter y2 = new Letter('Y', 2);

        assertFalse(x.equals(y));
        assertFalse(x.equals(z));
        assertFalse(x.equals(x2));
        assertTrue(x.equals(x));
        assertTrue(y.equals(y2));
    }

    @Test
    public void testEqualsObject() {
        Letter x = new Letter('X', 7);
        Letter x1 = new Letter('X', 7);

        assertFalse(x.equals("X"));

        // Make sure the Object version of equals has actually been overridden
        Object obj = x1;
        assertTrue(x.equals(obj));

    }

    @Test
    public void testToString() {
        Letter x = new Letter('X', 7);
        assertEquals("Letter: X Points: 7", x.toString());

        Letter t = new Letter('T', 3);
        assertEquals("Letter: T Points: 3", t.toString());
    }

}
