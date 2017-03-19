	package gui;
	import java.awt.Image;
	import java.awt.event.KeyEvent;
	import javax.swing.ImageIcon;
	import java.awt.Rectangle;

	public class Player {
		
		private String[] playerImagesRF = {"moving1.png",
										"moving2.png",
										"moving3.png",
										"moving4.png",
										"moving5.png",
										"moving6.png",
										"moving7.png",
										"moving8.png","standing.png"};
		private String[] playerImagesLF = {"moving1L.png",
										"moving2L.png",
										"moving3L.png",
										"moving4L.png",
										"moving5L.png",
										"moving6L.png",
										"moving7L.png",
										"moving8L.png","standingL.png"};
		private int imageState;
		private int numStates;
		private Image image;
		private int refreshed;
		private boolean stillStanding;
		private Image boostImage;
		
		private int x;
		private int y;
		private int speed;
		private long boostStart;
		private long boostEnd;
		private boolean boosting;

		private int rotation;
		private int height;
		private int width;
		private Rectangle bounds;
		private int[][] boundList;
		
		private boolean facingRight;
		private boolean facingLeft;
		private boolean movingUp;
		private boolean movingDown;
		private boolean movingLeft;
		private boolean movingRight;
		private boolean standing;
		
		public Player(int numPlayer){
			imageState = 0;
			numStates =playerImagesRF.length;
			ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImagesRF[8]));
			image = ii.getImage();
			ImageIcon iii = new ImageIcon(this.getClass().getResource("powerUp.png"));
			boostImage = iii.getImage();
			
			if(numPlayer == 1)
				x=350;
			if(numPlayer==2)
				x=250;
			y=700-image.getHeight(null)-30;
			System.out.println(image.getHeight(null));
			rotation = 0;
			height = image.getHeight(null);
			width = image.getWidth(null);
			initBounds();
			bounds = new Rectangle(x,y,width,height);
			facingRight = true;
			facingLeft = false;
			movingUp= false;
			movingDown= false;
			movingRight= false;
			movingLeft= false;
			speed = 1;
			stillStanding=false;
			
			
		}
		public void initBounds(){
			boundList = new int[9][2];
			boundList[0][0]=66;
			boundList[0][1]=140;
			
			boundList[1][0]=40;
			boundList[1][1]=136;
			
			boundList[2][0]=71;
			boundList[2][1]=126;
			
			boundList[3][0]=70;
			boundList[3][1]=132;
			
			boundList[4][0]=69;
			boundList[4][1]=136;
			
			boundList[5][0]=41;
			boundList[5][1]=129;
			
			boundList[6][0]=72;
			boundList[6][1]=123;
			
			boundList[7][0]=70;
			boundList[7][1]=133;
			
			boundList[8][0]=39;
			boundList[8][1]=130;
		}
		
		public void move(){
			reduceSpeed();
			if(movingUp&!movingDown){
				y-=speed;
				refreshBounds();}
			if(movingDown&!movingUp){
				y+=speed;
				refreshBounds();}
			if(movingRight&!movingLeft){
				x+=speed;
				if(refreshed%10==0 || facingLeft){
					changeImageState(false);
				}
				stillStanding=false;
				standing =false;
				refreshBounds();
			}
				
			if(movingLeft&!movingRight){
				x-=speed;
				if(refreshed%10==0 || facingRight){
					changeImageState(true);
				}
				stillStanding=false;
				standing =false;
				refreshBounds();
			}
			if(facingLeft&&standing && !stillStanding){
				changeImageState(true);
				stillStanding=true;
				refreshBounds();}
			if(facingRight&&standing&&!stillStanding){
				changeImageState(false);
				stillStanding=true;
				refreshBounds();}
			//refreshBounds();
			refreshed++;
		}
		public void refreshBounds(){
			int w = boundList[imageState][0];
			int h = boundList[imageState][1];
			int xReal = x+((width-w)/2);
			int yReal = y+(height-h);
			
			bounds = new Rectangle(xReal,yReal,w,h);
		}
		
		public void changeImageState(boolean left){
			if(left&&standing==false){
				imageState--;
				if(imageState<0)
					imageState=7;
				System.out.println(playerImagesLF[imageState]);
				ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImagesLF[imageState]));
				image = ii.getImage();
				}
			else if (standing==false){
				imageState--;
				if(imageState<0)
					imageState=7;
				System.out.println(playerImagesRF[imageState]);
				ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImagesRF[imageState]));
				image = ii.getImage();
				}
			else if(left){
				imageState = 8;
				ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImagesLF[imageState]));
				image = ii.getImage();
			}
			else{
				imageState = 8;
				ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImagesRF[imageState]));
				image = ii.getImage();
			}
			
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
		public int getRotation(){
			return rotation;
		}
		public int getHeight(){
			return height;
		}
		public int getWidth(){
			return width;
		}
		public Rectangle getBounds(){
			return bounds;
		}
		public Image getBoostImage(){
			return boostImage;
		}
		public boolean isBoosted(){
			return boosting;
		}
		public void boostSpeed(){
			speed++;
			boostStart = System.currentTimeMillis();
			boostEnd = boostStart+5000;
			boosting=true;
		}
		public void reduceSpeed(){
			if(boosting&&System.currentTimeMillis()>=boostEnd){
				speed--;
				boosting=false;}
		}
		
		public void setMovement(String stateChange){
			switch(stateChange){
			case "up" : movingUp=true;standing=false;break;
			case "up-stop": movingUp=false;
							standing=true;break;
			
							
			case "down": movingDown=true;standing=false;break;
			case "down-stop": movingDown=false;
							  standing=true;break;
			
							  
			case "right" : movingRight=true;
						   if(facingLeft){
						   facingRight = true;
						   facingLeft =false;}
						   standing=false;break;
						   
			case "right-stop" : movingRight=false;
								standing=true;break;
			
								
			case "left" : movingLeft=true;
						  if(facingRight){
							  facingLeft=true;
						  	  facingRight=false;}
						  standing=false;break;
						  	  
			case "left-stop": movingLeft=false;
							  standing=true;break;
			
			default: break;
			}
		}
		


	}

