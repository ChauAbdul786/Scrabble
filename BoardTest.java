import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
/**
 * Tests the Board class.
 */
public class BoardTest {

    // array of multipliers
    int[][] multipliers = { 
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
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
            { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 } };

    // int[][] multipliers = { { 1, 2, 1 }, { 1, 2, 1 }, { 1, 2, 1 } };

    // tests constructor
    @Test
    public void testConstructor() {
        Board b = new Board(multipliers);
        //ensures that the multipliers were correctly assigned to the board
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
        //makes sure that the Dictionary.txt is opened
        //and that the dictionary attribute is properly populated
        for(int i = 0; i < 26;i++){
            for(int j = 0; j < b.Dictionary[i].size();j++){
            assertNotNull(b.Dictionary[i].get(j));
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
        Scanner scanner = new Scanner(System.in);

        assertEquals(0, b.getBoardScore());;
        Letter letterQ = new Letter('Q', 10);
        assertTrue(b.play(letterQ, 3, 1,scanner));
        Letter letterS = new Letter('S', 1);
        assertTrue(b.play(letterS, 5, 6,scanner));

        assertEquals(21, b.getBoardScore());

        assertTrue(b.play(letterQ, 0, 7,scanner));
        assertTrue(b.play(letterS, 7, 3,scanner));

        assertEquals(74, b.getBoardScore());
    }

    // tests the Board's play function
    @Test
    public void testPlayLetter() {
        Board b = new Board(multipliers);
        Scanner scanner = new Scanner(System.in);

        Letter letter1 = new Letter('Q', 10);
        assertTrue(b.play(letter1, 3, 1,scanner));
        assertEquals(3, b.getPlayedTilesX(0));
        assertEquals(1, b.getPlayedTilesY(0));
        assertEquals(0, b.getPlayedIterator());

        Letter letter2 = new Letter('S', 1);
        assertTrue(b.play(letter2, 5, 6,scanner));
        assertEquals(5, b.getPlayedTilesX(1));
        assertEquals(6, b.getPlayedTilesY(1));

        assertEquals(1,b.getPlayedIterator());
        assertEquals(letter1, b.getLetter(3, 1));
        assertEquals(letter2, b.getLetter(5, 6));

        Letter letter3 = new Letter('A', 3);
        assertFalse(b.play(letter3, 5, 6,scanner));
        b.resetIterators();
        assertEquals(-1,b.getPlayedIterator());
       // assertNull(b.getPlayedTilesX(0));
        assertEquals(letter2, b.getLetter(5, 6));
    }

    @Test
    public void testPlayLetter2(){
        Board b = new Board(multipliers);
        Letter letter1 = new Letter('Q', 10);
        Scanner scanner = new Scanner(System.in);
        assertTrue(b.play(letter1, 3, 1,scanner));
        assertTrue(b.play(letter1, 3, 2,scanner));
        assertTrue(b.play(letter1, 3, 3,scanner));
        assertEquals(3, b.getPlayedTilesX(0));        
        assertEquals(3, b.getPlayedTilesX(1));        
        assertEquals(3, b.getPlayedTilesX(2));        
        assertEquals(1, b.getPlayedTilesY(0));        
        assertEquals(2, b.getPlayedTilesY(1));        
        assertEquals(3, b.getPlayedTilesY(2));        
    }

    // tests playing letters at illegal indexes
    @Test
    public void testPlayLetterAtIllegalIndex() {
        Board b = new Board(multipliers);
        Scanner scanner = new Scanner(System.in);

        Letter letter1 = new Letter('Q', 10);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.play(letter1, -1, 2,scanner);
            assertEquals(-1, b.getPlayedIterator());
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.play(letter1, 1, 16,scanner);
            assertEquals(-1, b.getPlayedIterator());
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.play(letter1, 16, 2,scanner);
            assertEquals(-1, b.getPlayedIterator());
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            b.play(letter1, 4, -2,scanner);
            assertEquals(-1, b.getPlayedIterator());
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
        Scanner scanner = new Scanner(System.in);
        b.play(new Letter('Q', 10), 4, 2,scanner);

        assertTrue(b.fits("ABCD", 0, 1));
        assertTrue(b.fits("ABC", 5, 6));

        assertFalse(b.fits("ABCD", 4, 2));

        b.play(new Letter('Q', 10), 7, 7,scanner);
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
        Scanner scanner = new Scanner(System.in);

        Letter letter1 = new Letter('Q', 10);
        assertTrue(b.play(letter1, 1, 1,scanner));
        Letter letter2 = new Letter('S', 1);
        assertTrue(b.play(letter2, 5, 5,scanner));

        assertEquals(20, b.getLetterScore(1, 1));
        assertEquals(1, b.getLetterScore(5, 5));
        assertEquals(0, b.getLetterScore(4, 4));
    }

    // tests getting scores at illegal indexes
    @Test
    public void testGetLetterScoreAtIllegalIndex() {
        Board b = new Board(multipliers);
        Scanner scanner = new Scanner(System.in);

        Letter letter1 = new Letter('Q', 10);
        assertTrue(b.play(letter1, 3, 3,scanner));
        Letter letter2 = new Letter('S', 1);
        assertTrue(b.play(letter2, 5, 10,scanner));

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

    //Word Tests for game logic
    @Test
    public void BasicWordCheck(){
    	Hand myHand = new Hand();
    	Scanner scanner = new Scanner(System.in);
        Board b = new Board(multipliers);
        Letter letterB = new Letter('B',3);
        Letter letterA = new Letter('A',1);
        Letter letterG = new Letter('G',2);
        assertTrue(b.play(letterB,3,4,scanner));
        assertTrue(b.play(letterA,3,5,scanner));
        assertTrue(b.play(letterG,3,6,scanner));
        assertTrue(b.checkWords(myHand));
    }

    @Test
    /*
      B     A      G 
      A            O
      T     O      T    A   L

      Tests putting multiple words onto the board
      Above is a visual of how it would look like
      All letters placed must make valid words 
     */
    public void AddingOntoWordCheck(){
    	Hand myHand = new Hand();
    	Scanner scanner = new Scanner(System.in);
        Board b = new Board(multipliers);
        Letter letterB = new Letter('B',3);
        Letter letterA = new Letter('A',1);
        Letter letterG = new Letter('G',2);
        Letter letterT = new Letter('T',1);
        Letter letterO = new Letter('O',1);
        Letter letterL = new Letter('L',1);
        assertTrue(b.play(letterB,3,4,scanner));
        assertTrue(b.play(letterA,3,5,scanner));
        assertTrue(b.play(letterG,3,6,scanner));
        assertTrue(b.checkWords(myHand));
        b.resetIterators();

        assertTrue(b.play(letterA,4,4,scanner));
        assertTrue(b.play(letterT,5,4,scanner));
        assertTrue(b.checkWords(myHand));
        b.resetIterators();

        assertTrue(b.play(letterO,5,5,scanner));
        assertTrue(b.play(letterT,5,6,scanner));
        assertTrue(b.play(letterA,5,7,scanner));
        assertTrue(b.play(letterL,5,8,scanner));
        assertTrue(b.checkWords(myHand));
        b.resetIterators();

        assertTrue(b.play(letterO,4,6,scanner));
        assertTrue(b.checkWords(myHand));
        b.resetIterators();

        //tests to ensure point methods are working correctly
        //with words connected to each other 
        // 1, 2, 1, 3, 1, 1 ,1 ,5, 1, 1, 1, 3, 1 ,2, 1
        assertEquals(17, b.getBoardScore());
    }

    //checking invalid words
    //does not currently work
    @Test
    public void  FalseWord1(){
    	Scanner scanner = new Scanner(System.in);
    	Hand myHand = new Hand();
        Board b = new Board(multipliers);
        Letter letterA = new Letter('A',3);
        Letter letterB = new Letter('B',3);
        Letter letterC = new Letter('C',3);
        //Letter letterA = new Letter('A',1);
        //Letter letterG = new Letter('G',2);
        assertTrue(b.play(letterA,3,4,scanner));
        assertTrue(b.play(letterB,3,5,scanner));
        assertTrue(b.play(letterC,3,6,scanner));
        assertFalse(b.checkWords(myHand));
    }
    @Test
    public void BasicPointCheck(){
    	int score = 0;
    	Hand myHand = new Hand();
    	Scanner scanner = new Scanner(System.in);
        Board b = new Board(multipliers);
        Letter letterB = new Letter('B',3);
        Letter letterA = new Letter('A',1);
        Letter letterG = new Letter('G',2);
        assertTrue(b.play(letterB,1,0,scanner));
        assertTrue(b.play(letterA,2,0,scanner));
        assertTrue(b.play(letterG,3,0,scanner));
        assertTrue(b.checkWords(myHand));
        score = b.calculateScore();
        assertEquals(b.getFoundIterator(), 0);
        assertEquals(6, score);
        
        b.resetIterators();
    }
    
    //not finished
    public void AddingOntoWordPointCheck(){
    	Hand myHand = new Hand();
    	Scanner scanner = new Scanner(System.in);
        Board b = new Board(multipliers);
        Letter letterB = new Letter('B',3);
        Letter letterA = new Letter('A',1);
        Letter letterG = new Letter('G',2);
        Letter letterT = new Letter('T',1);
        Letter letterO = new Letter('O',1);
        Letter letterL = new Letter('L',1);
        assertTrue(b.play(letterB,3,4,scanner));
        assertTrue(b.play(letterA,3,5,scanner));
        assertTrue(b.play(letterG,3,6,scanner));
        assertTrue(b.checkWords(myHand));
        b.resetIterators();

        assertTrue(b.play(letterA,4,4,scanner));
        assertTrue(b.play(letterT,5,4,scanner));
        assertTrue(b.checkWords(myHand));
        b.resetIterators();

        assertTrue(b.play(letterO,5,5,scanner));
        assertTrue(b.play(letterT,5,6,scanner));
        assertTrue(b.play(letterA,5,7,scanner));
        assertTrue(b.play(letterL,5,8,scanner));
        assertTrue(b.checkWords(myHand));
        b.resetIterators();

        assertTrue(b.play(letterO,4,6,scanner));
        assertTrue(b.checkWords(myHand));
        b.resetIterators();

        //tests to ensure point methods are working correctly
        //with words connected to each other 
        // 1, 2, 1, 3, 1, 1 ,1 ,5, 1, 1, 1, 3, 1 ,2, 1
        assertEquals(17, b.getBoardScore());
    }


}