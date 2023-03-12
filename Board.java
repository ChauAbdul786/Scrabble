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
    private Letter[] myTiles = new Letter[7];
    private tileDirection[] playedTiles = new tileDirection[7];
    private int playedTilesIterator = -1;
    private tileDirection[] checkWords = new tileDirection[20];
    private int checkWordsIterator = -1;
    private tileDirection[] foundWords = new tileDirection[20];
    private int foundWordsIterator = -1;
    private String[] formedWords = new String[20];
    private int formedWordsIterator = -1;
    public ArrayList<String>[] Dictionary;

    private intPairs[] triple = new intPairs[8];
    private intPairs[] doubles = new intPairs[17];

    private boolean horizontal;
    private boolean vertical;
    private int lowestTile;
    private int highestTile;



    // initializes a 15 x 15 board and copies the multiplier array into the
    // pointMult variable
    public Board(int[][] multiplier) {
        boardLength = 15;
        board = new Letter[boardLength][boardLength];
        pointMult = new int[boardLength][boardLength];
        Dictionary = new ArrayList[26];
        triple[0] = new intPairs(0,0);
        triple[1] = new intPairs(0,7);
        triple[2] = new intPairs(0,14);
        triple[3] = new intPairs(7,0);
        triple[4] = new intPairs(7,14);
        triple[5] = new intPairs(14,0);
        triple[6] = new intPairs(14,7);
        triple[7] = new intPairs(14,14);
        int a = 1, b= 1;
        for(int k = 0; k < 17; k++) {
        	if(k < 4) {
        		doubles[k] = new intPairs(a,b);
        		a++;
        		b++;
        	}
        	else if(k >= 4 && k < 8) {
        		if(k ==4) {
        			a = 1;
        			b = 13;
        		}
        		doubles[k] = new intPairs(a,b);
        		a++;
        		b--;
        	}
        	else if(k >= 8 && k < 12) {
        		if(k == 8) {
        			a = 10;
        			b = 4;
        		}
        		doubles[k] = new intPairs(a,b);
        		a++;
        		b--;
        		
        	}
        	else if(k >= 12 && k < 16){
        		if(k == 12) {
        			a = 10;
        		}
        		doubles[k] = new intPairs(a,a);
        		a++;
        	}
        	else if(k == 16) {doubles[k] = new intPairs(7,7);}
        }
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
    public boolean play(Letter letter, int index1, int index2, Scanner scanner) {
        try {
            if (board[index1][index2] != null) {
                return false;
            }
            if(letter.getPoints() == 0) {
            	System.out.println("What letter do you want to place");
            	char myLetter = scanner.nextLine().charAt(0);
                letter = new Letter(Character.toUpperCase(myLetter),0);
            }
            board[index1][index2] = letter;
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index not available. " + e);
        }
        playedTilesIterator++;
        tileDirection temp = new tileDirection(index1,index2, Direction.none);
        playedTiles[playedTilesIterator] = temp;
        myTiles[playedTilesIterator] = letter;
        return true;
    }
    
    // finds all the words that a turn has created.
    // checks if each of those words are valid

    public boolean checkWords(Hand myHand){


    	if(isLegal()) {
    		findAffected();
    		findWords();
    		if(ValidateWords()){

    			return true;
    		}
    	}
    	returnTiles(myHand);

    	resetIterators();
    	return false;
    }
    
    
    //checks to see if the tiles played are on the same horizontal axis or
    // same vertical axis then ensures that all tiles between are filled
    private boolean isLegal() {
    	if(playedTilesIterator >= 1){
    		horizontal = true;
    		vertical = true;
    		int myX = playedTiles[0].getX();
    		int myY = playedTiles[0].getY();
    		
    		for(int i = 0; i <= playedTilesIterator; i++) {
    			if(playedTiles[i].getX() != myX) {
    				horizontal = false;
    			}
    			if(playedTiles[i].getY() != myY) {
    				vertical = false;
    			}
    		}
    		
    		if(!horizontal && !vertical) {return false;}
    		
    		getRange();
    		
    		for(int j = lowestTile; j < highestTile; j++) {
    			if(horizontal) {
    				if(board[myX][j] == null) {return false;}
    			}
    			else if(vertical) {
    				if(board[j][myY] == null) {return false;}
    			}
    		}
    	}
    	return (horizontal || vertical);
    }
    
    //finds all indexes which are affected by the played tiles
    
    //finds tiles that are affected by the played tiles and 
    //finds the words attached to ensure the new words are valid.
    private void findAffected(){
    	for (int i = 0; i <= playedTilesIterator; i++){
    		checkAround(playedTiles[i]);
    	}
    }
    
    // checks around the tile to see if there is another tile that
    // has not been played this turn
    
    //checks the spaces around the index of the tile given
    private void checkAround(tileDirection myTile){
    	int row = myTile.getX();
    	int column = myTile.getY();
    	tileDirection temp;
    	
    	if(horizontal) {
    		if(column == lowestTile) {
    			// takes care of the left side
    			if( (column - 1) != -1) {
    				if(board[row][column -1] != null) {
    					temp = new tileDirection(row,column-1, Direction.left);
    					checkWordsIterator++;
    					checkWords[checkWordsIterator] = temp;
    				}
    				else {
    					temp = new tileDirection(row,column, Direction.right);
            			foundWordsIterator++;
            			foundWords[foundWordsIterator] = temp;
    				}
    			}
    			else {
    				temp = new tileDirection(row,column, Direction.right);

        			foundWordsIterator++;
        			foundWords[foundWordsIterator] = temp;
    			}
    		}

    		// takes care of all rows except the top
    		if (row - 1 != -1) {
    			if(board[row-1][column] != null) {
    				temp = new tileDirection(row-1,column, Direction.up);
        			checkWordsIterator++;
        			checkWords[checkWordsIterator] = temp;
    			}
    			else if (row + 1 != 15) {
    				if(board[row+1][column] != null) {
        				temp = new tileDirection(row+1,column, Direction.down);
        				foundWordsIterator++;
            			foundWords[foundWordsIterator] = temp;
        			}
    			}
    		}
    		// takes care of the top row
    		else {
				if(board[row+1][column] != null) {
    				temp = new tileDirection(row,column, Direction.down);
    				foundWordsIterator++;
        			foundWords[foundWordsIterator] = temp;
    			}
    		}
    	}
    	if(vertical) {
    		if(row == lowestTile) {
    			// takes care of the top side
    			if(row - 1 != -1) {
    				if(board[row - 1][column] != null) {
    					temp = new tileDirection(row -1,column, Direction.up);
    					checkWordsIterator++;
    					checkWords[checkWordsIterator] = temp;
    				}
    				else {
    					temp = new tileDirection(row,column, Direction.down);
            			foundWordsIterator++;
            			foundWords[foundWordsIterator] = temp;
    				}
    			}
    			else {
					temp = new tileDirection(row,column, Direction.down);

        			foundWordsIterator++;
        			foundWords[foundWordsIterator] = temp;
				}
    		}
    		// takes care of all column except the left
    		if (column - 1 != -1) {
    			if(board[row][column-1] != null) {
    				temp = new tileDirection(row-1,column, Direction.left);
        			checkWordsIterator++;
        			checkWords[checkWordsIterator] = temp;
    			}
    			else if (column + 1 != 15) {
    				if(board[row][column + 1] != null) {
        				temp = new tileDirection(row+1,column, Direction.right);
        				foundWordsIterator++;
            			foundWords[foundWordsIterator] = temp;
        			}
    			}
    		}
    		// takes care of the left column
    		else {
				if(board[row][column + 1] != null) {
    				temp = new tileDirection(row,column, Direction.right);
    				foundWordsIterator++;
        			foundWords[foundWordsIterator] = temp;
    			}
    		}
    	}
    }
    
    //gets the range of the tiles from the lowest index to highest
    // used to check every tile in between to ensure it is continuous
    // from the lowest index to the highest index
    private void getRange() {
    	lowestTile = 15;
    	highestTile = -1;
    	if(playedTilesIterator >= 0) {
    		for(int i = 0; i <= playedTilesIterator; i++) {
    			if(horizontal) {
    				if(playedTiles[i].getY() > highestTile) {
    					highestTile = playedTiles[i].getY();
    				}
    				if(playedTiles[i].getY() < lowestTile) {
    					lowestTile = playedTiles[i].getY();
    				}
    			}
    			else if(vertical) {
    				if(playedTiles[i].getX() > highestTile) {
    					highestTile = playedTiles[i].getX();
    				}
    				if(playedTiles[i].getX() < lowestTile) {
    					lowestTile = playedTiles[i].getX();
    				}
    			}
    		}
    	}
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
    			myTile = new tileDirection(row,column + 1,Direction.right);
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
    			myTile = new tileDirection(row+1,column,Direction.down);
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

    	for(int i = 0; i <= formedWordsIterator; i++) {

    		boolean isFound = false;
    		String myWord = formedWords[i-1];
    		int hashNum = (myWord.charAt(0)- 'A');
    		for(int j = 0; j <Dictionary[hashNum].size();j++) {
    			if(myWord.equals(Dictionary[hashNum].get(j))) {
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
    

    //calculates the score for the turn
    public int calculateScore() {
    	int score = 0;
    	int wordScore = 0;
    	int scoreMulti = 1;
    	int myLetters, row =0 , column= 0;
    	int k;
    	boolean inArray = false;
    	tileDirection temp;
    	myLetters = foundWordsIterator;
    	while (myLetters != -1){
    		temp = foundWords[myLetters];
    		wordScore = 0;
    		scoreMulti = 1;
    		row = temp.getX();
    		column = temp.getY();
    		if(temp.getDirection() == Direction.down) {
    			while(board[row][column] != null){
    				inArray = false;
    				for(int i = 0; i < playedTilesIterator;i++) {
    					if(playedTiles[i].getX() == row && playedTiles[i].getY() == column) {
    						inArray = true;
    						wordScore = wordScore + (board[row][column].getPoints() * pointMult[row][column]);
    						k = 0;
    						while(k <17) {
    							if(k<8) {
    								if(triple[k].getX() == row && triple[k].getY() == column) {
    									scoreMulti = 3;
    								}
    							}
    							if(doubles[k].getX() == row && doubles[k].getY() == column) {
    									scoreMulti = 2;
    								}
    							k++;
    						}
    						break;
    					}
    				}
    				if(!inArray) {
    					wordScore = wordScore + board[row][column].getPoints();
    				}
    				row++;
    				if(row == 15){
    					break;
    				}
    			}
				wordScore = wordScore * scoreMulti;
    		}
    		else if(temp.getDirection() == Direction.right) {
    			while(board[row][column] != null){
    				inArray = false;
    				for(int i = 0; i < playedTilesIterator;i++) {
    					if(playedTiles[i].getX() == row && playedTiles[i].getY() == column) {
    						inArray = true;
    						wordScore = wordScore + (board[row][column].getPoints() * pointMult[row][column]);
    						k = 0;
    						while(k <17) {
    							if(k<8) {
    								if(triple[k].getX() == row && triple[k].getY() == column) {
    									scoreMulti = 3;
    								}
    							}
    							if(doubles[k].getX() == row && doubles[k].getY() == column) {
    									scoreMulti = 2;
    								}
    							k++;
    						}
    						break;
    					}
    				}
    				if(!inArray) {
    					wordScore = wordScore + board[row][column].getPoints();
    				}
    				column++;
    				if(column == 15){
    					break;
    				}
    			}
				wordScore = wordScore * scoreMulti;
    		}
    		score = score + wordScore;
    		myLetters--;
    	}
    	
    	return score;
    }
    
    //resets all iterators
    public void resetIterators() {



    	playedTilesIterator = -1;
    	checkWordsIterator = -1;
    	foundWordsIterator = -1;
		formedWordsIterator = -1;
    }
    

    public void returnTiles(Hand myHand) {
    	int x,y;
    	for(int i = 0; i <= playedTilesIterator; i ++) {
    		x = playedTiles[i].getX();
    		y = playedTiles[i].getY();
    		board[x][y] = null;
    		for(int index = 0; index < 7; index++) {
    			if(myHand.getLetter(index) == null) {
    				myHand.insert(myTiles[i], index);
    				break;
    			}
    		}
    	}
    	
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
    public int getPlayedIterator(){
        return playedTilesIterator;
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