import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.MouseInfo;
import java.awt.Point;
//This is the class that represents the player, it's actually pretty similar to the Enemy class.
public class Player {
	private Rectangle playerImage;
	private Rectangle playerBounds;
	private int xPos;
	private int yPos;
	
	private boolean movingUp;
	private boolean movingDown;
	private boolean movingRight;
	private boolean movingLeft;
	private String movementState;
	private String lastMovement;
	
	private int maxProjectiles;
	private ArrayList<Projectile> shots;
	private boolean firing;
	private long timeOfLastShot;
	
	private int cursorXPos;
	private int cursorYPos;
	private double aimAngle;
	private Point topLeftCursorOffset;
	private Point bottomRightCursorOffset;
	private Point posOnScreen;
	
	public Player(int x, int y){
		playerImage = new Rectangle(20,40);
		
		xPos = x;
		yPos = y;
		playerBounds = new Rectangle(xPos,yPos,20,40);
		
		movingUp = false;
		movingDown = false;
		movingRight = false;
		movingLeft = false;
		movementState = "not moving";
		
		cursorXPos = 0;
		cursorYPos = 0;
		posOnScreen = new Point();
		aimAngle = setAimCursor();
		topLeftCursorOffset = new Point();
		bottomRightCursorOffset = new Point();
		
		maxProjectiles = 20;
		shots = new ArrayList(maxProjectiles);
		firing = false;
		timeOfLastShot = 0;
		
	}
	
	//this would be much nicer to read as a switch statement, and maybe more efficient?
	//takes the input string that tells it what movement key has been pressed or released, and changes
	//the corresponding movement boolean.
	public void setMovement(String movement){
		if(movement.equals("right"))
			movingRight=true;
		else if(movement.equals("right-stop"))
			movingRight=false;
		else if(movement.equals("left"))
			movingLeft=true;
		else if(movement.equals("left-stop"))
			movingLeft=false;
		else if(movement.equals("down"))
			movingDown=true;
		else if(movement.equals("down-stop"))
			movingDown=false;
		else if(movement.equals("up"))
			movingUp=true;
		else if(movement.equals("up-stop"))
			movingUp=false;
		setMovementState();
	}
	
	//Theres definitely better ways (more efficient) to do this! this was just the first thing i thought of.
	//sets the movement to a cardinal direction based on boolean state changes that correspond to up,down,left,right
	//kinda counter intuitive, i dont like this method, the move method does not depend on the movementState string
	//but the booleans still.
	public void setMovementState(){
		if(movingUp && !movingDown){
			if(movingRight && !movingLeft)
				movementState = "northeast";
			else if(movingLeft && !movingRight)
				movementState = "northwest";
			else
				movementState = "north";
		}
		
		else if(movingDown && !movingUp){
			if(movingRight && !movingLeft)
				movementState = "southeast";
			else if(movingLeft && !movingRight)
				movementState = "southwest";
			else
				movementState = "south";
		}
		
		else if(movingRight && !movingLeft)
			movementState = "east";
		else if(movingLeft && !movingRight)
			movementState = "west";
		if(!movementState.equals("not moving"))
			lastMovement = movementState.substring(0,movementState.length());
		else
			movementState = "not moving";
		
		
	}
	public String getMovementState(){
		return movementState;
	}
	
	//this move method is called every cycle of the timer in the Environment. it moves the player based on
	//the up/down/left/right movement booleans. I think this is the simplest way to do this? I wanted to do
	// a movementState String instead that had more precise direction, but this works just as well and is
	//less lines of code.
	public void move(){
		fireShot();
		if(movingRight)
			xPos++;
		if(movingLeft)
			xPos--;
		if(movingDown)
			yPos++;
		if(movingUp)
			yPos--;
		refreshBounds();
	}
	//adjusts the bounding box for collisions, accounts for movement of the player so that the bounding box is
	//always around the player.
	public void refreshBounds(){
		playerBounds = new Rectangle(xPos,yPos,20,40);
	}

	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}
	
	public void setXPos(int x){
		xPos = x;
	}
	
	public void setYPos(int y){
		yPos = y;
	}
	public Rectangle getPlayerImage(){
		return playerImage;
	}
	public Rectangle getBounds(){
		return playerBounds;
	}
	
	public int getCursorXPos(){
		return cursorXPos;
	}
	
	public int getCursorYPos(){
		return cursorYPos;
	}
	
	//this method is not very eloquent, but it works. Because the screen itself has a different xy grid than
	//the window it makes it difficult to find the cursor. This method computes the necessary offset of the window
	//in relation to the screen itself and adjusts so that the aim is always pointing towards the cursor,
	//regardless of the location of the window, or the size of the window.
	public double setAimCursor(){
		Point cursorPos = MouseInfo.getPointerInfo().getLocation();
		cursorXPos = cursorPos.x;
		cursorYPos = cursorPos.y;
		try{
			if(cursorXPos>=topLeftCursorOffset.x && cursorXPos<=bottomRightCursorOffset.x)
				cursorXPos-=topLeftCursorOffset.x;
			if(cursorYPos>=topLeftCursorOffset.y && cursorYPos<=bottomRightCursorOffset.y)
				cursorYPos-=topLeftCursorOffset.y;
		}catch(NullPointerException ex){};
		int x = cursorXPos-posOnScreen.x+10;
		int y = -1*(cursorYPos-posOnScreen.y)+20;
		double cHypot = Math.sqrt((x*x)+(y*y));
		double theta;
		if(y<0)
			theta = -1*Math.acos(x/cHypot);
		else
			theta = Math.acos(x/cHypot);
		return theta;
	}
	
	//calculates the offset needing for getting the correct aimAngle because of size and position of the window.
	public void setOffset(Point playerP,Point p,int w, int h){
		topLeftCursorOffset = p;
		bottomRightCursorOffset = new Point(p.x+w,p.y+h);
		posOnScreen = playerP;
	}
	
	//adds a projectile to the shots array
	public void fireShot(){
		aimAngle = setAimCursor();
		if(firing){
			if(System.currentTimeMillis()>=(timeOfLastShot+250)){
					shots.add(new Projectile(xPos,yPos+5,aimAngle,"projectileImage.png"));
				timeOfLastShot = System.currentTimeMillis();
			}
		}
	}
	
	public void setFiring(boolean isFiring){
		firing = isFiring;
	}
	
	public ArrayList<Projectile> getProjectiles(){
		return shots;
	}
}
