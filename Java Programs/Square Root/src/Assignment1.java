/*
 * Tristan Miller
 * CSC 3380
 * The Assignment1 class is the main method that utilizes Formula, the subclass Squareroot, the FormulaImplementation interface
 * and the two classes that implement formula implementation: BinarySearch, and Newtons.
 */
import java.util.Scanner;

public class Assignment1 {

	public static void main(String[] args) {
		
		//Prompting user and taking input
		System.out.println("Input the square root you would like to find (format it as sqrt(num):  ");
		Scanner input = new Scanner(System.in);
		String root = input.next();
		System.out.println("\nEnter 0 for Binary Search solution or 1 for Newtons method solution:  ");
		String solveType = input.next();
		
		//creating equation and deciding which method to use to solve
		SquareRoot equation = new SquareRoot(root);
		if(Integer.parseInt(solveType)==0)
			BinarySearch.findSolution(equation);
		if(Integer.parseInt(solveType)==1)
			Newtons.findSolution(equation);
		
	}

}
