import java.awt.Color;

public class MyPixelColor {
	
	private int red;
	private int blue;
	private int green;
	private int alpha;
	private Color color;
	private int rgb;
	
	public MyPixelColor(int colorNum){
		color = new Color(colorNum);
		red = color.getRed();
		blue = color.getBlue();
		green = color.getGreen();
		alpha = color.getAlpha();
		rgb = colorNum;
	}
	
	public String toString(){
		return "{"+red+","+green+","+blue+"}";
	}
	public int getRed(){
		return red;
	}
	
	public int getBlue(){
		return blue;
	}
	
	public int getGreen(){
		return green;
	}
	
	public int getAlpha(){
		return alpha;
	}
	
	public Color getColor(){
		return color;
	}

}
