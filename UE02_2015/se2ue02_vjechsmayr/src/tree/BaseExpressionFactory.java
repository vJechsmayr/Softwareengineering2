package tree;

import expression.Expression;
import expression.ExpressionFactory;

public class BaseExpressionFactory implements ExpressionFactory{

	@Override
	public Expression createConstant(int value) {
		ConstantExpression c = new ConstantExpression(value);
		return c;
	}

	@Override
	public Expression createBinary(char op, Expression left, Expression right) {
		
		if(op == '+'){
			AddExpression a = new AddExpression(left,right);
			return a;
		}else
			if(op == '-'){
				SubExpression s = new SubExpression(left, right);
				return s;
			}else
				if(op == '*'){
					MulExpression m = new MulExpression(left, right);
					return m;
				}
		return null;
	}
}
