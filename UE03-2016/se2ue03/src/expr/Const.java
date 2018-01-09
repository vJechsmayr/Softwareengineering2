package expr;

/**
 * @author V.Jechsmayr 
 * Datum: 18.03.2016
 */

public class Const extends Expr {

	private double value = 0;

	public Const(double v) {
		this.value = v;
	}

	@Override
	public String toInfixString() {
		return String.valueOf(value);
	}

	@Override
	public double evaluate() {
		return value;
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
		if (value == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isOne() {
		if (value == 1) {
			return true;
		}
		return false;
	}
}
