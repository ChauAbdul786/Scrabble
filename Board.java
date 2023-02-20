import java.lang.*;
import java.util.Arrays;

public class Board {
    // Board is initialized as a double array of type letter
    private Letter[][] board;
    private int[][] pointMult;
    private int boardLength;

    // initializes a 15 x 15 board and copies the multiplier array into the
    // pointMult variable
    public Board(int[][] multiplier) {
        boardLength = 15;
        board = new Letter[boardLength][boardLength];
        pointMult = new int[boardLength][boardLength];
        pointMult = Arrays.stream(multiplier).map(int[]::clone).toArray(int[][]::new);
        // System.arraycopy(multiplier, 0, pointMult, 0, multiplier.length);
    }

    // Returns a letter at a given index. Out of bounds exception thrown if the
    // index is not valid.
    public Letter getLetter(int index1, int index2) {
        if (index1 > boardLength || index2 > boardLength || index1 < 0 || index2 < 0) {
            throw new IndexOutOfBoundsException("Given Index is Out of Bounds " + index1 + " " + index2);
        }
        return board[index1][index2];
    }

    // Returns the point multiplier at a given index. Out of bounds exception thrown
    // if the index is not valid
    public int getPointMult(int index, int index2) {
        if (index > boardLength || index < 0 || index2 > boardLength || index2 < 0) {
            throw new IndexOutOfBoundsException("Given Index is Out of Bounds " + index);
        }
        int points = pointMult[index][index2];
        return points;
    }

    // Returns the total score of the board
    public int getBoardScore() {
        int score = 0;
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (board[i][j] != null) {
                    score = score + (board[i][j].getPoints() * pointMult[i][j]); // gets the points at every index and
                                                                                 // multiplies by the multiplier at the
                                                                                 // given column number
                }
            }
        }
        return score;
    }

    // Checks if there is a letter at a given index. Returns false if there is, true
    // if there is not AND sets the letter at the given index
    public boolean play(Letter letter, int index1, int index2) {
        try {
            if (board[index1][index2] != null)
                return false;
            board[index1][index2] = letter;
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index not available. " + e);
        }
        return true;
    }

    // Checks if a word will fit on the board
    public boolean fits(String word, int index1, int index2) {
        boolean doesFit = false;
        if (index1 > boardLength || index2 > boardLength || index1 < 0 || index2 < 0) {
            throw new IndexOutOfBoundsException("Index not available.");
        }
        // if the word length is longer than the boardlength at a given index,
        // immediately return
        if (word.length() + index1 > boardLength) {
            return false;
        }
        if (word.length() + index2 > boardLength) {
            return false;
        }
        // If there is a letter/word at any position within the starting index and the
        // length of the word, it cannot fit
        for (int i = index2; i < (word.length() + index2); i++) {// checks vertical
            if (board[index1][i] != null) {
                doesFit = false;
                break;
            }
            doesFit = true;
        }

        if (doesFit)
            return true;

        for (int i = index1; i < (word.length() + index1); i++) {// checks horizontal
            if (board[i][index2] != null) {
                doesFit = false;
                break;
            }
            doesFit = true;
        }

        return doesFit;
    }

    // returns an individual letters score at a given index
    // Need to update later for the special tiles cases
    // If there is no letter, return 0, otherwise it gets the letters points and
    // then multiplies it by the multiplier value
    public int getLetterScore(int index1, int index2) {
        if (board[index1][index2] == null) {
            return 0;
        }
        if ((index1 > boardLength) || (index2 > boardLength)) {
            throw new IndexOutOfBoundsException("Index Does Not Exist: " + index1 + " " + index2);
        }
        int score = board[index1][index2].getPoints();
        score = score * getPointMult(index1, index2);
        return score;
    }

    // Not sure that we need this
    @Override
    public String toString() {
        //
        return "";
    }

}
