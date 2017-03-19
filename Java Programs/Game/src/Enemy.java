import java.awt.Rectangle;
import java.util.ArrayList;
// this class describes the basic object I use for an enemy, the blue ones.
public class Enemy {

	private Rectangle enemyImage;
	private Rectangle enemyBounds;
	private double xPos;
	private double yPos;
	
	private double speed;
	private String movementState;
	private String lastMovement;
	private long timeOfLastMovementChange;
	private boolean smartMovement;
	
	private int maxProjectiles;
	private ArrayList<Projectile> shots;
	private boolean firing;
	private long timeOfLastShot;
	private boolean ableToFire;
	
	public Enemy(){
		enemyImage = new Rectangle(20,40);
		
		xPos = (Math.random()*1925);
		yPos = (Math.random()*1400);
		
		movementState = "not moving";
		timeOfLastMovementChange = 0;
		speed = .5;
		smartMovement = true;
		
		maxProjectiles = 20;
		shots = new ArrayList(maxProjectiles);
		firing = false;
		ableToFire=false;
		timeOfLastShot = 0;
		refreshBounds();
		
	}
	//every time the object moves, the bounding box used in collisions must also be moved, in this case I
	//just make a new rectangle object in the same memory reference. Might be wasteful idk
	public void refreshBounds(){
		enemyBounds = new Rectangle((int)xPos,(int)yPos,20,40);
	}
	
	
	public void setMovement(){//random movement
		if(System.currentTimeMillis()>=timeOfLastMovementChange+500){
			int randomMovementNumber = (int)(Math.random()*10+1);
			switch(randomMovementNumber){
				case 1: movementState = "east"; break;
				case 2: movementState = "west"; break;
				case 3: movementState = "south"; break;
				case 4: movementState = "north"; break;
				case 5: movementState = "northeast"; break;
				case 6: movementState = "northwest"; break;
				case 7: movementState = "southeast"; break;
				case 8: movementState = "southwest"; break;
				case 9: movementState = "not moving"; break;
				case 10: movementState = "not moving"; break;
			}
			
			timeOfLastMovementChange = System.currentTimeMillis();
		}
	}

	public String getMovementState(){
		return movementState;
	}
	public void move(Player player){
		fireShot();
		if(smartMovement)
			smartMove(player);
		else
			setMovement();
			switch(movementState){
				case "east": xPos++;;break;
				case "west": xPos--; break;
				case "north": yPos--; break;
				case "south": yPos++; break;
				case "northeast": xPos++;
								  yPos--; break;
				case "northwest": xPos--;
								  yPos--; break;
				case "southeast": xPos++;
				                  yPos--; break;
				case "southwest": xPos++;
								  yPos++; break;
				case "not moving": break;
			}
		refreshBounds();
	}
	//This method is used in place of the random movement, moves the enemy in a straight line towards the player.
	public void smartMove(Player player){
		double xLeg = player.getXPos()-xPos;
		double yLeg = player.getYPos()-yPos;
		double hypot = Math.sqrt((xLeg*xLeg)+(yLeg*yLeg));
		double theta;
		if(yLeg<0)
			theta = -Math.acos(xLeg/hypot);
		else
			theta = Math.acos(xLeg/hypot);
		
		xPos+=Math.cos(theta)*speed;
		yPos+=Math.sin(theta)*speed;
			
	}
	
	
	public Rectangle getEnemyImage(){
		return enemyImage;
	}
	public Rectangle getBounds(){
		return enemyBounds;
	}
	//not used by the enemy, but i based this class off the player class which i made first, so its here
	//in case in the future i want to make the normal enemies shoot. also useful for making subclasses.
	public void fireShot(){
		if(firing){
			if(System.currentTimeMillis()>=(timeOfLastShot+250)){
				shots.add(new Projectile((int)xPos,(int)yPos+5,lastMovement));
				timeOfLastShot = System.currentTimeMillis();
			}
			for(int k = 0;k<shots.size();k++){
				Projectile shot = (Projectile)shots.get(k);
				if(shot.getXPos()>700 || shot.getXPos()<0 || shot.getYPos()>700|| shot.getXPos()<0){
					shots.remove(k);
					k--;
				}
			}
		}
	}
	
	public void setFiring(boolean isFiring){
		firing = isFiring;
	}
	
	public Projectile[] getProjectiles(){
		Projectile[] shotsArray = new Projectile[shots.size()];
		for(int k = 0;k<shots.size();k++)
			shotsArray[k]=shots.get(k);
		return shotsArray;
	}
	
	public Rectangle getEnemyBounds() {
		return enemyBounds;
	}
	public void setEnemyBounds(Rectangle enemyBounds) {
		this.enemyBounds = enemyBounds;
	}
	public double getXPos() {
		return xPos;
	}
	public void setXPos(double xPos) {
		this.xPos = xPos;
	}
	public double getYPos() {
		return yPos;
	}
	public void setYPos(double yPos) {
		this.yPos = yPos;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public String getLastMovement() {
		return lastMovement;
	}
	public void setLastMovement(String lastMovement) {
		this.lastMovement = lastMovement;
	}
	public long getTimeOfLastMovementChange() {
		return timeOfLastMovementChange;
	}
	public void setTimeOfLastMovementChange(long timeOfLastMovementChange) {
		this.timeOfLastMovementChange = timeOfLastMovementChange;
	}
	public boolean isSmartMovement() {
		return smartMovement;
	}
	public void setSmartMovement(boolean smartMovement) {
		this.smartMovement = smartMovement;
	}
	public int getMaxProjectiles() {
		return maxProjectiles;
	}
	public void setMaxProjectiles(int maxProjectiles) {
		this.maxProjectiles = maxProjectiles;
	}
	public ArrayList<Projectile> getShots() {
		return shots;
	}
	public void setShots(ArrayList<Projectile> shots) {
		this.shots = shots;
	}
	public long getTimeOfLastShot() {
		return timeOfLastShot;
	}
	public void setTimeOfLastShot(long timeOfLastShot) {
		this.timeOfLastShot = timeOfLastShot;
	}
	public boolean isFiring() {
		return firing;
	}
	public boolean isAbleToFire(){
		return ableToFire;
	}
	public void setAbleToFire(boolean ableToFire){
		this.ableToFire = ableToFire;
	}
	public void setEnemyImage(Rectangle enemyImage) {
		this.enemyImage = enemyImage;
	}
	public void setMovementState(String movementState) {
		this.movementState = movementState;
	}

}