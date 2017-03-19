// Lab30ast.java
// The Screen Saver Program
// Student Version



import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.*;


public class Lab30ast
{    
	public static void main(String args[])  
	{
		GfxApp gfx = new GfxApp();
		gfx.setSize(800,600);
		gfx.addWindowListener(new WindowAdapter() {public void
			windowClosing(WindowEvent e) {System.exit(0);}});
		gfx.show();
	}
}












