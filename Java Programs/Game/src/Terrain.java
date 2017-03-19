import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Terrain {
	private Image image;
	private String imageName;
	private Rectangle bounds;
	private int xPos;
	private int yPos;
	
	public Terrain(int x,int y,Rectangle b,String iName){
		xPos = x;
		yPos = y;
		bounds = b;
		imageName = iName;
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

}
