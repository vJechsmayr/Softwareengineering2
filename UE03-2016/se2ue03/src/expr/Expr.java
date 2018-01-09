package expr;

/**
 * @author V.Jechsmayr
 * Datum: 18.03.2016
 */

/**
 * Base class for expressions.
 */
public abstract class Expr {

	/**
	 * Creates and returns a infix string representation of this expression.
	 * 
	 * @return the infix string representation
	 */
	public abstract String toInfixString();
	// Liefert den Ausgabe-String (x * (8.0 + y))

	public abstract double evaluate(); // ArithmeticException bei Division durch 0

	public abstract Expr simplify(); // Ausdruck vereinfachen, vereinfachten
										// Ausdruck zurückliefern

	// boolean equals(){return false;} //Expressions vergleichen

	public abstract boolean isEquivTo(Expr other); // Äquivalenz von Ausdrücken feststellen

	public abstract boolean isZero();

	public abstract boolean isOne();

}
