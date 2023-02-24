import java.util.*;
public class Bag {
    List<Letter> letters = new ArrayList<Letter>();

    //Constructs the bag. 
    //Letters of the same alphanumeric value will always be  
    //in one continuous stream in the list. 
    public Bag(){
        //'-' is used for blank tile 
        int currLetterPoints = 0;
        this.addLetters('-', currLetterPoints, 2);

        //1 point tiles 
        currLetterPoints = 1;
        this.addLetters('E', currLetterPoints, 12);
        this.addLetters('A', currLetterPoints, 9);
        this.addLetters('I', currLetterPoints, 9);
        this.addLetters('O', currLetterPoints, 8);
        this.addLetters('N', currLetterPoints, 6);
        this.addLetters('R', currLetterPoints, 6);
        this.addLetters('T', currLetterPoints, 6);
        this.addLetters('L', currLetterPoints, 4);
        this.addLetters('S', currLetterPoints, 4);
        this.addLetters('U', currLetterPoints, 4);

        //2 point tiles 
        currLetterPoints = 2;
        this.addLetters('D', currLetterPoints, 4);
        this.addLetters('G', currLetterPoints, 3);

        //3 point tiles 
        currLetterPoints = 3;
        this.addLetters('B', currLetterPoints, 2);
        this.addLetters('C', currLetterPoints, 2);
        this.addLetters('M', currLetterPoints, 2);
        this.addLetters('P', currLetterPoints, 2);

        //4 point tiles
        currLetterPoints = 4;
        this.addLetters('F', currLetterPoints, 2);
        this.addLetters('H', currLetterPoints, 2);
        this.addLetters('V', currLetterPoints, 2);
        this.addLetters('W', currLetterPoints, 2);
        this.addLetters('Y', currLetterPoints, 2);
        
        //5 point tiles
        currLetterPoints = 5;
        this.addLetters('K', currLetterPoints, 5);

        //8 point tiles
        currLetterPoints = 8;
        this.addLetters('J', currLetterPoints, 1);
        this.addLetters('X', currLetterPoints, 1);

        //10 point tiles
        currLetterPoints = 10;
        this.addLetters('Q', currLetterPoints, 1);
        this.addLetters('Z', currLetterPoints, 1);
    }

    //Private helper function for Bag constructor
    private void addLetters(char letter, int points, int amount){
        for(int i = 0; i < amount; i++){
            letters.add(new Letter(letter, points));
        }
    }
    
    //Retrives letter from the bag, removing it from the bag
    public Letter getLetter(char letter){
        for(int i = 0; i < letters.size(); i++){
            if(letters.get(i).getLetter() == letter){
                return (letters.remove(i)); //Check later, ArrayList<E>.remove(Object o) may possibly return boolean
                                            //No return type error is being shown for now.
            }
        }

        throw new IllegalArgumentException("Letter: " + letter + " is not in the bag");
    }

    //Retrieves a random letter from the bag, removing it from the bag
    public Letter getLetter(){
        int letterIndex = (int)(Math.random() * (letters.size()));
        return(letters.remove(letterIndex));
    }

    //Adds a letter to the bag, the letter will be conjoined in one continuous stream
    //with the rest of the letters of the same value within the list.
    public String addLetter(Letter letter){
        for(int i = 0; i < letters.size(); i++){
            if(letters.get(i) == letter){
                letters.add(i, letter);
                return letter.getLetter() + ": " + findAmountOfLetter(letter.getLetter()) + " (Already existed in bag)\n";
            }
        }

        letters.add(letter);
        return letter.getLetter() + ": 1 (Did not exist in bag)\n";
    }

    //Returns amount of letters of specified value in the bag
    public int findAmountOfLetter(char letter){
        int result = 0;
        for(int i = 0; i < letters.size(); i++){
            if(letters.get(i).getLetter() == letter){
                while(((i < letters.size()) && (letters.get(i).getLetter() == letter))){
                    result++;
                    i++;
                }
                return result;
            }
        }

        return result;
    }

    //Returns size of bag
    public int getSize(){
        return letters.size();
    }

    //Returns true if bag is empty, false otherwise
    public boolean isEmpty(){
        if(letters.size() > 0)
            return false;

        return true;
    }

    //Prints out the bag, grouping alike letters together.
    public String toString(){
        int size = letters.size();
        String result = "Bag Size: " + size + "\n";
        char letterToFind = '-';
        
        result += "Blank Tiles: " + findAmountOfLetter('-') + "\n";

        letterToFind = 'A';
        for(int i = 0; i < 26; i++){
            int amountOfCurrLetter = findAmountOfLetter(letterToFind);
            if(amountOfCurrLetter > 0){
                result += letterToFind + ": " + amountOfCurrLetter + "\n";
            }
            letterToFind++;
        }

        return result; 
    }
}
