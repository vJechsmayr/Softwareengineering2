package expression;

/**
 * Interface for creating Expression instances.
 */
public interface ExpressionFactory {

	/**
	 * Creates a new expression instance that represents a constant value.
	 */
	Expression createConstant(int value);

	/**
	 * Creates a new expression instance that represents the specified operation and has left and right as child expressions.
	 */
	Expression createBinary(char op, Expression left, Expression right);
}
