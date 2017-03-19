package gui;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Projectile {
	private int x;
	private int y;
	private String imageName = "fireball.png";
	private Image image;
	private int height;
	private int width;
	private Rectangle bounds;
	private boolean special = false;
	
	public Projectile(){
		ImageIcon ii = new ImageIcon(this.getClass().getResource(imageName));
		if(Math.random()*20>=19){
			ii = new ImageIcon(this.getClass().getResource("fireballSpecial.png"));
			special = true;}
		image = ii.getImage();
		height = image.getHeight(null);
		width = image.getWidth(null);

		x = (int)(Math.random()*700);
		y = 0;
	    bounds = new Rectangle(x+5,y+7,width-10,height-7);
	}
	
	public void move(){
		if(special)
			y++;
		else{
			y+= 1;
			if(World.getLevel()>1)
				y+=(int)(World.getLevel()/5);
		}
		refreshBounds();
	}
	public void refreshBounds(){
		height = image.getHeight(null);
		width = image.getWidth(null);
		bounds = new Rectangle(x+5,y+7,width-10,height-7);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	public Image getImage(){
		return image;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public Rectangle getBounds(){
		return bounds;
	}
	public boolean isSpecial(){
		return special;
	}
	

}
