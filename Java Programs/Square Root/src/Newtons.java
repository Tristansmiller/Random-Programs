/*
 * Tristan Miller
 * CSC 3380
 * The Newtons class is an implementation of FormulaImplementation that solves square roots using newtons method.
 */
public class Newtons implements FormulaImplementation {

	public static void findSolution(Formula n){
		
		//initial values and parameters for newtons method
		int initialVal = n.getNum();
		double accuracy = .0005;
		double x = 1;
		int step = 1;
		
		//loop that implements newtons method where the next value tried is equal to x-f(x)/f'(x)
		System.out.printf("%s\t%s\t%s\t%s%n","Step","X","F(x)","Results");
		while(Math.abs(x*x-initialVal)>accuracy){
			System.out.printf("%d\t%.4f\t%.4f\t%s%n",step,x,x*x-initialVal,"Not a zero");
			x= x - (x*x-initialVal)/(2*x);
			step++;
		}
		
		//Prints out the correct solution.
		System.out.printf("%d\t%f\t%f\t%s%n%n",step,x,x*x-49,"zero");
		System.out.printf("Sqrt(%d) = %.4f",initialVal,x);
	}
}
