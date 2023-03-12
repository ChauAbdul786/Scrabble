public class Letter {
    private char letter;
    private int points;
    
    // The constructor checks the validity of the arguments and throws an 'IllegalArgumentException' if either of them are invalid.
    public Letter() {
        this.letter = ' ';
        this.points = 0;
    }
    public Letter(char letter, int points) {
      if (!((Character.isAlphabetic(letter) && Character.isUpperCase(letter)) || letter == '-')) {
        throw new IllegalArgumentException("Invalid letter: " + letter);
      }
      if (points < 0) {
        throw new IllegalArgumentException("Invalid points: " + points);
      }
      this.letter = letter;
      this.points = points;
    }
    
    public char getLetter() {
      return letter;
    }
    
    public int getPoints() {
      return points;
    }
    
    // Returns 'true' if the argument is a 'Letter' object with the same letter and points, and 'false' otherwise.
    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (!(obj instanceof Letter)) {
        return false;
      }
      Letter other = (Letter) obj;
      return letter == other.letter && points == other.points;
    }
    
    // Returns a string representation of the letter in the format necessary.
    @Override
    public String toString() {
      return "Letter: " + letter + " Points: " + points;
    }
}
  
