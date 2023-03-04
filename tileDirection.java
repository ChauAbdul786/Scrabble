enum Direction{up,down,left,right,none}
public class tileDirection {
	private int X;
	private int Y;
	private Direction myDirection;
	public tileDirection(int a, int b, Direction c)
	{
		X = a;
		Y = b;
		myDirection = c;
	}
	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
	}
	public Direction getDirection() {
		return myDirection;
	}
}

