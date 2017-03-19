import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class CollisionMapGenerator {
	private String imageName;
	private Image image;
	private BufferedImage bImage;
	private int width;
	private int height;
	private int[] rgbArray;
	private MyPixelColor[][] myRGBArray;
	private final int BLACK = (Color.BLACK).getRGB();
	private final int WHITE = (Color.WHITE).getRGB();
	
	public CollisionMapGenerator(String s){
		imageName = s;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(imageName));
		image = ii.getImage();
		bImage = createBufferedImage(image);
		width = image.getWidth(null);
		height = image.getHeight(null);
		rgbArray = bImage.getRGB(0, 0, width, height, rgbArray, 0, width);
		myRGBArray = createMyRGBArray(rgbArray);
	}
	
	public MyPixelColor[][] createMyRGBArray(int[] rgb){
		MyPixelColor[][]outputRGB = new MyPixelColor[height][width];
		for(int k = 0;k<rgb.length;k++){
			outputRGB[k/height][k%width] = new MyPixelColor(rgb[k]);
		}
		return outputRGB;
	}
	public BufferedImage createBufferedImage(Image img){
		BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = bi.createGraphics();
		bGr.drawImage(img,0,0,null);
		bGr.dispose();
		return bi;
		
	}
	
	public MyPixelColor[][] getRGBArray(){
		return myRGBArray;
	}
	public String getRGBArrayString(){
		String output = "";
		for(int k=0;k<myRGBArray.length;k++){
			for(int x=0;x<myRGBArray[0].length;x++){
				if(k!=myRGBArray.length-1)
					output+="("+myRGBArray[k][x]+") , ";
				else
					output+="("+myRGBArray[k][x]+")";
			}
			output+="\n";
		}
		return output;
	}
}
