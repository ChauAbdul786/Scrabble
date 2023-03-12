import java.lang.*;
import java.util.Arrays;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Board {
    // Board is initialized as a double array of type letter
    private Letter[][] board;
    private int[][] pointMult;
    private int boardLength;
    private tileDirection[] playedTiles = new tileDirection[7];
    public int playedTilesIterator = -1;
    private tileDirection[] checkWords = new tileDirection[20];
    private int checkWordsIterator = -1;
    private tileDirection[] foundWords = new tileDirection[20];
    private int foundWordsIterator = -1;
    private String[] formedWords = new String[20];
    private int formedWordsIterator = -1;
    public ArrayList<String>[] Dictionary;

    // initializes a 15 x 15 board and copies the multiplier array into the
    // pointMult variable
    public Board(int[][] multiplier) {
        boardLength = 15;
        board = new Letter[boardLength][boardLength];
        pointMult = new int[boardLength][boardLength];
        Dictionary = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            Dictionary[i] = new ArrayList<String>();
        }
        pointMult = Arrays.stream(multiplier).map(int[]::clone).toArray(int[][]::new);
        try {
            File myObj = new File("Dictionary.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              Dictionary[data.charAt(0) - 'A'].add(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
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
            if (board[index1][index2] != null) {
            	playedTilesIterator = -1;
                return false;
            }
            board[index1][index2] = letter;
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index not available. " + e);
        }
        playedTilesIterator++;
        tileDirection temp = new tileDirection(index1,index2, Direction.none);
        playedTiles[playedTilesIterator] = temp;
        return true;
    }
    
    // finds all the words that a turn has created.
    // checks if each of those words are valid
    public boolean checkWords(){
    	findAffected();
    	findWords();
    	
    	playedTilesIterator = -1;
    	checkWordsIterator = -1;
    	
    	if(ValidateWords()){
    		formedWordsIterator = -1;
    		return true;
    	}
		formedWordsIterator = -1;
    	return false;
    }
    
    //finds all indexes which are affected by the played tiles
    
    //finds tiles that are affected by the played tiles and 
    //finds the words attached to ensure the new words are valid.
    private void findAffected(){
    	for (int i = playedTilesIterator; i != -1; i--){
    		checkAround(playedTiles[playedTilesIterator]);
    	}
    	playedTilesIterator = -1;
    }
    
    // checks around the tile to see if there is another tile that
    // has not been played this turn
    
    //checks the spaces around the index of the tile given
    private void checkAround(tileDirection myTile){
    	int row = myTile.getX();
    	int column = myTile.getY();
    	if(column-1 != -1) {
    		if(board[row][column-1] != null && !(inArray(row,column-1))){
    			tileDirection temp = new tileDirection(row,column-1, Direction.left);
    			checkWordsIterator++;
    			checkWords[checkWordsIterator] = temp;
    		}
    		else if(column + 1 !=15) {
    			if(board[row][column+1] != null && (!inArray(row,column+1))){
    				tileDirection temp = new tileDirection(row,column+1, Direction.right);
        			foundWordsIterator++;
        			foundWords[checkWordsIterator] = temp;
    			}
    		}
    	}
    	if(row-1 != -1) {
    		if(board[row-1][column] != null && !(inArray(row-1,column))){
    			tileDirection temp = new tileDirection(row-1,column, Direction.up);
    			checkWordsIterator++;
    			checkWords[checkWordsIterator] = temp;
    		}
    		else if(row + 1 !=15) {
    			if(board[row+1][column] != null && (!inArray(row+1,column))){
    				tileDirection temp = new tileDirection(row+1,column, Direction.down);
        			foundWordsIterator++;
        			foundWords[checkWordsIterator] = temp;
    			}
    		}
    	}
    	
    }
    
    //checks if the two index are in the array of played tiles.
    
    //Checks if the index tile is in the array of played tiles
    private boolean inArray(int row, int column){
    	for (int i = 0; i <= playedTilesIterator; i++) {
    		if(row == playedTiles[i].getX() && column == playedTiles[i].getY())
    				return true;
    	}
    	return false;
    }
    
    //finds the start of a word to be added to an array and the direction to go from there.

    //Finds the first letter of the affected words and then adds the
    //word it makes to the formedWords array
    private void findWords() {
    	tileDirection myTile;
    	int row, column, myLetters;
    	myLetters = checkWordsIterator;
    	while(myLetters != -1) {
    		myTile = checkWords[myLetters];
    		row = myTile.getX();
    		column = myTile.getY();
    		if(myTile.getDirection() == Direction.left ) {
    			while (board[row][column] != null) {
    				column --;
    				if(column == -1)
    				{
    					break;
    				}
    			}
    			foundWordsIterator++;
    			myTile = new tileDirection(row,column,Direction.right);
    			foundWords[myLetters] = myTile;
    		}
    		else if(myTile.getDirection() == Direction.up ) {
    			while (board[row][column] != null) {
    				row --;
    				if(row == -1)
    				{
    					break;
    				}
    			}
    			foundWordsIterator++;
    			myTile = new tileDirection(row,column,Direction.down);
    			foundWords[myLetters] = myTile;
    		}
    		myLetters --;
    	}
    	formWord();
    }
    
    // Uses the start of each word and forms a word to be placed in an array
    
    //Forms words from the first letter of the word.
    private void formWord() {
    	int myLetters, row , column;
    	tileDirection temp;
    	myLetters = foundWordsIterator;
    	while (myLetters != -1){
        	String myString = "";
    		temp = foundWords[myLetters];
    		row = temp.getX();
    		column = temp.getY();
    		if(temp.getDirection() == Direction.down) {
    			while(board[row][column] != null){
    				myString = myString + board[row][column].getLetter();
    				row++;
    				if(row == 15){
    					break;
    				}
    			}
    		}
    		else if(temp.getDirection() == Direction.right) {
    			while(board[row][column] != null){
    				myString = myString + board[row][column].getLetter();
    				column++;
    				if(column == 15){
    					break;
    				}
    			}
    		}
    		formedWordsIterator++;
    		formedWords[formedWordsIterator] = myString;
    		myLetters--;
    	}
    	
    }
    
    //Checks to see if all the formed words are valid.
    private boolean ValidateWords(){
    	for(int i = 1; i < formedWordsIterator+1; i++) {
    		boolean isFound = false;
    		String myWord = formedWords[i-1];
    		int hashNum = (myWord.charAt(0)- 'A');
    		for(int j = 0; j <Dictionary[hashNum].size();j++) {
    			if(myWord == Dictionary[hashNum].get(j)) {
    				isFound=true;
    				break;
    			}
    		}
    		if(!isFound) {
    			return false;
    		}
    		
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

    //Accessors for various private attributes
    public int getPlayedTilesX(int i){
        return playedTiles[i].getX();
    }
    public int getPlayedTilesY(int i){
        return playedTiles[i].getY();
    }

    public tileDirection[] getCheckWords(){
        return checkWords;
    }

    public tileDirection[] getFoundWords(){
        return foundWords;
    }

    public int getCheckIterator(){
        return checkWordsIterator;
    }

    public int getFoundIterator(){
        return foundWordsIterator;
    }

    public String[] getFormedWords(){
        return formedWords;
    }
    
    public int getFormedWordsIterator(){
        return formedWordsIterator;
    }
    //Converts the Board into a string
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
