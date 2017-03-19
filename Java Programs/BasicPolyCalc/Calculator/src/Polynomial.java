import javax.swing.JOptionPane;

public class Polynomial {
	private PolyNode firstTerm; // first node of the Polynomial
	private int numTerms; // Number of terms in Polynomial
	private double degree; // Highest power of polynomial
	private String[] terms; // Array of the terms in String form, is split and
							// trimmed from original string
	private char[] operators; // Array of all the operators in the Polynomial,
								// in original order
	private int[] orderOfOperations; // stores the order that the operators need
										// to be called in, index 0 is first
										// operation, index 1 is second...
	private Polynomial trueEquation;
	private String rawString;

	public Polynomial(int num, double d) {
		numTerms = num;
		degree = d;
		firstTerm = new PolyNode(degree, null);
		createPolynomial(firstTerm);
	}

	public Polynomial() {
		String expression = JOptionPane
				.showInputDialog("What is the polynomial? (use X as the variable, and  ^ for powers)");
		rawString = expression;
		expression.replaceAll(" ", "");expression.replaceAll("<","");expression.toUpperCase();//cleaning input up
		numTerms = expression.split("\\+|(?<!(\\G|E))-|/|\\*").length;

		terms = new String[numTerms];
		terms = expression.split("\\+|(?<!(\\G|E))-|/|\\*");
		operators = new char[numTerms - 1];
		int placeMarker = -1;
		for (int k = 0; k < terms.length; k++) {
			if (k != terms.length - 1) {
				// System.out.println("Place Marker is at index "+placeMarker+" adding the length of "+terms[k]);
				placeMarker += terms[k].length() + 1;
				operators[k] = expression.charAt(placeMarker);
			}
			terms[k] = terms[k].trim();
		}
		createPolynomial(terms);
		findOrderOfOperations();
	}

	public Polynomial(String expression) {
		rawString = expression;
		expression.toUpperCase();//cleaning input up
		numTerms = expression.split("\\+|(?<!(\\G|E))-|/|\\*").length;
		terms = new String[numTerms];
		terms = expression.split("\\+|(?<!(\\G|E))-|/|\\*");
		operators = new char[numTerms - 1];
		int placeMarker = -1;
		for (int k = 0; k < terms.length; k++) {
			if (k != terms.length - 1) {
				// System.out.println("Place Marker is at index "+placeMarker+" adding the length of "+terms[k]);
				placeMarker += terms[k].length() + 1;
				operators[k] = expression.charAt(placeMarker);
			}
			terms[k] = terms[k].trim();
		}
		// for(int k =0;k<terms.length;k++)System.out.print("["+terms[k]+"]");
		createPolynomial(terms);
		findOrderOfOperations();
	}

	public Polynomial(String expression, Polynomial originalEquation) {
		trueEquation = originalEquation;
		rawString = expression;
		expression.replaceAll(" ", "");expression.replaceAll("<","");expression.toUpperCase();//cleaning input up
		numTerms = expression.split("\\+|(?<!(\\G|E))-|/|\\*").length;

		terms = new String[numTerms];
		terms = expression.split("\\+|(?<!(\\G|E))-|/|\\*");
		operators = new char[numTerms - 1];
		int placeMarker = -1;
		for (int k = 0; k < terms.length; k++) {
			if (k != terms.length - 1) {
				// System.out.println("Place Marker is at index "+placeMarker+" adding the length of "+terms[k]);
				placeMarker += terms[k].length() + 1;
				operators[k] = expression.charAt(placeMarker);
			}
			terms[k] = terms[k].trim();
		}
		// for(int k =0;k<terms.length;k++)System.out.print("["+terms[k]+"]");
		createPolynomial(terms);
		findOrderOfOperations();
	}

	public void createPolynomial(PolyNode p) {
		System.out.println("Creating linked list");
		for (int k = numTerms - 1; k > 0; k--)// creates the polynomial by
												// creating new nodes and
												// linking old node to new node
		{
			PolyNode temp = p;
			p = new PolyNode(null);
			temp.setNext(p);
			// System.out.println(temp.getCoeff()+"x^"+temp.getDegree()+" ------->  "+(temp.getNext()).getCoeff()+"x^"+(temp.getNext()).getDegree());
		}
	}

