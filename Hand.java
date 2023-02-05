
public class Hand extends Letter{
    
    private int maxsize = 7;
    private list<Letter> myLetter;
    private int currSize;

    /*
    The default constructor should 
    create a hand of MAX_SIZE. 
    */
    public hand(){
        this.myLetter =  new ArrayList<Letter>();
        this.currSize = 0;

    }

    /*
    This constructor should create a hand of size. 
    If size is less than zero, a hand of size zero
    should be created. If size is greater than 
    MAX_SIZE a hand of MAX_SIZE should be created.
    */
    public hand(int size){
        this.currSize = size;
        this.myLetter =  new ArrayList<Letter>();
        if( size < 0)
        {
            this.currSize = 0;
        }
        if (size > maxsize)
        {
            this.currSize = maxsize;
        }
    }

    public int getSize(){
        return currSize;
    }

    /*
    This method should search through the hand and 
    return the index of the first occurrence of letter. 
    If the letter is not in the hand, return -1. This
    does not remove a letter.
    */
    public int indexOf(char letter){
        for (int i = 0; i < myLetter.size(); i++){
            if (letter == myLetter.get(i).getLetter() ){
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
    public boolean insert(Letter letter){
        if (currSize < maxsize)
        {
            myLetter.add(letter);
            currSize++;
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
    returns the letter at index from the myLetter list
    */
    public char retrieveLetter(int index){
        return myLetter.get(index).getLetter();
    }

    /*
    removes a Letter from the myLetter list at designated
    index. returns the removed letter.
    */
    public Letter remove(int index){
        Letter temp = myLetter.get(index);
        myLetter.remove(index);
        return temp;
    }

    // not sure we need
    public boolean canForm(string word){
        if ( word == null)
        {
            throw new NullPointerException("Empty word");
        }
        if (word.size() > maxsize)
        {
            return false;
        }
        string myHand = this.toString();
        bool found = false;
        for (int i = 0; i < word.size(); i++)
        {
            for(int j = 0; j < myHand.size(); j++)
            {
                if(!found && word.at(i) == myHand.at(j))
                {
                    found = true;
                    myHand.remove(j);
                }
            }
            if (!found)
            {
                return false;
            }
            found = false;
        }
        return true;
    }

    /* 
    Returns a string based on the Letters in hand 
    */
    public String toString(){
        string myHand;
        for(int i = 0; i < currSize; i++)
        {
            myHand.add(myLetter.get(i).getLetter() );
        }
        return myHand;

    }



}