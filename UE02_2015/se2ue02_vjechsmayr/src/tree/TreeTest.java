package tree;

import expression.Expression;
import expression.ExpressionFactory;
import expression.ExpressionParser;
import tree.BaseExpressionFactory;

public class TreeTest {

	public static void main(String[] args) {
		final ExpressionFactory fact = new BaseExpressionFactory();
		
		final Expression expression = new ExpressionParser().createExpression("20+(9-1)*(17-7)", fact);
		//final Expression expression = new ExpressionParser().createExpression("20+3", fact);
		expression.draw(0, 0);
	}
}
