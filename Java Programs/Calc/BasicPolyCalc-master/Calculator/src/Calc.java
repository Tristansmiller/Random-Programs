import java.text.DecimalFormat;
import java.util.regex.*;
import javax.swing.JOptionPane;

public class Calc {
	private static int indexStart;
	private static int indexEnd;
	private static int endBoundary;
	
	public static void processInput(String input){
		if(isPolynomial(input)){
			double value = Double.parseDouble(JOptionPane.showInputDialog("What is the value of X?"));
			while(containsParentheses(input)){
				String inner = insideParentheses(input);
				while(containsParentheses(inner)) inner = insideParentheses(inner);
				String innerString = ""+simplifyPolynomial(new Polynomial(inner),value);
				input = replaceParentheses(input,innerString);
				System.out.println(input);
				}
			System.out.println(computePolynomial(value,new Polynomial(input)));
		}
		else{
			double value = 1;
			input = ""+convertToPolynomial(input);
		   	while(containsParentheses(input)){
			   	String inner = insideParentheses(input);
				String innerString = ""+simplifyPolynomial(new Polynomial(inner),value);
				input = replaceParentheses(input,innerString);
				System.out.println(input);
		   		}
		System.out.println(computePolynomial(value,new Polynomial(input)));
		}
	}
	
	public static boolean containsParentheses(String input){
		Pattern p = Pattern.compile("(.*)([(].+[)])(.*)");
		Matcher m = p.matcher(input);
		return m.matches();
	}
	public static String outsideParentheses(String input){
		String[] newString = input.split("\\(.*\\)");
		System.out.println(newString.length);
		String output = "";
		for(int k=0;k<newString.length;k++){
			System.out.println(newString[k]);
			output +=newString[k];}
		return output;
	}
	public static String replaceParentheses(String input, String replacement){
		String[] newString = new String[2];
		newString[0]=input.substring(0,indexStart-2);
		newString[1]=input.substring(indexEnd+1,input.length()-1);
		String output = newString[0]+replacement+newString[1];
		return output;
	}
	
	public static String insideParentheses(String input){
			endBoundary = input.length();
			indexStart = input.indexOf("(")+1;
			System.out.println("Index start is "+indexStart);
			for(int k =indexStart;k<=input.length()-1;){
					if(k<endBoundary&&input.indexOf(")",k)!=-1){
						indexEnd = input.indexOf(")",k);
						System.out.println("Index end is "+indexEnd);
						k=indexEnd+1;}
			}
			endBoundary = indexEnd;	
			System.out.println("Trimming "+input);
			System.out.println("going from index "+indexStart+" to "+indexEnd);
			System.out.println(input.substring(indexStart,indexEnd));
			return input.substring(indexStart, indexEnd);
	}
	
	
	public static PolyNode multiply(PolyNode node1, PolyNode node2) {
		double productCoeff = node1.getCoeff() * node2.getCoeff();
		double productDegree = node1.getDegree() + node2.getDegree();
		PolyNode product = new PolyNode(productDegree, productCoeff,
				node2.getNext());
		return product;
	}

	public static PolyNode divide(PolyNode node1, PolyNode node2) {
		double quotientCoeff = node1.getCoeff() / node2.getCoeff();
		double quotientDegree = node1.getDegree() - node2.getDegree();
		PolyNode quotient = new PolyNode(quotientDegree, quotientCoeff,
				node2.getNext());
		return quotient;
	}
	
	public static boolean isPolynomial(String input){
		return input.contains("X");
	}

	public static String makeTable(Polynomial P) {// makes the table by taking a
												// domain from the user, and
												// calls computePolynomial for
												// each value in the domain.
												// domain is + and -
		String table = "     X     |     Y     \n" + "___________|___________";
		double start = Double.parseDouble(JOptionPane
				.showInputDialog("Enter the start of the table."));
		double end = Double.parseDouble(JOptionPane
				.showInputDialog("Enter the end of the table."));
		double increment = Double.parseDouble(JOptionPane
				.showInputDialog("Enter the table increment"));
		DecimalFormat df = new DecimalFormat("0.0000");
		while (start <= end){
			table = table + "\n\t" + start + "\t|\t"
					+ computePolynomial(start, P) + "\t";
			start = Double.parseDouble(df.format(start+increment));}
		return table;
	}
	
