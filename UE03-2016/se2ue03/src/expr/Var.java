package expr;

/**
 * @author V.Jechsmayr 
 * Datum: 18.03.2016
 */

public class Var extends Expr {

	private String name;

	public Var(String n) {
		this.name = n;
	}

	public Var(double v) {
		this.name = String.valueOf(v);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toInfixString() {
		return name;
	}

	@Override
	public double evaluate() {
		return Integer.parseInt(name);
	}

	@Override
	public Expr simplify() {
		return this;
	}

	@Override
	public boolean isEquivTo(Expr other) {
		if (this.equals(other)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isZero() {
		return false;
	}

	@Override
	public boolean isOne() {
		return false;
	}
}
