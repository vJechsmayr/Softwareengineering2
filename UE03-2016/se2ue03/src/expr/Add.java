package expr;

/**
 * @author V.Jechsmayr 
 * Datum: 18.03.2016
 */

public class Add extends Operation {

	public Add(Expr l, Expr r) {
		super(l, r);
	}

	@Override
	public String toInfixString() {
		return "( " + this.getLeft().toInfixString() + " + " + this.getRight().toInfixString() + " )";
	}

	@Override
	public double evaluate() {
		return this.getLeft().evaluate() + this.getRight().evaluate();
	}

	@Override
	public Expr simplify() {
		Expr l = this.getLeft().simplify();
		Expr r = this.getRight().simplify();

		if (l.isZero()) {
			return r;
		} else if (r.isZero()) {
			return l;
		} else if (l.isEquivTo(r)) {
			return new Mul(new Const(2), r);
		}
		return new Add(l, r);
	}

	@Override
	public boolean isEquivTo(Expr other) {
		Operation o = (Operation) other;

		if (((this.getLeft().equals(o.getLeft()) && (this.getRight().equals(o.getRight()))))
				|| (this.getLeft().equals(o.getRight()) && (this.getRight().equals(o.getLeft())))) {
			return true;
		}
		return false;
	}

}
