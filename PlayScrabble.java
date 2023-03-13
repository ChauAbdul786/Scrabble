import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

/**
 * Simple Scrabble, a console-based word game.
 */
public class PlayScrabble {

    public static final int[][] MULTIPLIERS = { 
    { 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 },
    { 1, 1, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 1, 1 },
    { 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1 },
    { 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2 },
    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
    { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
    { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
    { 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 },
    { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
    { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
    { 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2 },
    { 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1 },
    { 1, 1, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 1, 1 },
    { 1, 1, 1, 2, 1, 1, 1, 3, 1, 1, 1, 2, 1, 1, 1 } };
    public static final int HAND_SIZE = 7;
    private static Hand[] playerHands;
    private static HandView[] playerHandGUIs;
    private static Bag bag;
    private static Board board;
    private static int[] Score;
    private static int skippedTurn;
    
    /**
     * Main loop for the game.
     * 
     * @param args command-line arguments (ignored)
     */
    public static void main(String[] args) {
        //terminalVersion();
        guiVersion();
    }

    public static void guiVersion(){
        /*PlayScrabbleView gui = new PlayScrabbleView();
        JFrame frame = new JFrame();
        gui.startGUI(frame);

        board = new Board(MULTIPLIERS);
        BoardView boardgui = new BoardView();
        boardgui.startBoardGUI(frame, board, MULTIPLIERS);

        bag = new Bag();

        Hand hand = new Hand(HAND_SIZE);
        buildHand(hand);
        HandView handgui = new HandView();
        handgui.startHandGUI(frame, hand);*/
        JFrame frame = new JFrame();

        MainMenuView mainMenuGUI = new MainMenuView();
        mainMenuGUI.startMainMenuGUI(frame);
        int players = -1;
        while(players == -1){
            try{
                players = mainMenuGUI.getPlayerSelection();
                Thread.sleep(200);
            }catch (InterruptedException e){
            }
        }

        PlayScrabbleView gui = new PlayScrabbleView();
        gui.startGUI(frame);

        playerHands = new Hand[players];
        playerHandGUIs = new HandView[players];
        Score = new int[players];
        skippedTurn = 0;

        board = new Board(MULTIPLIERS);
        bag = new Bag();

        board = new Board(MULTIPLIERS);
        BoardView boardgui = new BoardView();
        boardgui.startBoardGUI(frame, board, MULTIPLIERS);

        ScoresView scoreGUI = new ScoresView();
        scoreGUI.startScoresGUI(frame, players);

        Hand testHand = new Hand();
        buildHand(testHand);
        HandView testHandGUI = new HandView();
        testHandGUI.startHandGUI(frame, testHand);
    }

    public static void terminalVersion(){
        System.out.println("*** Welcome To Simple Scrabble ***");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("How many players?");
        System.out.println();
        
        int players = scanner.nextInt();
        int turn = 0;
        
        
        playerHands = new Hand[players];
        Score = new int[players];
        skippedTurn = 0;
        
        board = new Board(MULTIPLIERS);
        bag = new Bag();
        
        for(int i = 0; i < players; i++) {
        	playerHands[i] = new Hand(HAND_SIZE);
        	Score[i] = 0;
        	buildHand(playerHands[i]);
        }
        int action = 0;
        boolean turnOver = false;
        
      
        while(!gameEnd(players)) {
        	
        	System.out.printf("Player %d    Score: %d  Remaining tiles in bag: %d\n", turn + 1, Score[turn], bag.getSize());
        	System.out.println("Your hand:\n");
    		displayHand(turn);
        	
            
            //start timer 
            //long start = System.currentTimeMillis();
            //long end = start + 12 * 1000;
            
            while (!turnOver){
            	System.out.println("Press 1 to play a word, 2 to swap tiles, or 3 to pass your turn.");
            	action = scanner.nextInt();
            	if(action == 1){ // play a word
            		if(getTurn(scanner, turn)) {
            			skippedTurn = 0;
            			turnOver = true;
            		}
           		}
           		else if(action == 2) { // swap tiles   		
           			if(swapTiles(scanner, turn)) {
           				skippedTurn = 0;
            			turnOver = true;
            		}
           		}
            	else if(action == 3) {
            		skippedTurn++;
            		turnOver = true;
            	}
            	else {
            		System.out.println("Invalid");
            	}		
            }
            turnOver = false;
        	turn++;
        	if(turn == players) {turn = 0;}
            
        }
        
    }
    

    /**
     * Inserts random letters into the given hand.
     * 
     * @param hand the hand to build
     */
    private static void buildHand(Hand hand) {
        for (int i = 0; i < HAND_SIZE; i++) {
            hand.insert(bag.getLetter(), i);
        }
    }

    /**
     * Gets the next integer and consumes the newline. Repeats query until a
     * valid move is entered.
     * 
     * @param scanner the input
     * @return where to move
     */
    private static int getMove(Scanner scanner) {

        int nextInt = -1;
        boolean done = false;

        while (!done) {
            try {
                nextInt = scanner.nextInt();
                scanner.nextLine();
                done = nextInt >= 0 && nextInt <= MULTIPLIERS.length;
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
            if (!done) {
                System.out.printf("Please enter an integer from 0-%d.\n",
                        MULTIPLIERS.length);
            }
        }
        return nextInt;
    }

    /**
     * Gets the first letter of the next line of input.
     * 
     * @param scanner the input
     * @return the letter to play
     */
    private static char getPlay(Scanner scanner) {
        char letter = scanner.nextLine().charAt(0);
        return Character.toUpperCase(letter);
    }

    
    
    private static boolean gameEnd(int players) {
    	if (skippedTurn == 2*players) {return true;}
    	if(bag.isEmpty()) {
    		for(int i = 0; i < players; i++) {
    			if(playerHands[i].getSize() == 0) {return true;}
    		}
    	}
    	
    	return false;
    }
    
    
    private static boolean getTurn(Scanner scanner, int turn) {
    	int choice = 0;
    	int row,column, index = -1;
    	// 0- 6 are valid areas where tiles can be
    	// 7 is to finish (can be used to pass the turn as well
    	// 8 is to cancel
    	while(choice < 7) {
    		displayBoard();
    		System.out.println("Which tiles would you like to play? press 7 to finish or 8 to cancel\n");
    		System.out.println("Your hand:\n\n");
    		displayHand(turn);
    		choice = scanner.nextInt();
    		
    		if(choice >=0 && choice < HAND_SIZE) {
    			
	    		System.out.printf(
	                    "Which row would you like to move to? (0-%d; %d quits)\n",
	                    MULTIPLIERS.length - 1, MULTIPLIERS.length);
	    		row = getMove(scanner);
	            System.out.printf(
	                    "Which column would you like to move to? (0-%d; %d quits)\n",
	                    MULTIPLIERS.length - 1, MULTIPLIERS.length);
	            column = getMove(scanner);
	            index = choice;
	            if(playerHands[turn].getLetter(index) == null) {
	            		System.out.println("Not a valid number\n");
	            }
	            else if(!board.play(playerHands[turn].getLetter(index), row, column, scanner)) {
	            	playerHands[turn].remove(index);
	            	board.returnTiles(playerHands[turn]);
	            	return false;
	            }
	            else {playerHands[turn].remove(index);}
    		}
    		else if(choice == 7) {
    			if(board.checkWords(playerHands[turn]) && board.getLetter(7, 7) != null) {
    				for(int i = 0; i < HAND_SIZE; i ++) {
    					if(playerHands[turn].getLetter(i) == null){
    						playerHands[turn].insert(bag.getLetter(), i);
    					}
    				}
    				Score[turn] = Score[turn] + board.calculateScore();
    				board.resetIterators();
    				return true;
    			}
    			else {
    				System.out.println("illegal move!");
    				board.returnTiles(playerHands[turn]);
        			return false;
    			}
    		}
    		else if (choice == 8) {
    			board.returnTiles(playerHands[turn]);
    			return false;
    		}
    		else if(choice < 0 || choice >=9) {
    			System.out.println("invalid");
    			choice = 0;
    		}
            
    	}
    	return true;
    }
    
    private static boolean swapTiles(Scanner scanner, int turn) {
    	Letter[] toSwap = new Letter[7];
    	int choice = 0;
    	int iterator = -1;
    	int handIt = 0;
    	boolean added = false;
    	while(choice >=0 && choice < HAND_SIZE) {
    		System.out.printf("What letters do you want to swap out? press 7 to finish and 8 to cancel\n\n");
    		displayHand(turn);
    		choice = scanner.nextInt();
    		if (choice >=0 && choice < 7) {
    			if(playerHands[turn].getLetter(choice) != null) {
    				iterator ++;
    				toSwap[iterator] = playerHands[turn].getLetter(choice);
    				playerHands[turn].remove(choice);
    			}
    			else {
    				System.out.printf("Not a valid index\n");
    			}
    		}
    	}
    	if (choice == 7) {
    		for(int i = 0; i <= iterator; i++) {
				added = false;
    			while(!added) {
    				if(playerHands[turn].getLetter(handIt) == null) {
    					added = true;
    					playerHands[turn].insert(bag.getLetter(), handIt);
    				}// it should not go out of array
    				else {handIt++;}
    			}
    		}
    	}
    	
    		for(int k = 0; k <= iterator;k++) {
    			// add letters to the bag
    		}
    	
    	if(choice == 8) {
    		for(int i = 0; i <= iterator; i++) {
        		added = false;
    			while(!added) {
    				if(playerHands[turn].getLetter(handIt) == null) {
    					added = true;
    					playerHands[turn].insert(toSwap[iterator], handIt);
    				}
    				else {handIt++;}
    			}
    		}
    		return false;
    	}
    	else if(choice < 0 || choice >=9) {
			System.out.println("invalid");
			choice = 0;
		}
    	return true;
    	
    }
    // displays the board
    private static void displayBoard() {
    	for (int i = 0; i < 15; i ++) {
    		for(int k = 0; k <15; k++) {
    			if(board.getLetter(i,k) == null){
    				System.out.print("- ");
    			}
    			else {
    				System.out.print(board.getLetter(i,k).getLetter());
    				System.out.print(" ");
    			}
    		}
			System.out.print("\n");
    	}
    	System.out.print("\n");
    }
    
    //displays the hand
    private static void displayHand(int turn) {
    	char myLetter;
    	int points;
    	for(int i = 0; i < HAND_SIZE; i++) {
    		myLetter = '-';
    		points = 0;
    		if(playerHands[turn].getLetter(i) != null){
    			myLetter = playerHands[turn].getLetter(i).getLetter();
    			points = playerHands[turn].getLetter(i).getPoints();
    		}
    		System.out.printf("%d: %s Points: %d \n",i, myLetter, points);
    	}
    	System.out.printf("\n");
    }
}