	public static void makeGraph(Polynomial P){
		double start = Double.parseDouble(JOptionPane.showInputDialog("Enter start of domain"));
		double end = Double.parseDouble(JOptionPane.showInputDialog("Enter end of domain"));
		double[] yPoints = new double[(int)((end-start)/.01)+1];
		double[] xPoints = new double[(int)((end-start)/.01)+1];
		DecimalFormat df = new DecimalFormat("0.00");
		double domain = end-start;
		double range;
		int index = 0;
		while(start<=end){
			xPoints[index] = start;
			yPoints[index] = computePolynomial(start,P);
			index++;
			start = Double.parseDouble(df.format(start+.01));;
		}
		double highY=0;
		double lowY=0;
		for(int k = 0;k<yPoints.length;k++){
			if(yPoints[k]>highY)
				highY = yPoints[k];
			if(yPoints[k]<lowY)
				lowY = yPoints[k];
		}
		range = highY-lowY;
		System.out.println(range);
		new Graph(xPoints,yPoints, domain, range);
	}

	public static int[] findOrderOfOperations(char[] operators,int[] orderOfOperations) {// Find the order of operation by looking for the operators and then putting the index of the operator into an array
				int maxSteps = operators.length;
				orderOfOperations = new int[maxSteps];
					// System.out.print("PRINTING OPERATORS : ");
					// for(int k =0;k<maxSteps;k++){System.out.print("["+operators[k]+"]");}
					for (int k = 0; k < maxSteps; k++) {
						int operatorIndex = 0;
						while (operatorIndex < maxSteps) {
							// System.out.println("LOOKING FOR MULTIPLICATION CHARACTER AND DIVISION");
							if (operators[operatorIndex] == '*'
							|| operators[operatorIndex] == '/')
							orderOfOperations[k++] = operatorIndex;
							operatorIndex++;
						}
						operatorIndex = 0;
						while (operatorIndex < maxSteps) {
						// System.out.println("LOOKING FOR ADDITION CHARACTER AND SUBTRACTION CHARACTER");
							if (operators[operatorIndex] == '+'
							|| operators[operatorIndex] == '-')
							orderOfOperations[k++] = operatorIndex;
							operatorIndex++;
						}
					}
						
				for(int k =0;k<maxSteps;k++)
				System.out.println("Step "+k+": use operator "+orderOfOperations[k]+" ("+operators[orderOfOperations[k]]+")");
				System.out.println("\n\n\n");
				return orderOfOperations;		
				}
		
	
	public static double computeSimpleExpression(String input){
		return computePolynomial(1,convertToPolynomial(input));
	}
	public static Polynomial convertToPolynomial(String S){
		String poly = "";
		String[] numStrings = S.split("\\+|(?<!(\\G|E))-|/|\\*");
		char[] operators = new char[numStrings.length - 1];
		int placeMarker = -1;
		for (int k = 0; k < numStrings.length; k++) {
			if (k != numStrings.length - 1) {
				// System.out.println("Place Marker is at index "+placeMarker+" adding the length of "+terms[k]);
				placeMarker += numStrings[k].length() + 1;
				operators[k] = S.charAt(placeMarker);
			}
		}
		for(int k=0;k<numStrings.length;k++){
			if(numStrings[k].contains("^")){
				String[] temp = numStrings[k].split("\\^");
				double temp2 = Double.parseDouble(temp[0]);
				temp2=Math.pow(temp2, Double.parseDouble(temp[1]));
				numStrings[k]=""+temp2+"X^0";}
			else
				numStrings[k]=numStrings[k]+"X^0";
		}
		for(int k=0;k<numStrings.length;k++){
			if(k<numStrings.length-1)
				poly+=numStrings[k]+operators[k];
			else
				poly+=numStrings[k];		
		}
		return new Polynomial(poly);
	}
	public static double[] shiftArray(double[] array, int skip){
		double[] newArray = new double[array.length-1];
		int arrayPointer = 0;
		for(int k=0;k<newArray.length;k++){
			if(k==skip)arrayPointer++;
			newArray[k]=array[arrayPointer];
			arrayPointer++;
		}
		return newArray;
	}
	
