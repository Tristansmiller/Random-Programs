/*
 * Tristan Miller
 * CSC 3380
 * The Square root class is a subclass of formula with added functionality that analyzes the input string to
 * find the number inside that is to be rooted.
 */
public class SquareRoot extends Formula {

	//Number inside the square root
	private int numberToBeRooted;
	
	public SquareRoot(){
		super();
	}
	
	//Takes an input string of sqrt(num) format and extracts the number, as well as saving the entire string.
	public SquareRoot(String input){
		super(input);
		String innerString = equation.substring(equation.indexOf("(")+1,equation.indexOf(")"));
		numberToBeRooted = Integer.parseInt(innerString);
	}
	
	public int getNum(){ return numberToBeRooted;}
	
	
}
