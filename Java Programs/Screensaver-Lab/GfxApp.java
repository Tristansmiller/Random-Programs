import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.*;


public class GfxApp extends Frame {

	private int circleCount, circleSize, circleTime;
	private CoordQueue circleCoords;
	
	public GfxApp()
	{
		circleTime = Integer.parseInt(JOptionPane.showInputDialog("Input how many seconds you want the screensaver to be on."));
		circleCount = Integer.parseInt(JOptionPane.showInputDialog("Input the amount of circles on screen."));
		circleSize  = Integer.parseInt(JOptionPane.showInputDialog("Input the size of the circles"));
		circleCoords = new CoordQueue(circleCount);
	}
			


   	
	public void paint(Graphics g)
	{
		
		int incX = 5;
		int incY = 5;
		int diameter = circleSize;
		int timeDelay = 10;
		Circle c = new Circle(g,diameter,incX,incY,timeDelay);
		for (int k = 1; k <= circleTime*30; k++)
		{	
			if(circleCoords.isFull())
				c.eraseCircle(g,circleCoords.add(new Coord(c.gettlX(),c.gettlY())));
			else
				circleCoords.add(new Coord(c.gettlX(),c.gettlY()));
			c.drawCircle(g);
			c.hitEdge();
		}

	} 

}
