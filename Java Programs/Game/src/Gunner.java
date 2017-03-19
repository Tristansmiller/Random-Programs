import java.util.ArrayList;
//this class describes the enemy objects that can shoot at the player, is a subclass of Enemy.
public class Gunner extends Enemy {
	private double aimAngle;
	public Gunner(Player player){
		super();
		setSpeed(1);
		setFiring(true);
		setAbleToFire(true);
		setSmartMovement(false);
		refreshAim(player);
	}
	
	//Since both this object and the player move constantly, the angle to shoot must also be constantly adjusted
	//this method simply recalculates the aimAngle
	public void refreshAim(Player player){
		double xLeg = player.getXPos()-getXPos();
		double yLeg = player.getYPos()-getYPos();
		double hypot = Math.sqrt((xLeg*xLeg)+(yLeg*yLeg));
		double theta;
		if(yLeg<0)
			theta = Math.acos(xLeg/hypot);
		else
			theta = -Math.acos(xLeg/hypot);
		aimAngle = theta;
	}
	public void fireShot(){
		if(isFiring()){
			if(System.currentTimeMillis()>=(getTimeOfLastShot()+1000)){
					getShots().add(new Projectile((int)getXPos(),(int)getYPos()+5,aimAngle,"projectileImage2.png"));
				setTimeOfLastShot(System.currentTimeMillis());
			}
		}
	}
	
	//since the smartMovement boolean is false in this subclass, it uses random movement.
	public void move(Player player){
		refreshAim(player);
		fireShot();
		if(isSmartMovement())
			smartMove(player);
		else
			setMovement();
			switch(getMovementState()){
				case "east": setXPos(getXPos()+1);break;
				case "west": setXPos(getXPos()-1); break;
				case "north": setYPos(getYPos()-1); break;
				case "south": setYPos(getYPos()+1); break;
				case "northeast": setXPos(getXPos()+1);
				 				  setYPos(getYPos()-1); break;
				case "northwest": setXPos(getXPos()-1);
				 				  setYPos(getYPos()-1); break;
				case "southeast": setXPos(getXPos()+1);
								  setYPos(getYPos()-1); break;
				case "southwest": setXPos(getXPos()+1);
								  setYPos(getYPos()+1); break;
				case "not moving": break;
			}
		refreshBounds();
	}

}
