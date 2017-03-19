import javax.swing.JOptionPane;

public class PolyNode {
	private double coeff; // coefficient of each term
	private double degree; // degree of each term
	private PolyNode next; // link to the next term node

	public PolyNode(double d, PolyNode initNext) {
		degree = d;
		coeff = Double.parseDouble(JOptionPane
				.showInputDialog("What is the coefficient for term x^" + degree
						+ "?"));
		next = initNext;

	}

	public PolyNode(double d, double c, PolyNode initNext) {
		degree = d;
		coeff = c;
		next = initNext;
	}

	public PolyNode(PolyNode initNext) {
		degree = Double.parseDouble(JOptionPane
				.showInputDialog("What is the degree of the  next term?"));
		coeff = Double.parseDouble(JOptionPane
				.showInputDialog("What is the coefficient for term x^" + degree
						+ "?"));
		next = initNext;

	}

	public String toString() {
		if (degree == 0)
			return coeff + "";
		else
			return coeff + "X^" + degree;

	}

	public boolean equals(PolyNode p) { // PolyNodes are equal if they have the
										// same Coefficient and Degree
		return ((this.getCoeff() == p.getCoeff()) && (this.getDegree() == p
				.getDegree()));
	}

	public double computePolyNode(double x) {
		// System.out.println(this+" = "+(coeff*(Math.pow(x,degree))));
		return coeff * (Math.pow(x, degree)); // finds the value of the term
												// with value x
	}

	public void changeNode(double d, double c, PolyNode n) {
		if (Double.isNaN(c)) {
			degree = 1;
			coeff = 0;
		} else {
			degree = d;
			coeff = c;
		}
		next = n;
	}

	// ACCESSOR METHODS
	public double getCoeff() {
		return coeff;
	}

	public double getDegree() {
		return degree;
	}

	public PolyNode getNext() {
		return next;
	}

	// MUTATOR METHODS
	public void setCoeff(double newCoeff) {
		coeff = newCoeff;
	}

	public void setDegree(double newDegree) {
		degree = newDegree;
	}

	public void setNext(PolyNode newNext) {
		next = newNext;
	}

}