package prog4;
/*
 * Tristan Miller
 * CSC 3380
 * cs338019
 * Assignment 4
 * 
 * This Class represents a cupcake with frosting, and is a decorator class.
 */
public class Frosting extends CupcakeDecorator {
	public int frosting;
	
	public Frosting(Cupcake dCup, int fType){
		super(dCup);
		frosting = fType;
	}
	
	public double price(){
		return super.price();
	}
	
	public int getFrosting(){
		return frosting;
	}
	
	public String toString(){
		switch(frosting){
		case 1: return super.toString()+" Cream Cheese ";
		case 2: return super.toString()+" Chocolate Fudge ";
		case 3: return super.toString()+" Butter Cream ";
		default: return "";
		}
	}

}
