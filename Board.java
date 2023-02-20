import java.lang.*;
import java.util.Arrays;

public class Board {
    //Board is initialized as a double array of type letter
    private Letter[][] board;
    private int[][] pointMult;
    int boardLength;

    //initializes a 15 x 15 board and copies the multiplier array into the pointMult variable
    public Board(int[][] multiplier) {
        // int length = multiplier.length;
        // board = new Letter[length][length];
        // pointMult = new int[length][length];
        // for (int i = 0; i < length; i++) {
        //     System.arraycopy(multiplier, 0, pointMult[i], 0, length);
        // }
        boardLength = 15;
        board = new Letter[boardLength][boardLength];
        pointMult = new int[boardLength][boardLength];
        pointMult = Arrays.stream(multiplier).map(int[]::clone).toArray(int[][]::new);
    }

    //Returns a letter at a given index. Out of bounds exception thrown if the index is not valid.
    public Letter getLetter(int row, int col) {
        // if (row >= board.length || col >= board[0].length || row < 0 || col < 0) {
        //     throw new ArrayIndexOutOfBoundsException();
        // }
        // return board[row][col];
        if (row > boardLength || col > boardLength || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException("Given Index is Out of Bounds " + row + " " + col);
        }
        return board[row][col];
    }

    //Returns the point multiplier at a given index. Out of bounds exception thrown if the index is not valid 
    public int getPointMult(int row, int col) {
        // if (row >= board.length || row < 0 || col >= board[0].length || col < 0) {
        //     throw new ArrayIndexOutOfBoundsException();
        // }
        // return pointMult[row][col];
        if (row > boardLength || row < 0 || col > boardLength || col < 0) {
            throw new IndexOutOfBoundsException("Given Index is Out of Bounds " + row);
        }
        return pointMult[row][col];
    }

    //Returns the total score of the board
    public int getBoardScore() {
        // int score = 0;
        // for (int i = 0; i < board.length; i++) {
        //     for (int j = 0; j < board[0].length; j++) {
        //         Letter letter = board[i][j];
        //         if (letter != null) {
        //             score += letter.getPoints() * pointMult[i][j];
        //         }
        //     }
        // }
        // return score;

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

    //Checks if there is a letter at a given index. Returns false if there is, true if there is not AND sets the letter at the given index
    public boolean play(Letter letter, int row, int col) {
        // if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
        //     throw new ArrayIndexOutOfBoundsException();
        // }  
        // if (board[row][col] != null) {
        //     return false;
        // }
        // board[row][col] = letter;
        // return true;
        try {
            if (board[row][col] != null)
                return false;
            board[row][col] = letter;
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index not available. " + e);
        }
        return true;
    }
    
    //Checks if a word will fit on the board
    public boolean fits(String word, int row, int col) {
        // boolean doesFit = false;
        // if (row < 0 || row >= board.length || col < 0 || col > board[0].length) {
        //     throw new ArrayIndexOutOfBoundsException();
        // }
        // //if the word length is longer than the boardlength at a given index, immediately return
        // if (word.length() + row > board.length) {
        //     return false;
        // }
        // //If there is a letter/word at any position within the starting index and the length of the word, it cannot fit
        // for (int i = 0; i < word.length(); i++) {//checks vertical
        //     if (board[row + i][col] != null && board[row + i][col] != new Letter(word.charAt(i), 0)) {
        //         return false;
        //     }
        // }
        // return true;

        boolean doesFit = false;
        if (row > boardLength || col > boardLength || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException("Index not available.");
        }
        // if the word length is longer than the boardlength at a given index,
        // immediately return
        if (word.length() + row > boardLength) {
            return false;
        }
        if (word.length() + col > boardLength) {
            return false;
        }
        // If there is a letter/word at any position within the starting index and the
        // length of the word, it cannot fit
        for (int i = col; i < (word.length() + col); i++) {// checks vertical
            if (board[row][i] != null) {
                doesFit = false;
                break;
            }
            doesFit = true;
        }

        if (doesFit)
            return true;

        for (int i = row; i < (word.length() + row); i++) {// checks horizontal
            if (board[i][col] != null) {
                doesFit = false;
                break;
            }
            doesFit = true;
        }

        return doesFit;
    }

    //returns an individual letters score at a given index
    //Need to update later for the special tiles cases
    //If there is no letter, return 0, otherwise it gets the letters points and then multiplies it by the multiplier value
    public int getLetterScore(int row, int col) {
        // if((row >= board.length)|| row < 0 || col >= board.length || col < 0){
        //     throw new ArrayIndexOutOfBoundsException();
        // }
        // Letter letter = board[row][col];
        // if(letter == null){
        //     return 0;
        // }
        // return letter.getPoints() * pointMult[row][col];

        if (board[row][col] == null) {
            return 0;
        }
        if ((row > boardLength) || (col > boardLength)) {
            throw new IndexOutOfBoundsException("Index Does Not Exist: " + row + " " + col);
        }
        int score = board[row][col].getPoints();
        score = score * getPointMult(row, col);
        return score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(i + "," + j + ": ");
                Letter letter = board[i][j];
                if (letter != null) {
                    sb.append(letter.toString() + "\n");
                } else {
                    sb.append("-\n");
                }
                sb.append("Multiplier: " + pointMult[i][j] + "\n");
            }
        }
        return sb.toString();
    }
}
