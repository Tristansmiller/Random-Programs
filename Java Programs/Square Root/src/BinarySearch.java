/*
 * Tristan Miller
 * CSC 3380
 * The BinarySearch class is an implementation of FormulaImplementation that solves square roots using a Binary Search.
 */
public class BinarySearch implements FormulaImplementation {

	public static void findSolution(Formula n){
		
		//Initial values to start the binary Search with.
		int initialVal = n.getNum();
		double low = 0;
		double high = initialVal;
		double mid = low+high/2;
		double square = mid*mid;
		int step = 1;
		
		//the actual binary search itself, searches for solutions within .0005 accuracy.
		System.out.printf("%s\t%s\t%s\t%s\t%s\t%s%n","Step","Low","High","Mid","Square","Result");
		while(!(square<initialVal+.0005 && square>initialVal-.0005)){
			if(square>initialVal){
				System.out.printf("%d\t%.4f\t%.4f\t%.4f\t%.4f\t -too high%n",step,low,high,mid,square);
				high = mid;
				mid = (low+high)/2;
				square = mid*mid;
			}
			else{
				System.out.printf("%d\t%.4f\t%.4f\t%.4f\t%.4f\t-too low%n",step,low,high,mid,square);
				low = mid;
				mid = (low+high)/2;
				square = mid*mid;
			}
			step++;
		}
		
		//prints the final answer after concluding the binary search.
	    System.out.printf("%d\t%.4f\t%.4f\t%.4f\t%.4f\t-Correct%n%n",step,low,high,mid,square);
	    System.out.printf("sqrt(%d) = %.4f",initialVal,mid);	
	}
	
}
