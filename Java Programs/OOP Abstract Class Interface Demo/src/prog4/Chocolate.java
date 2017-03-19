package prog4;
/*
 * Tristan Miller
 * CSC 3380
 * cs338019
 * Assignment 4
 * 
 * This class implements the cupcake interface and serves as a container for the base price and batter type.
 */
public class Chocolate implements Cupcake {
	private int batter;
	
	public Chocolate(){
		batter = 2;
	}
	
	public double price(){
		return 1.25;
	}
	
	public int getBatter(){
		return batter;
	}
	
	public String toString(){
		return "Chocolate ";
	}


}
