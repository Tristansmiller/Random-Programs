
public class Coord {
	private int xPos;
	private int yPos;

	public Coord(int x, int y) 
	{
		xPos = x;
		yPos = y;		
	}
	public Coord(Coord c)
	{ 
		xPos = c.getX(); 
		yPos = c.getY();
	}
	
	public int getX(){ return xPos;}
	public int getY(){ return yPos;}

}
