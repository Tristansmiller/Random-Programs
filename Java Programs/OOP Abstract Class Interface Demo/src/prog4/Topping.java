package prog4;
/*
 * Tristan Miller
 * CSC 3380
 * cs338019
 * Assignment 4
 * 
 * This class represents a cupcake with a topping, and is a decorator class.
 */
public class Topping extends CupcakeDecorator {
	public int topping;
	
	public Topping(Cupcake dCup, int tType){
		super(dCup);
		topping = tType;
	}
	
	public double price(){
		return super.price()+toppingPrice();
	}
	
	public double toppingPrice(){
		switch(topping){
			case 1: return .75;
			case 2: return .5;
			case 3: return .35;
			default: return 0;
		}
	}

	public int getTopping(){
		return topping;
	}
	public int getBatter(){
		return super.getBatter();
	}
	
	public String toString(){
		switch(topping){
			case 1: return super.toString()+" Shredded Coconut";
			case 2: return super.toString()+" Chocolate Sprinkles";
			case 3: return super.toString()+" Confetti Candy";
			default: return "";
		}
	}

}
