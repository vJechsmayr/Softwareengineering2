package expr;

/**
 * @author V.Jechsmayr 
 * Datum: 18.03.2016
 */

public class Div extends Operation {

	public Div(Expr l, Expr r) {
		super(l, r);
	}

	@Override
	public String toInfixString() {
		return "( " + this.getLeft().toInfixString() + " / " + this.getRight().toInfixString() + " )";
	}

	@Override
	public double evaluate() {
		if (this.getRight().evaluate() == 0) {
			throw new ArithmeticException("Divide by Zero!");
		}
		return this.getLeft().evaluate() / this.getRight().evaluate();
	}

	@Override
	public Expr simplify() {
		Expr l = this.getLeft().simplify();
		Expr r = this.getRight().simplify();

		if (l.isZero()) {
			return new Const(0);
		} else if (r.isZero()) {
			return l;
		}

		if (l.equals(r)) {
			return new Const(1);
		}

		return new Div(l, r);
	}

	@Override
	public boolean isEquivTo(Expr other) {
		Operation o = (Operation) other;

		if (this.getLeft().equals(o.getLeft()) && this.getRight().equals(o.getRight())) {
			return true;
		}
		return false;
	}

}
