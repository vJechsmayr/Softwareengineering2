package expr;

/**
 * @author V.Jechsmayr 
 * Datum: 18.03.2016
 */

public class Sub extends Operation {

	public Sub(Expr l, Expr r) {
		super(l, r);
	}

	@Override
	public String toInfixString() {
		return "( " + this.getLeft().toInfixString() + " - " + this.getRight().toInfixString() + " )";
	}

	@Override
	public double evaluate() {
		return this.getLeft().evaluate() - this.getRight().evaluate();
	}

	@Override
	public Expr simplify() {
		Expr l = this.getLeft().simplify();
		Expr r = this.getRight().simplify();

		if (r.isZero()) {
			return l;
		}

		if (l.isEquivTo(r)) {
			return new Const(0);
		}
		return new Sub(l, r);
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
