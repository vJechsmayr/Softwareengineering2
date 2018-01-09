package tree;

import expression.Expression;

public class AddExpression extends BinaryExpression{
	
	public AddExpression(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public int evaluate() {
		return this.getLeft().evaluate() + this.getRight().evaluate();
	}

	@Override
	public void draw(int x, int y) {
		super.draw('+', x, y);
	}
}
