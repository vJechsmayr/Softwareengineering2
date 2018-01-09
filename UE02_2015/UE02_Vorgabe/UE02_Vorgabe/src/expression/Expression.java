package expression;

/**
 * Interface of one node in the expression tree.
 */
public interface Expression {

	/**
	 * Calculates the arithmetic result of the expression and returns the result.
	 */
	public int evaluate();

	/**
	 * Draws the expression on the screen relative to position (x/y) using the Window class.
	 */
	void draw(int x, int y);

	/**
	 * Computes the total width of this expression node, also taking child nodes into account.
	 */
	int getWidth();

	/**
	 * Determines the center coordinate of the expression. For a nice symmetric drawing, a node should always be placed centered above its
	 * child nodes.
	 */
	int getCenter();
}