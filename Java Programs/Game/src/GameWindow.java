
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JFrame;
//this class is the actual window itself, holds the panel which all the graphics occur in.
public class GameWindow extends JFrame{
	public GameWindow(){
		setSize(1000,1000);
		add(new World(getWidth(),getHeight()));		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Work in Progress Game");
		setResizable(true);
		setVisible(true);
		

	}
	//this is the panel which contains the environment, interface, and does all the graphics.
	public class World extends JPanel implements ActionListener{
		private Environment gameWorld;
		private Interface ui;
		private Timer refresher;
		private int width;
		private int height;
		private int cameraX;
		private int cameraY;
		private int minViewX;
		private int minViewY;
		private int maxViewX;
		private int maxViewY;
		private boolean gameOver;
		public World(){
			setFocusable(true);
			setBackground(Color.BLACK);
			setDoubleBuffered(true);
			gameWorld = new Environment();
			cameraX = gameWorld.getPlayer().getXPos();
			cameraY = gameWorld.getPlayer().getYPos();
			width = 1000;
			height = 1000;
			minViewX = cameraX-width/2;
			minViewY = cameraY-height/2;
			maxViewX = cameraX+width/2;
			maxViewY = cameraY+height/2;
			ui = new Interface();
			initInterface();
			gameOver = false;
			refresher = new Timer(5,this);
			refresher.start();
			
		}
		
		public World(int w, int h){
			setFocusable(true);
			setBackground(Color.WHITE);
			setDoubleBuffered(true);
			gameWorld = new Environment();
			cameraX = gameWorld.getPlayer().getXPos();
			cameraY = gameWorld.getPlayer().getYPos();
			width = w;
			height = h;
			minViewX = cameraX-width/2;
			minViewY = cameraY-height/2;
			maxViewX = cameraX+width/2;
			maxViewY = cameraY+height/2;
			ui = new Interface();
			initInterface();
			refresher = new Timer(5,this);
			refresher.start();
		}
		
		//this method is used to find which part of the environment needs to be shown in the panel.
		//the view is always centered on the player.
		public void adjustCameraView(){
			if(width!=getWidth())
				width = getWidth();
			if(height!=getHeight())
				height = getHeight();
			cameraX = gameWorld.getPlayer().getXPos();
			cameraY = gameWorld.getPlayer().getYPos();
			minViewX = cameraX-width/2;
			minViewY = cameraY-height/2;
			maxViewX = cameraX+width/2;
			maxViewY = cameraY+height/2;
			gameWorld.getPlayer().setOffset(new Point(width/2,height/2),getLocationOnScreen(),width,height);
			
				
		}
		
		//this is the general method that "paints" all the graphics.
		public void paint(Graphics g){
			super.paint(g);
			g.setColor(Color.BLACK);
			g.fillRect(-minViewX,-minViewY,4000,4000);//paints a black rectangle the size of the environment so that you can see when you've reached the edge.
			drawPlayer(g);
			drawProjectiles(gameWorld.getPlayer().getProjectiles(),g);
			for(int k = 0;k<gameWorld.getEnemies().size();k++){
				if(gameWorld.getEnemies().get(k) instanceof Gunner)
					drawProjectiles(gameWorld.getEnemies().get(k).getShots(),g);
			}
			drawEnemies(g);
			g.setColor(Color.WHITE);
			g.drawString("Score: "+gameWorld.getScore(),500,50);
			if(gameOver){
				drawGameOver(g);
			}
				
			//drawBounds(g);
		}
		//this method is only called whenever a collisionException has occurred.
		public void drawGameOver(Graphics g){
			Graphics2D g2d = (Graphics2D)g;
			AffineTransform at = new AffineTransform();
			ImageIcon ii = new ImageIcon(this.getClass().getResource("gameOverImage.png"));
			Image gameOverImage = ii.getImage();
			at.translate(width/2-(gameOverImage.getWidth(this)/2),height/2-(gameOverImage.getHeight(this)/2));
			g2d.drawImage(gameOverImage,at,this);
		}
		
		//The drawPlayer method and drawEnemies methods currently use Java graphics to draw a rectangle and 
		//fill in a specific color, rather than loading an image.
		public void drawPlayer(Graphics g){
		/*	Graphics2D g2D = (Graphics2D)g;
			AffineTransform at = new AffineTransform();
			at.translate(player1.getXPos(),player1.getYPos());
			g2D.draw(player1.getPlayerImage(), at, this); */ //to be used later when i have an 
															// actual player image.
			g.setColor(Color.RED);
			g.fillRect(gameWorld.getPlayer().getXPos()-minViewX, gameWorld.getPlayer().getYPos()-minViewY, (int)(gameWorld.getPlayer().getPlayerImage().getWidth())
										, (int)(gameWorld.getPlayer().getPlayerImage().getHeight()));
		}
		
		public void drawEnemies(Graphics g){
			g.setColor(Color.BLUE);
			for(int k = 0;k<gameWorld.getEnemies().size();k++){
				if(gameWorld.getEnemies().get(k) instanceof Gunner)
					g.setColor(Color.MAGENTA);
				else
					g.setColor(Color.BLUE);
				g.fillRect((int)gameWorld.getEnemies().get(k).getXPos()-minViewX, (int)gameWorld.getEnemies().get(k).getYPos()-minViewY, 20, 40);
			}
		}
		//not in use, i use this for debugging if problems with collisions are occurring.
		public void drawBounds(Graphics g){
			g.setColor(Color.WHITE);
			Rectangle currentBounds;
			currentBounds = gameWorld.getPlayer().getBounds();
			g.drawRect((int)currentBounds.getX(),(int)currentBounds.getY(),(int)currentBounds.getWidth(),(int)currentBounds.getHeight());
			for(int k =0;k<gameWorld.getPlayer().getProjectiles().size();k++){
				currentBounds = gameWorld.getPlayer().getProjectiles().get(k).getBounds();
				g.drawRect((int)currentBounds.getX(),(int)currentBounds.getY(),(int)currentBounds.getWidth(),(int)currentBounds.getHeight());
			}
			for(int k =0;k<gameWorld.getEnemies().size();k++){
				currentBounds = gameWorld.getEnemies().get(k).getBounds();
				g.drawRect((int)currentBounds.getX(),(int)currentBounds.getY(),(int)currentBounds.getWidth(),(int)currentBounds.getHeight());
			}
		}
		
