import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Simple Scrabble, a console-based word game.
 */
public class PlayScrabble {

    public static final int[][] MULTIPLIERS = { { 1, 2, 1, 3, 1, 1, 1, 5, 1, 1, 1, 3, 1, 2, 1 },
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
    public static final int HAND_SIZE = 7;

    /**
     * Main loop for the game.
     * 
     * @param args command-line arguments (ignored)
     */
    public static void main(String[] args) {
        System.out.println("*** Welcome To Simple Scrabble ***");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        Board board = new Board(MULTIPLIERS);

        Hand hand = new Hand(HAND_SIZE);
        buildHand(hand);

        boolean done = false;

        while (!done) {
            // display the state
            System.out.println("=============================================");
            System.out.println(board);
            System.out.println("Your hand:");
            System.out.println(hand);

            // get the user input
            System.out.printf(
                    "Which row would you like to move to? (0-%d; %d quits)\n",
                    MULTIPLIERS.length - 1, MULTIPLIERS.length);
            int move1 = getMove(scanner);
            System.out.printf(
                "Which column would you like to move to? (0-%d; %d quits)\n",
                MULTIPLIERS.length - 1, MULTIPLIERS.length);
            int move2 = getMove(scanner);
            if (move1 == MULTIPLIERS.length && move2 == MULTIPLIERS.length) {
                done = true;
            } else {
                System.out.println("What letter would you like to play?");
                char letter = getPlay(scanner);

                // play the next move (if valid)
                int index = hand.indexOf(letter);
                if (index == -1) {
                    System.out.println("That letter isn't in your hand!");
                } else {
                    Letter letterToPlay = hand.remove(index);
                    boolean success = board.play(letterToPlay, move1, move2);
                    if (!success) {
                        hand.insert(letterToPlay, index);
                        System.out.println("Illegal move. Try again.");
                    } else {
                        hand.remove(index);
                        System.out.println("Played " + letterToPlay
                                + " at position " + move1 + ", " + move2);
                    }
                }
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("Your score is: " + board.getBoardScore());
        System.out.println("Bye!");
    }

    /**
     * Inserts random letters into the given hand.
     * 
     * @param hand the hand to build
     */
    private static void buildHand(Hand hand) {
        Random rnd = new Random();
        for (int i = 0; i < hand.getSize(); i++) {
            char letter = (char) ('A' + rnd.nextInt(26));
            int points = rnd.nextInt(5) + 1;
            hand.insert(new Letter(letter, points), i);
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

}