	public static PolyNode simplifyPolynomial(Polynomial eq, double value){
		eq.setTrueEquation();
		Polynomial equation = eq.getTrueEquation();
		double x = value;
		PolyNode p = equation.getFirst();
		double result = 0;
		int steps = 0;
		int maxSteps = equation.getOperators().length;
		while (steps < maxSteps) {
			int nextOperation = equation.getOrderOfOperations()[0];
			switch (equation.getOperators()[nextOperation]) {
			case '+':
				p = equation.getNode(nextOperation);
				double sum = p.computePolyNode(x);
				System.out.println("Adding " + p + " and " + p.getNext());
				p = p.getNext();
				sum += p.computePolyNode(x);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, sum,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1, sum / x,
							p.getNext());
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			case '/':
				p = equation.getNode(nextOperation);
				double quotient = p.computePolyNode(x);
				System.out.println("Dividing " + p + " and " + p.getNext());
				p = p.getNext();
				quotient /= p.computePolyNode(x);
				if (p.computePolyNode(x) == 0)
					return new PolyNode(0,0,null);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, quotient,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1, quotient / x,
							p.getNext());
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			case '*':
				p = equation.getNode(nextOperation);
				double product = p.computePolyNode(x);// System.out.println("Multiplying "+p+" and "+p.getNext());
				p = p.getNext();
				product *= p.computePolyNode(x);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, product,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1, product / x,
							p.getNext());
				System.out.println(equation.getNode(nextOperation));
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			case '-':
				p = equation.getNode(nextOperation);
				double difference = p.computePolyNode(x);
				System.out.println("Subtracting " + p + " and " + p.getNext());
				p = p.getNext();
				difference -= p.computePolyNode(x);
				System.out.println("the difference is " + difference);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, difference,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1,
							difference / x, p.getNext());
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			default:
				break;

			}
			steps++;
		}
		return equation.getFirst();
	}
	public static PolyNode simplifyPolynomial(Polynomial eq){
		eq.setTrueEquation();
		Polynomial equation = eq.getTrueEquation();
		double x = Double.parseDouble(JOptionPane
				.showInputDialog("What is the x value?"));
		PolyNode p = equation.getFirst();
		double result = 0;
		int steps = 0;
		int maxSteps = equation.getOperators().length;
		while (steps < maxSteps) {
			int nextOperation = equation.getOrderOfOperations()[0];
			switch (equation.getOperators()[nextOperation]) {
			case '+':
				p = equation.getNode(nextOperation);
				double sum = p.computePolyNode(x);
				System.out.println("Adding " + p + " and " + p.getNext());
				p = p.getNext();
				sum += p.computePolyNode(x);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, sum,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1, sum / x,
							p.getNext());
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			case '/':
				p = equation.getNode(nextOperation);
				double quotient = p.computePolyNode(x);
				System.out.println("Dividing " + p + " and " + p.getNext());
				p = p.getNext();
				quotient /= p.computePolyNode(x);
				if (p.computePolyNode(x) == 0)
					return new PolyNode(0,0,null);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, quotient,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1, quotient / x,
							p.getNext());
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			case '*':
				p = equation.getNode(nextOperation);
				double product = p.computePolyNode(x);// System.out.println("Multiplying "+p+" and "+p.getNext());
				p = p.getNext();
				product *= p.computePolyNode(x);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, product,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1, product / x,
							p.getNext());
				System.out.println(equation.getNode(nextOperation));
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			case '-':
				p = equation.getNode(nextOperation);
				double difference = p.computePolyNode(x);
				System.out.println("Subtracting " + p + " and " + p.getNext());
				p = p.getNext();
				difference -= p.computePolyNode(x);
				System.out.println("the difference is " + difference);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, difference,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1,
							difference / x, p.getNext());
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			default:
				break;

			}
			steps++;
		}
		return equation.getFirst();
	}
	public static double computePolynomial(Polynomial eq) {// calculates what
															// the polynomial
															// equals when the
															// value is x, by
															// computing each
															// term individually
															// and then adding
															// them all
		eq.setTrueEquation();
		Polynomial equation = eq.getTrueEquation();
		double x = Double.parseDouble(JOptionPane
				.showInputDialog("What is the x value?"));
		PolyNode p = equation.getFirst();
		double result = 0;
		int steps = 0;
		int maxSteps = equation.getOperators().length;
		while (steps < maxSteps) {
			int nextOperation = equation.getOrderOfOperations()[0];
			switch (equation.getOperators()[nextOperation]) {
			case '+':
				p = equation.getNode(nextOperation);
				double sum = p.computePolyNode(x);
				System.out.println("Adding " + p + " and " + p.getNext());
				p = p.getNext();
				sum += p.computePolyNode(x);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, sum,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1, sum / x,
							p.getNext());
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			case '/':
				p = equation.getNode(nextOperation);
				double quotient = p.computePolyNode(x);
				System.out.println("Dividing " + p + " and " + p.getNext());
				p = p.getNext();
				quotient /= p.computePolyNode(x);
				if (p.computePolyNode(x) == 0)
					return Double.NaN;
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, quotient,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1, quotient / x,
							p.getNext());
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			case '*':
				p = equation.getNode(nextOperation);
				double product = p.computePolyNode(x);// System.out.println("Multiplying "+p+" and "+p.getNext());
				p = p.getNext();
				product *= p.computePolyNode(x);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, product,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1, product / x,
							p.getNext());
				System.out.println(equation.getNode(nextOperation));
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			case '-':
				p = equation.getNode(nextOperation);
				double difference = p.computePolyNode(x);
				System.out.println("Subtracting " + p + " and " + p.getNext());
				p = p.getNext();
				difference -= p.computePolyNode(x);
				System.out.println("the difference is " + difference);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, difference,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1,
							difference / x, p.getNext());
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			default:
				break;

			}
			steps++;
		}
		result = equation.getFirst().computePolyNode(x);
		System.out.println("\n" + equation + "\nf(" + x + ") = " + result
				+ "\n");
		return result;

	}

	public static double computePolynomial(double value, Polynomial eq) {// same
																			// as
																			// other
																			// computePolynomial
																			// method,
																			// but
																			// has
																			// the
																			// value
																			// passed
																			// as
																			// a
																			// parameter
																			// instead
																			// of
																			// user
																			// input
		eq.setTrueEquation();
		Polynomial equation = eq.getTrueEquation();
		double x = value;
		PolyNode p = equation.getFirst();
		double result = 0;
		int steps = 0;
		int maxSteps = equation.getOperators().length;
		while (steps < maxSteps) {
			int nextOperation = equation.getOrderOfOperations()[0];
			switch (equation.getOperators()[nextOperation]) {
			case '+':
				p = equation.getNode(nextOperation);
				double sum = p.computePolyNode(x);
				System.out.println("Adding " + p + " and " + p.getNext());
				p = p.getNext();
				sum += p.computePolyNode(x);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, sum,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1, sum / x,
							p.getNext());
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			case '/':
				p = equation.getNode(nextOperation);
				double quotient = p.computePolyNode(x);
				System.out.println("Dividing " + p + " and " + p.getNext());
				p = p.getNext();
				quotient /= p.computePolyNode(x);
				if (p.computePolyNode(x) == 0)
					return Double.NaN;
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, quotient,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1, quotient / x,
							p.getNext());
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			case '*':
				p = equation.getNode(nextOperation);
				double product = p.computePolyNode(x);
				System.out.println("Multiplying " + p + " and " + p.getNext());
				p = p.getNext();
				product *= p.computePolyNode(x);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, product,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1, product / x,
							p.getNext());
				System.out.println(equation.getNode(nextOperation));
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			case '-':
				p = equation.getNode(nextOperation);
				double difference = p.computePolyNode(x);
				System.out.println("Subtracting " + p + " and " + p.getNext());
				p = p.getNext();
				difference -= p.computePolyNode(x);
				System.out.println("the difference is " + difference);
				if (p.getDegree() == 0 && x == 0)
					equation.getNode(nextOperation).changeNode(0, difference,
							p.getNext());
				else
					equation.getNode(nextOperation).changeNode(1,
							difference / x, p.getNext());
				equation.operatorShift(nextOperation);
				System.out.println(equation.getNode(nextOperation));
				System.out.println(equation.toStringInformal());
				equation = new Polynomial(equation.toStringInformal(),
						equation.getTrueEquation());
				break;

			default:
				break;

			}
			steps++;
		}
		result = equation.getFirst().computePolyNode(x);
		System.out.println("\n" + equation + "\nf(" + x + ") = " + result
				+ "\n");
		return result;

	}
	

}
