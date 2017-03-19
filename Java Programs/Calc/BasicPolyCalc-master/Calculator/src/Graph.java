
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Graph extends JFrame {
	public Graph(double[] xPoints, double[] yPoints,double domain, double range){
			add(new Grapher(xPoints,yPoints,domain,range));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(800,800);
			setLocationRelativeTo(null);
			setTitle("Graph");
			setResizable(false);
			setVisible(true);}
	private class Grapher extends JPanel{
		double[] x;
		double[] y;
		int[] graphingX;
		int[] graphingY;
		double domain;
		double range;
		
		public Grapher(double[] xPoints, double[] yPoints, double d,double r){
			x=xPoints;
			y=yPoints;
			setFocusable(true);
			setBackground(Color.WHITE);
			setDoubleBuffered(true);
			domain = d;
			range = r;
			graphingX = new int[x.length];
			graphingY = new int[y.length];
			convertPoints();
		}
		public void convertPoints(){
			double xScalingFactor = 800/domain;
			double yScalingFactor = 800/range;
			System.out.println(range);
			System.out.println(domain);
			System.out.println(yScalingFactor);
			System.out.println(xScalingFactor);
			for(int k = 0;k<x.length;k++){
				graphingX[k]=(int)(x[k]*xScalingFactor)+400;
				graphingY[k]=400-(int)(y[k]*yScalingFactor);
				//System.out.println("actual y : "+y[k] );
				//System.out.println("actual x : "+x[k] );
				//System.out.println("graphing coord x: "+graphingX[k]);
				//System.out.println("Graphing coord y: "+graphingY[k]);
			}
		}
		public void paint(Graphics g){
			drawGrid(g);
			drawLine(g);	
		}
		
		public void drawGrid(Graphics g){
			g.drawLine(0,400,800,400);
			g.drawLine(400, 0,400, 800);
		}
		
		public void drawLine(Graphics g){
			g.drawPolyline(graphingX, graphingY, graphingX.length);
		}
	}

}
