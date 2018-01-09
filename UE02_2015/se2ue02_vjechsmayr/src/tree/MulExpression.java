package tree;

import expression.Expression;

public class MulExpression extends BinaryExpression{

	public MulExpression(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public int evaluate() {
		return this.getLeft().evaluate() * this.getRight().evaluate();
	}

	@Override
	public void draw(int x, int y) {
		super.draw('*', x, y);
	}

}
