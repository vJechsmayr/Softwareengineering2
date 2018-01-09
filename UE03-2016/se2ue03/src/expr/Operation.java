package expr;

/**
 * @author V.Jechsmayr 
 * Datum: 18.03.2016
 */

public abstract class Operation extends Expr {

	private Expr left;
	private Expr right;

	public Operation(Expr l, Expr r) {
		this.left = l;
		this.right = r;
	}

	public Expr getLeft() {
		return left;
	}

	public Expr getRight() {
		return right;
	}

	public boolean isOne() {
		return false;
	}

	public boolean isZero() {
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o == null) {
			return false;
		} else if (getClass() != o.getClass()) {
			return false;
		}

		Operation otherOP = (Operation) o;

		if (left == null && otherOP.left != null) {
			return false;
		} else if (right == null && otherOP.right != null) {
			return false;
		}
		return true;
	}

}
