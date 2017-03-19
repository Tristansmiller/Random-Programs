package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.awt.Rectangle;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.Timer;


public class World extends JPanel implements ActionListener{
	
	private Timer timer;
	private Player player1;
	private Player player2;
	private ArrayList<Projectile> projectiles;
	private int maxProjectiles;
	InputMap im;
	ActionMap am;
	private int score;
	private boolean gameOver = false;
	private int fps;
	private int fpsCounter;
	private long startTime;
	private Color[] backgroundColors = {Color.BLACK,Color.BLUE,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.YELLOW};
	private static int level=1;
	private static int phase=1;
	private static int highScore;
	private static boolean visibleBounding=false;
	private static boolean isPaused=false;
	private static boolean isCollisions=true;
	private static boolean twoPlayer = false;

	
	public World(){
		
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		initKeyBindings();
		
		score = 0;
		startTime = System.currentTimeMillis();
		fpsCounter = 0;
		
		
		player1 = new Player(1);
		projectiles = new ArrayList<Projectile>();
		projectiles.add(new Projectile());
		maxProjectiles = 10;
		
		timer = new Timer(5,this);
		timer.start();
	}
	
	public void initKeyBindings(){
		InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getActionMap();
		
	//	im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,false), "up-pressed");
	//	im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,true),"up-released");
		
	//	im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,false), "down-pressed");
	//	im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,true),"down-released");
		
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,false), "right-pressed");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,true),"right-released");
		
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,false), "left-pressed");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,true),"left-released");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false),"enter-pressed");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_B,0,false),"b-pressed");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C,0,false),"c-pressed");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0,false),"space-pressed");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0,false),"escape-pressed");
		//im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0,false),"w-pressed");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0,false),"a-pressed");
		//im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0,false),"s-pressed");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0,false),"d-pressed");
		//im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0,true),"w-released");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0,true),"a-released");
		//im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0,true),"s-released");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0,true),"d-released");
		
		
		
		
		
		/*am.put("up-pressed", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){	
						player.setMovement("up");
					}
				});
		am.put("up-released", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){	
						player.setMovement("up-stop");
					}
				});
		am.put("down-pressed", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){	
						player.setMovement("down");
					}
				});
		am.put("down-released", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){	
						player.setMovement("down-stop");
					}
				});*/
		am.put("d-released", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){
						if(twoPlayer)
							player2.setMovement("right-stop");
					}
				});
		am.put("d-pressed", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){
						if(twoPlayer)
							player2.setMovement("right");
					}
				});
		am.put("a-released", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){
						if(twoPlayer)
						player2.setMovement("left-stop");
					}
				});
		am.put("a-pressed", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){
						if(twoPlayer)
						player2.setMovement("left");
					}
				});
		am.put("escape-pressed", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){
						if(!twoPlayer){
						player2 = new Player(2);
						twoPlayer = true;}
						else
							twoPlayer = false;
					}
				});
		am.put("space-pressed", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){	
						if(isPaused){
							timer.start();
							isPaused=false;}
						else{
							timer.stop();
							isPaused=true;}
					}
				});
		am.put("c-pressed", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){	
						if(isCollisions)
							isCollisions=false;
						else
							isCollisions=true;
					}
				});
		am.put("b-pressed", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){	
						if(visibleBounding)
							visibleBounding=false;
						else
							visibleBounding=true;
					}
				});	
		am.put("enter-pressed", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){	
						if(gameOver)restartGame();
					}
				});
		am.put("right-pressed", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){	
						player1.setMovement("right");
					}
				});
		am.put("right-released", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){	
						player1.setMovement("right-stop");
					}
				});
		am.put("left-pressed", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){	
						player1.setMovement("left");
					}
				});
		am.put("left-released", 
				new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent e){	
						player1.setMovement("left-stop");
					}
				});
			
		}
		
	public void paint(Graphics g){
		fpsCounter++;
		super.paint(g);
		g.setColor(Color.WHITE);
		g.drawString("Score : "+score, 350, 100);
		g.drawString("High Score: "+highScore,350,150);
		g.drawString("Fireballs: "+projectiles.size(),100,100);
		g.drawString("Two Player: "+twoPlayer, 100, 150);
		g.drawString("FPS: "+fps, 100, 200);
		g.setColor(Color.DARK_GRAY);
		drawPlayer(g,1);
		if(twoPlayer)
			drawPlayer(g,2);
		drawAllProjectiles(g);
		if(visibleBounding)
			drawBoundingBoxes(g);
		if(gameOver)
			drawGameOverScreen(g);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	public void drawBoundingBoxes(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.draw(player1.getBounds());
		if(twoPlayer)
			g2d.draw(player2.getBounds());
		for(int k = 0;k<projectiles.size();k++){
			g2d.draw(projectiles.get(k).getBounds());
		}
	}
	public void drawGameOverScreen(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		//g2d.scale(10, 10);
		g2d.setColor(Color.WHITE);
		g2d.drawString("GAME OVER         SCORE: "+score+"           PRESS ENTER TO RESTART",100,350);
	}
	public void drawPlayer(Graphics g, int numPlayer){
		if(numPlayer == 1){
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform at = new AffineTransform();
		at.translate(player1.getX(), player1.getY());
		//at.scale(.5, .5);
		g2d.drawImage(player1.getImage(), at, this);
		if(player1.isBoosted())
			g2d.drawImage(player1.getBoostImage(),at,this);}
		
		
		else if(numPlayer == 2){
			Graphics2D g2d = (Graphics2D)g;
			AffineTransform at = new AffineTransform();
			at.translate(player2.getX(), player2.getY());
			//at.scale(.5, .5);
			g2d.drawImage(player2.getImage(), at, this);
			if(player2.isBoosted())
				g2d.drawImage(player2.getBoostImage(),at,this);}
	}
	
	public void drawAllProjectiles(Graphics g){
		for(int k = 0;k<projectiles.size();k++)
			drawProjectile(g,projectiles.get(k));
	}
	
	public void drawProjectile(Graphics g,Projectile p){
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform at = new AffineTransform();
		at.translate(p.getX(),p.getY());
		g2d.drawImage(p.getImage(),at,this);
	}
	
	public void actionPerformed(ActionEvent e){
		if(score/level==20){
			levelUp();
		}
		if(!gameOver){
			if(System.currentTimeMillis()>=startTime+1000){
				fps = fpsCounter;
				fpsCounter = 0;
				startTime = System.currentTimeMillis();
			}
			player1.move();
			if(twoPlayer)
				player2.move();
			moveProjectiles();
			if(isCollisions)
				checkCollisions();
			repaint();}
		else{
			timer.stop();
			highScore = score;
			repaint();
		}
			
	}
	
	public void restartGame(){
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		initKeyBindings();
		
		score = 0;
		
		smiley = new Smiley();
		player1 = new Player(1);
		if(twoPlayer)
			player2=new Player(2);
		projectiles = new ArrayList<Projectile>();
		projectiles.add(new Projectile());
		maxProjectiles = 10;
		level = 1;
		gameOver=false;
		
		timer = new Timer(5,this);
		timer.start();
	}
	
	public void checkCollisions(){
			Rectangle playerBounds = player1.getBounds();
			Rectangle projectileBounds;
			for(int k=0;k<projectiles.size();k++){
				projectileBounds = projectiles.get(k).getBounds();
			//	System.out.println("Player Bounds "+playerBounds.toString());
			//	System.out.println("Projectile Bounds "+projectileBounds.toString());
				if(projectileBounds.intersects(playerBounds)){
				Rectangle boundIntersection = playerBounds.intersection(projectileBounds);
				if((boundIntersection.getHeight()*boundIntersection.getWidth())>250){
					System.out.println("Player Bounds "+playerBounds.toString());
					System.out.println("Projectile Bounds "+projectileBounds.toString());
					if(projectiles.get(k).isSpecial()&&!player1.isBoosted()){
						player1.boostSpeed();
						projectiles.remove(k);}
					else if(!projectiles.get(k).isSpecial()){
						gameOver=true;}}}
			}
			
			if(twoPlayer){
				playerBounds = player2.getBounds();
				for(int k=0;k<projectiles.size();k++){
					projectileBounds = projectiles.get(k).getBounds();
				//	System.out.println("Player Bounds "+playerBounds.toString());
				//	System.out.println("Projectile Bounds "+projectileBounds.toString());
					if(projectileBounds.intersects(playerBounds)){
					Rectangle boundIntersection = playerBounds.intersection(projectileBounds);
					if((boundIntersection.getHeight()*boundIntersection.getWidth())>250){
						System.out.println("Player Bounds "+playerBounds.toString());
						System.out.println("Projectile Bounds "+projectileBounds.toString());
						if(projectiles.get(k).isSpecial()&&!player2.isBoosted()){
							player2.boostSpeed();
							projectiles.remove(k);}
						else if(!projectiles.get(k).isSpecial()){
							projectiles.remove(k);
							gameOver=true;}}}
				}
			}
		
	}

	public void moveProjectiles(){
		if(projectiles.size()<maxProjectiles){
			//System.out.println("Needs "+(maxProjectiles-projectiles.size())+" more projectiles");
			if((projectiles.get(projectiles.size()-1)).getY()>700/maxProjectiles){
				//System.out.println("Adding a projectile.");
				projectiles.add(new Projectile());
				//System.out.println("total Projectiles: "+projectiles.size());
			}
			//System.out.println("Last projectiles height "+projectiles.get(projectiles.size()-1).getY());

		}
		for(int k=0;k<projectiles.size();k++){
			if(projectiles.get(k).getY()>700){
				//System.out.println("removing a projectile");
				projectiles.remove(k);
				//System.out.println("total Projectiles: "+projectiles.size());
				score++;}
		}
		for(int k=0;k<projectiles.size();k++){
			projectiles.get(k).move();
		}
		
	}
	
	public void levelUp(){
		//setBackground(backgroundColors[(int)Math.round(Math.random()*(backgroundColors.length-1))]);
		level++;
		maxProjectiles+=2;
		if(level>=10&&level<20)
			phase++;
		
	}
	
	public static int getLevel(){
		return level;
	}

}