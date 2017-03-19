/*
 * Tristan Miller
 * CSC 3380
 * The Formula class serves as a module to contain a String that represents a formula
 */
public class Formula {

	//the string representing an equation
	protected String equation;
	
	private int num;
	
	public Formula(){
		equation = "";
	}
	
	public Formula(String input){
		equation = input;
	}
	
	public int getNum(){ return num;}
	
	
}
