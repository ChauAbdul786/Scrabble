import java.lang.*;

public class Board {
    private Letter[][] board;
    private int[] pointMult;
    private int boardLength;

    public Board(int multiplier[]) {
        boardLength = multiplier.length;
        board = new Letter[boardLength][boardLength];
        pointMult = new int[boardLength];
        System.arraycopy(multiplier, 0, pointMult, 0, multiplier.length);
    }

    public Letter getLetter(int index1, int index2) {
        if (index1 > boardLength || index2 > boardLength) {
            throw new IndexOutOfBoundsException("Given Index is Out of Bounds " + index1 + " " + index2);
        }
        return board[index1][index2];
    }

    public int getPointMult(int index) {
        if (index > boardLength) {
            throw new IndexOutOfBoundsException("Given Index is Out of Bounds " + index);
        }
        return pointMult[index];
    }

    public int getBoardScore() {
        int score = 0;
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                score = board[i][j].getPoints() * pointMult[j];
            }

        }
        return score;
    }

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

    public boolean fits(String word, int index1, int index2) {
        boolean doesFit = false;

        if (word.length() + index1 > boardLength) {
            return false;
        }
        if (word.length() + index2 > boardLength) {
            return false;
        }

        for (int i = index2; i < (word.length() + index2); i++) {
            if (board[index1][i] != null) {
                doesFit = false;
                break;
            }
            doesFit = true;
        }

        if (doesFit)
            return true;

        for (int i = index1; i < (word.length() + index1); i++) {
            if (board[i][index2] != null) {
                doesFit = false;
                break;
            }
            doesFit = true;
        }

        return doesFit;
    }

    public int getLetterScore(int index1, int index2) {
        return 0;
    }

    @Override
    public String toString() {

        return "";
    }

}
