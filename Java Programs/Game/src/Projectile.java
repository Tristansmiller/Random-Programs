import java.awt.Rectangle;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.geom.AffineTransform;
import java.awt.Shape;
// this is the class for projectiles that enemies and players shoot.
public class Projectile {
	private Rectangle shape;
	private Shape trueBounds;
	private Rectangle projectileBounds;
	private double xPos;
	private double yPos;
	private String direction;
	private double movementAngle;
	private Image projectileImage;
	
	public Projectile(){
		shape = new Rectangle(10,5);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("projectileImage.png"));
		projectileImage = ii.getImage();
		xPos = 0;
		yPos = 0;
		projectileBounds = new Rectangle((int)xPos,(int)yPos,10,5);
		direction = "east";
		refreshBounds();
	}
	public Projectile(int x, int y, String d){
		shape = new Rectangle(10,5);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("projectileImage.png"));
		projectileImage = ii.getImage();
		xPos = x;
		yPos = y;
		direction = d;
		refreshBounds();
	}
	public Projectile(int x, int y, double a, String iName){
		shape = new Rectangle(10,5);
		ImageIcon ii = new ImageIcon(this.getClass().getResource(iName));
		projectileImage = ii.getImage();
		xPos = x;
		yPos = y;
		direction = "";
		movementAngle = -a;
		refreshBounds();
	}
	
	//this refreshes the bounding box, and since the projectiles are shot at an angle, the shape must be rotated
	//at a corresponding angle.
	public void refreshBounds(){
		double xAdjustment = Math.cos(movementAngle)*10;
		double yAdjustment = Math.sin(movementAngle)*10;
		AffineTransform at = new AffineTransform();
		at.translate(xPos, yPos);
		at.rotate(movementAngle);
		trueBounds = at.createTransformedShape(new Rectangle(10,5));
		projectileBounds = trueBounds.getBounds();
	}

	
	//simply moves the projectile in whichever angle it was shot at, is simple because the angle will not change.
	public void move(){
		xPos+=Math.cos(movementAngle)*2;
		yPos+=Math.sin(movementAngle)*2;
		refreshBounds();
	}
	
	public Rectangle getBounds(){
		return projectileBounds;
	}
	public double getXPos(){
		return xPos;
	}
	
	public double getYPos(){
		return yPos;
	}
	
	public String getDirection(){
		return direction;
	}
	
	public double getMovementAngle(){
		return movementAngle;
	}
	public Image getImage(){
		return projectileImage;
	}
	public Shape getTrueBounds(){
		return trueBounds;
	}
}
