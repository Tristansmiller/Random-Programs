import java.awt.Color;
import java.awt.Graphics;



public class Circle
{
	private int tlX;		// top-left X coordinate
	private int tlY;		// top-left Y coordinate
	private int incX;		// increment movement of X coordinate
	private int incY;		// increment movement of Y coordinate
	private boolean addX;	// flag to determine add/subtract of increment for X
	private boolean addY;	// flag to determine add/subtract of increment for Y
	private int size;		// diameter of the circle
	private int timeDelay;	// time delay until next circle is drawn
      
	public Circle(Graphics g, int s, int x, int y, int td)
	{
		incX = x;
		incY = y;
		size = s;
		addX = true;
		addY = false;
		tlX = 400;
		tlY = 300;
		timeDelay = td;
	}
   
	public void delay(int n)
	{
		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.currentTimeMillis();	
	}

	public void drawCircle(Graphics g)
	{
		g.setColor(Color.blue);
		g.drawOval(tlX,tlY,size,size);
		delay(timeDelay);
		if (addX)
			tlX+=incX;
		else
			tlX-=incX;
		if (addY)
			tlY+=incY;
		else
			tlY-=incY;
	}
   
   	 
	public void newData()
	{
		incX = (int) Math.round(Math.random() * 7 + 5);
		incY = (int) Math.round(Math.random() * 7 + 5);
	}

	public void hitEdge()
	{
		boolean flag = false;
		if (tlX < incX)
		{
			addX = true;
			flag = true;
		}
		if (tlX > 800 - (30 + incX)) 
		{
			addX = false;
			flag = true;
		}
		if (tlY < incY + 30) // The +30 is due to the fact that the title bar covers the top 30 pixels of the window
		{
			addY = true;
			flag = true;
		}
		if (tlY > 600 - (30 + incY)) 
		{
			addY = false;
			flag = true;
		}
		if (flag)
			newData();
	}
	
	public void eraseCircle(Graphics g,Coord xy)
	{
		g.setColor(Color.white);
		g.drawOval(xy.getX(),xy.getY(),size,size);
	}
	
	public int gettlX(){return tlX;}
	public int gettlY(){return tlY;}
	

}

