import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
//this class represents the entire environment that the all the projectiles, enemies, and player are contained in.
public class Environment {
	private String environmentName;
	private int xSize;
	private int ySize;
	private Player player1;
	private ArrayList<Enemy> enemies;
	private int numEnemies;
	private int score;
	private int lastScoreIncrease;
	
	public Environment(){
		xSize = 4000;
		ySize = 4000;
		player1 = new Player(1000,1000);
		enemies = new ArrayList<Enemy>();
		numEnemies = 40;
		initEnemies();
		
	}
	//this method creates the arrayList of enemies. places them at random locations within the environment, but
	//ensures that they do not spawn within 400 pixels of the player. makes two gunners, and the rest normal.
	public void initEnemies(){
		for(int k =0;k<numEnemies-2;k++){
			boolean newEnemyIntersectsPlayer;
			do{
				enemies.add(new Enemy());
				Enemy newSpawn = enemies.get(enemies.size()-1);
				if((newSpawn.getXPos()<player1.getXPos()+200 && newSpawn.getXPos()>player1.getXPos()-200) &&
						(newSpawn.getYPos()<player1.getYPos()+200 && newSpawn.getYPos()>player1.getYPos()-200)){
					enemies.remove(enemies.size()-1);
					newEnemyIntersectsPlayer = true;
					System.out.println("enemy spawned on player, respawning enemy");
				}
				else
					newEnemyIntersectsPlayer = false;
			}while(newEnemyIntersectsPlayer);
		}
		
		
		for(int k=0;k<2;k++){
			boolean newEnemyIntersectsPlayer;
			do{
				enemies.add(new Gunner(player1));
				Enemy newSpawn = enemies.get(enemies.size()-1);
				if((newSpawn.getXPos()<player1.getXPos()+200 && newSpawn.getXPos()>player1.getXPos()-200) &&
						(newSpawn.getYPos()<player1.getYPos()+200 && newSpawn.getYPos()>player1.getYPos()-200)){
					enemies.remove(enemies.size()-1);
					newEnemyIntersectsPlayer = true;
					System.out.println("enemy spawned on player, respawning enemy");
				}
				else
					newEnemyIntersectsPlayer = false;
			}while(newEnemyIntersectsPlayer);
		}
	}
	
	//this method moves the player object, and makes sure it does not move past the edge of the environment.
	public void movePlayer(){
		player1.move();
		if(player1.getXPos()+20>xSize)
			player1.setXPos(xSize);
		if(player1.getXPos()<0)
			player1.setXPos(0);
		if(player1.getYPos()+40>ySize)
			player1.setYPos(ySize);
		if(player1.getYPos()<0)
			player1.setYPos(0);
		/*if(!playerMovement.equals(player1.getMovementState())){
			playerMovement = player1.getMovementState();	
			System.out.println(playerMovement);}*/ //use this if you need the movement state live
		
	}
	
	//this method moves all the projectiles, and deletes them if they go out of the environment.
	public void moveProjectiles(){
		if(player1.getProjectiles().size()!=0){
			ArrayList<Projectile> shots = player1.getProjectiles();
			for(int k=0;k<shots.size();k++){
				shots.get(k).move();
				if(shots.get(k).getXPos()>xSize || shots.get(k).getXPos()+15<0 ||
						shots.get(k).getYPos()>ySize || shots.get(k).getYPos()+15<0){
					shots.remove(k);
					k--;
				}
			}
		}
		for(int k = 0;k<enemies.size();k++){
			if(enemies.get(k) instanceof Gunner){
				ArrayList<Projectile> shots = enemies.get(k).getShots();
				for(int x=0;x<shots.size();x++){
					shots.get(x).move();
					if(shots.get(x).getXPos()>xSize || shots.get(x).getXPos()+15<0 ||
							shots.get(x).getYPos()>ySize || shots.get(x).getYPos()+15<0){
						shots.remove(x);
						x--;
					}
				}
			}
		}
	}
	//this method moves all of the enemies, and makes sure they dont move out of the environment.
	public void moveEnemies(){
		for(int k =0;k<enemies.size();k++){
			enemies.get(k).move(player1);
			if(enemies.get(k).getXPos()>xSize)
				enemies.get(k).setXPos(xSize);
			if(enemies.get(k).getXPos()<0)
				enemies.get(k).setXPos(0);
			if(enemies.get(k).getYPos()>ySize)
				enemies.get(k).setYPos(ySize);
			if(enemies.get(k).getYPos()<0)
				enemies.get(k).setYPos(0);
		}
				
	}
	//this method specifically checks if the player is currently colliding with any enemies, and throws a
	//collision exception if the player is.
	public void checkPlayerEnemyCollisions(Rectangle pb, Rectangle[] eb){
		Rectangle playerBounds = pb;
		Rectangle[] enemyBounds = eb;
		for(int k = 0;k<enemyBounds.length;k++){
			Rectangle intersection = enemyBounds[k].intersection(playerBounds);
			if(!intersection.isEmpty()&&(intersection.getHeight()*intersection.getWidth())>0)
				throw new CollisionException();
		}
		
	}
	