		//the drawProjectile method is different from the other draw methods in that I actually am loading
		//images in the location of the projectile instead of drawing rectangle within java.
		public void drawProjectiles(Projectile[] p,Graphics g){
			for(int k =0;k<p.length;k++){
				Projectile currentShot = p[k];
				Graphics2D g2d = (Graphics2D)g;
				AffineTransform at = new AffineTransform();
				
				at.translate(currentShot.getXPos()-minViewX,currentShot.getYPos()-minViewY);
				at.rotate(currentShot.getMovementAngle());
				
				g2d.drawImage(currentShot.getImage(),at,this);
			}
				
		}
		public void drawProjectiles(ArrayList<Projectile> p,Graphics g)
		{
			for(int k=0;k<p.size();k++){
				Projectile currentShot = p.get(k);
				Graphics2D g2d = (Graphics2D)g;
				AffineTransform at = new AffineTransform();
				at.translate(currentShot.getXPos()-minViewX, currentShot.getYPos()-minViewY);
				at.rotate(currentShot.getMovementAngle());
				g2d.drawImage(currentShot.getImage(),at,this);
				
			}
		}
		
		//this sets the keys in use by this panel and the actions those keys are supposed to execute.
		public void initInterface(){
			setActionMap(ui.getActionMap());
			setInputMap(0,ui.getInputMap());
		}
		
		//this is called every time the Timer object refreshes, basically runs the game. calls the environment
		//process actions and then repaints to represent the movements that occurred.
		public void actionPerformed(ActionEvent e){
			if(!gameOver)
				try{
					gameWorld.processActions();
					adjustCameraView();}
				catch(CollisionException ex){
					gameOver = true;
				}
			repaint();
			//player1.move();
			
		}
		
		public void resetGame(){
			gameWorld.resetEnvironment();
			gameOver = false;
		}
		
		//this class is used to give certain keys specific actions in relation to the program
		//You can use KeyListeners and KeyHandlers, but I use Key Bindings, so that the keys just incite
		//various boolean state changes, rather than calling actual methods themselves.
		public class Interface {
			private ActionMap am;
			private InputMap im;
			
			public Interface(){
				im = new InputMap();
				am = new ActionMap();
				initKeyBindings();
			}
			
			public void initKeyBindings(){
				initInputMap();
				initActionMap();
			}
			
			public void initInputMap(){
				im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0,false),"space-pressed");
				im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0,true),"space-released");
				
				im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false),"enter-pressed");
				
				im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0,false),"w-pressed");
				im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0,true),"w-released");
				
				im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0,false),"a-pressed");
				im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0,true),"a-released");
				
				im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0,false),"s-pressed");
				im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0,true),"s-released");

				im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0,false),"d-pressed");
				im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0,true),"d-released");
				
			}
			
			public void initActionMap(){
				am.put("w-pressed", 
						new AbstractAction(){
							@Override
							public void actionPerformed(ActionEvent e){	
								gameWorld.getPlayer().setMovement("up");
							}
						});
				am.put("w-released", 
						new AbstractAction(){
							@Override
							public void actionPerformed(ActionEvent e){	
								gameWorld.getPlayer().setMovement("up-stop");
							}
						});
				am.put("s-pressed", 
						new AbstractAction(){
							@Override
							public void actionPerformed(ActionEvent e){	
								gameWorld.getPlayer().setMovement("down");
							}
						});
				am.put("s-released", 
						new AbstractAction(){
							@Override
							public void actionPerformed(ActionEvent e){	
								gameWorld.getPlayer().setMovement("down-stop");
							}
						});
				am.put("d-pressed", 
						new AbstractAction(){
							@Override
							public void actionPerformed(ActionEvent e){	
								gameWorld.getPlayer().setMovement("right");
							}
						});
				am.put("d-released", 
						new AbstractAction(){
							@Override
							public void actionPerformed(ActionEvent e){	
								gameWorld.getPlayer().setMovement("right-stop");
							}
						});
				am.put("a-pressed", 
						new AbstractAction(){
							@Override
							public void actionPerformed(ActionEvent e){	
								gameWorld.getPlayer().setMovement("left");
							}
						});
				am.put("a-released", 
						new AbstractAction(){
							@Override
							public void actionPerformed(ActionEvent e){	
								gameWorld.getPlayer().setMovement("left-stop");
							}
						});
				
				am.put("space-pressed", 
						new AbstractAction(){
							@Override
							public void actionPerformed(ActionEvent e){	
								gameWorld.getPlayer().setFiring(true);
							}
						});
				am.put("space-released", 
						new AbstractAction(){
							@Override
							public void actionPerformed(ActionEvent e){	
								gameWorld.getPlayer().setFiring(false);
							}
						});
				am.put("enter-pressed",
							new AbstractAction(){
							@Override
							public void actionPerformed(ActionEvent e){
								resetGame();
							}
						});
				
			}
			
			public InputMap getInputMap(){
				return im;
			}
			public ActionMap getActionMap(){
				return am;
			}
		}
	}

}