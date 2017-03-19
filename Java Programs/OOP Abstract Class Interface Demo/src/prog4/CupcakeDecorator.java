package prog4;
/*
 * Tristan Miller
 * CSC 3380
 * cs338019
 * Assignment 4
 * 
 * This is the abstract decorator class that holds the base Cupcake object that the concrete decorator classes adds to.
 */
public abstract class CupcakeDecorator implements Cupcake {

	private Cupcake decoratedCupcake;
	
	public CupcakeDecorator(Cupcake dCup){
		decoratedCupcake = dCup;
	}
	
	public double price(){
		return decoratedCupcake.price();
	}
	
	public int getBatter(){
		return decoratedCupcake.getBatter();
	}
	
	public String toString(){
		return decoratedCupcake.toString();
	}
	
}