	//this method specifically checks if any of the players projectiles are colliding with any enemies.
	public void checkPlayerProjectileCollisions(Rectangle[] projB, Rectangle[] eb){
		Rectangle[] projectileBounds = projB;
		Rectangle[] enemyBounds = eb;
		for(int k=0;k<projectileBounds.length;k++){
			for(int x = 0;x<enemyBounds.length;x++){
				Rectangle intersection = projectileBounds[k].intersection(enemyBounds[x]);
				if(!intersection.isEmpty()&&(intersection.getHeight()*intersection.getWidth())>0){
					enemies.remove(x); score++;
					boolean newEnemyIntersectsPlayer;
					do{
						if((int)(Math.random()*4+1)==4)
							enemies.add(new Gunner(player1));
						else
							enemies.add(new Enemy());
						/*Rectangle intersection2 = enemies.get(enemies.size()-1).getBounds().intersection(playerBounds);
						if(!intersection2.isEmpty() && (intersection2.getHeight()*intersection2.getWidth())>0){*/
						Enemy newSpawn = enemies.get(enemies.size()-1);
						if((newSpawn.getXPos()<player1.getXPos()+200 && newSpawn.getXPos()>player1.getXPos()-200) &&
								(newSpawn.getYPos()<player1.getYPos()+200 && newSpawn.getYPos()>player1.getYPos()-200)){
							enemies.remove(enemies.size()-1);
							newEnemyIntersectsPlayer = true;
						}
						else
							newEnemyIntersectsPlayer = false;
					}while(newEnemyIntersectsPlayer);
				}
			}
		}
	}
	//this method specifically checks if any of the enemies projectiles are colliding with the player
	public void checkEnemyProjectileCollisions(Rectangle[] projB, Rectangle pB)
	{
		Rectangle[] projectileBounds = projB;
		Rectangle playerBounds = pB;
		
		for(int k =0;k<projectileBounds.length;k++){
			Rectangle intersection = projectileBounds[k].intersection(playerBounds);
			if(!intersection.isEmpty()&&(intersection.getHeight()*intersection.getWidth())>0)
				throw new CollisionException();
		}
	}
	
	//this is the general method called to detect collisions, gets all objects bounds and then calls the more
	//specific collision method. Originally, everything was inside this one method, but it was a huge method so
	//i broke it down into smaller parts.
	public void checkCollisions(){
		Rectangle playerBounds = player1.getBounds();
		Rectangle[] enemyBounds = new Rectangle[enemies.size()];
		for(int k =0;k<enemyBounds.length;k++)
			enemyBounds[k]=enemies.get(k).getBounds();
		
		ArrayList<Rectangle[]> enemyProjectileBounds = new ArrayList<Rectangle[]>();
		for(int k =0;k<enemies.size();k++)
			if(enemies.get(k) instanceof Gunner){
				ArrayList<Projectile> eShots = enemies.get(k).getShots();
				Rectangle[] currentProjs = new Rectangle[eShots.size()];
				for(int x = 0;x<eShots.size();x++)
					currentProjs[x] = eShots.get(x).getBounds();
				enemyProjectileBounds.add(currentProjs);
			}
		
		Rectangle[] playerProjectileBounds = new Rectangle[player1.getProjectiles().size()];
		for(int k =0;k<playerProjectileBounds.length;k++)
			playerProjectileBounds[k] = player1.getProjectiles().get(k).getBounds();
		
		
		checkPlayerEnemyCollisions(playerBounds, enemyBounds);
		checkPlayerProjectileCollisions(playerProjectileBounds, enemyBounds);
		for(int k = 0;k<enemyProjectileBounds.size();k++)
			checkEnemyProjectileCollisions(enemyProjectileBounds.get(k),playerBounds);
		
	}
	
	//this method is used to increase max number of enemies as the number of enemies destroyed increases.
	public void increaseDifficulty(){
		if(score==lastScoreIncrease+5){
			numEnemies++;
			lastScoreIncrease=score;
		}
	}
	
	//this method is the one called every cycle for the environment class, it does all the objects actions,
	//and then checks for collisions.
	public void processActions(){
		movePlayer();
		moveProjectiles();
		moveEnemies();
		checkCollisions();
		increaseDifficulty();
		
	}
	
	public void resetEnvironment(){
		player1 = new Player(1000,1000);
		enemies = new ArrayList<Enemy>();
		numEnemies = 40;
		score = 0;
		initEnemies();
	}
	
	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}

	public Player getPlayer() {
		return player1;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public int getNumEnemies() {
		return numEnemies;
	}

	public int getScore() {
		return score;
	}

	public int getLastScoreIncrease() {
		return lastScoreIncrease;
	}
	

	
}