	public void createPolynomial(String[] terms) {
		String[] termSplit = new String[numTerms];
		double[] coeffs = new double[numTerms];
		double[] degrees = new double[numTerms];

		for (int k = 0; k < numTerms; k++) {  //System.out.println(terms[k]);
			termSplit = terms[k].split("X\\^");
			coeffs[k] = Double.parseDouble(termSplit[0]);
			degrees[k] = Double.parseDouble(termSplit[1]);
		}
		firstTerm = new PolyNode(degrees[0], coeffs[0], null);

		PolyNode p = firstTerm;
		for (int k = 1; k < numTerms; k++)// creates the polynomial by creating
											// new nodes and linking old node to
											// new node
		{
			PolyNode temp = p;
			p = new PolyNode(degrees[k], coeffs[k], null);
			temp.setNext(p);
			// System.out.println(temp.getCoeff()+"x^"+temp.getDegree()+" ------->  "+(temp.getNext()).getCoeff()+"x^"+(temp.getNext()).getDegree());
		}
		// System.out.println("\n\n\n");
	}

	public String toString() {
		if (trueEquation != null) {
			PolyNode p = trueEquation.getFirst();
			String sPolynomial = "f(x) = ";
			for (int k = 0; k < trueEquation.getNumTerms(); k++) {
				if (k != trueEquation.getNumTerms() - 1) {
					if (p.getNext() != null) {
						sPolynomial += "" + p + " "
								+ trueEquation.getOperators()[k] + " ";
						p = p.getNext();
					} else {
						sPolynomial += "" + p;
						return sPolynomial;
					}
				} else
					sPolynomial += "" + p;

			}
			return sPolynomial;
		} else {
			PolyNode p = firstTerm;
			String sPolynomial = "f(x) = ";
			for (int k = 0; k < numTerms; k++) {
				if (k != numTerms - 1) {
					if (p.getNext() != null) {
						sPolynomial += "" + p + " " + operators[k] + " ";
						p = p.getNext();
					} else {
						sPolynomial += "" + p;
						return sPolynomial;
					}
				} else
					sPolynomial += "" + p;

			}
			return sPolynomial;
		}

	}

	public String toStringInformal() {
		PolyNode p = firstTerm;
		String sPolynomial = "";
		for (int k = 0; k < numTerms; k++) {
			if (k != numTerms - 1) {
				if (p.getNext() != null) {
					sPolynomial += "" + p.getCoeff() + "X^" + p.getDegree()
							+ " " + operators[k] + " ";
					p = p.getNext();
				} else {
					sPolynomial += "" + p.getCoeff() + "X^" + p.getDegree();
					return sPolynomial;
				}
			} else
				sPolynomial += "" + p.getCoeff() + "X^" + p.getDegree();

		}
		return sPolynomial;

	}

	public PolyNode getNode(int x) {
		PolyNode iterator = firstTerm;
		for (int k = 0; k < x; k++)
			iterator = iterator.getNext();
		return iterator;

	}

	public int findNode(PolyNode p) {
		int termNum = 1;
		PolyNode iterator = firstTerm;
		boolean found = false;
		do {
			found = iterator.equals(p);
			if (!found) {
				iterator = iterator.getNext();
				termNum++;
			}
		} while (iterator.getNext() != null && !found);
		if (found)
			return termNum;
		else
			return -1;
	}

	public void findOrderOfOperations() {// Find the order of operation by
											// looking for the operators and
											// then putting the index of the
											// operator into an array
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

		// for(int k =0;k<maxSteps;k++)
		// System.out.println("Step "+k+": use operator "+orderOfOperations[k]+" ("+operators[orderOfOperations[k]]+")");
		// System.out.println("\n\n\n");

	}

	public void setTrueEquation() {
		trueEquation = new Polynomial(rawString);
	}

	public void operatorShift(int k) {
		char[] newOperators = new char[operators.length - 1];
		int newIndex = 0;
		for (int x = 0; x < operators.length; x++) {
			if (x != k)
				newOperators[newIndex++] = operators[x];
		}
		operators = newOperators;
	}

	public void resetEquation() {
		firstTerm = trueEquation.getFirst();
	}

	public PolyNode getFirst() {
		return firstTerm;
	}

	public int getNumTerms() {
		return numTerms;
	}

	public double getDegree() {
		return degree;
	}

	public String[] getTerms() {
		return terms;
	}

	public char[] getOperators() {
		return operators;
	}

	public int[] getOrderOfOperations() {
		return orderOfOperations;
	}

	public Polynomial getTrueEquation() {
		return trueEquation;
	}

	public String getRawString() {
		return rawString;
	}
}
