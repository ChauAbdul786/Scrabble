public class Hand extends Letter{
    
    static final int MAX_SIZE = 8;
    private Letter[] letters;
    private int currSize;

    /*
    The default constructor should 
    create a hand of MAX_SIZE. 
    */
    public Hand(){
        this(MAX_SIZE);
    }

    /*
    This constructor should create a hand of size. 
    If size is less than zero, a hand of size zero
    should be created. If size is greater than 
    MAX_SIZE a hand of MAX_SIZE should be created.
    */
    public Hand(int size){
        if (size < 0) {
            size = 0;
        } else if (size > MAX_SIZE) {
            size = MAX_SIZE;
        }
        this.currSize = size;
        this.letters = new Letter[size];
    }

    public int getSize(){
        return currSize;
    }

    public Letter getLetter(int index) {
        if(index >= 0 && index < letters.length) {
            return letters[index];
        } else {
            return null;
        }
    }

    /*
    This method should search through the hand and 
    return the index of the first occurrence of letter. 
    If the letter is not in the hand, return -1. This
    does not remove a letter.
    */
    public int indexOf(char letter){
        for (int i = 0; i < currSize; i++){
            if (letters[i] != null && letters[i].getLetter() == letter) {
                return i;
            }
        }
        return -1;
    }

    /*
    If index is within maxhand size, the letter 
    should be inserted into the hand and return true.
    If those conditions do not hold, 
    false should be returned. 
    */
    public boolean insert(Letter letter, int index) {
        if (index < 0 || index >= currSize) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (letters[index] == null) {
            letters[index] = letter;
            return true;
        }
        return false;
    }

    /*
    returns the letter at index from the myLetter list
    */
    // public char retrieveLetter(int index){
    //     return myLetter.get(index).getLetter();
    // }

    /*
    removes a Letter from the myLetter list at designated
    index. returns the removed letter.
    */
    public Letter remove(int index) {
        if (index < 0 || index >= currSize) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Letter temp = letters[index];
        letters[index] = null;
        currSize--;
        return temp;
    }

    public boolean canForm(String word){
        if (word == null) {
            throw new NullPointerException("Empty word");
        }
        int[] count = new int[26];
        for (Letter letter : letters) {
            if (letter != null) {
                count[letter.getLetter() - 'A']++;
            }
        }
        for (char c : word.toCharArray()) {
            if (count[c - 'A'] == 0) {
                return false;
            }
            count[c - 'A']--;
        }
        return true;
    }

    /* 
    Returns a string based on the Letters in hand 
    */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < currSize; i++) {
            sb.append(i).append(": ");
            if (letters[i] != null) {
                sb.append(letters[i].toString());
            } else {
                sb.append("-");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
