package expression;

/**
 * Class for parsing expressions that uses an ExpressionFactory object to create the expression instances.<br/>
 * 
 * Example usage:
 * 
 * <pre>
 * final ExpressionFactory factory = ...;
 * final Expression expression = new ExpressionParser().createExpression("20+(9-1)*(17-7)", factory);
 * </pre>
 */
public final class ExpressionParser {

	private Buffer parser;
	private ExpressionFactory factory;

	public ExpressionParser() {
	}

	/**
	 * Parses an arithmetic expression and uses the given ExpressionFactory instance to create the Expression instances.<br/>
	 * 
	 * Full EBNF:
	 * 
	 * <pre>
	 * Expression = Term { ('+' | '-') Term }
	 * Term = Factor { '*' Factor }
	 * Factor = number | '(' Expression ')'
	 * </pre>
	 */
	public Expression createExpression(String s, ExpressionFactory factory) {
		this.factory = factory;
		this.parser = new Buffer(s);
		final Expression result = parseExpression();
		if (!parser.atEnd()) {
			throw new IllegalArgumentException("Found illegal character '" + parser.getCurrent() + "' at position " + parser.getPosition());
		}
		return result;
	}

	/**
	 * EBNF: <code>Expression = Term { ('+' | '-') Term }</code>
	 */
	private Expression parseExpression() {
		Expression left = parseTerm();
		while (parser.getCurrent() == '+' || parser.getCurrent() == '-') {
			final char operator = parser.advance();
			final Expression right = parseTerm();
			left = factory.createBinary(operator, left, right);
		}

		return left;
	}

	/**
	 * EBNF: <code>Term = Factor { '*' Factor }</code>
	 */
	private Expression parseTerm() {
		Expression left = parseFactor();
		while (parser.getCurrent() == '*') {
			final char operator = parser.advance();
			final Expression right = parseFactor();
			left = factory.createBinary(operator, left, right);
		}

		return left;
	}

	/**
	 * EBNF: <code>Factor = number | '(' Expression ')'</code>
	 */
	private Expression parseFactor() {
		if (parser.getCurrent() == '(') {
			parser.advance();
			final Expression innerExpression = parseExpression();
			if (parser.getCurrent() != ')') {
				throw new IllegalArgumentException("Expected ')' at position " + parser.getPosition());
			}
			parser.advance();
			return innerExpression;
		} else if (Character.isDigit(parser.getCurrent())) {
			int value = parser.getCurrent() - '0';
			parser.advance();
			while (Character.isDigit(parser.getCurrent())) {
				value *= 10;
				value += (parser.getCurrent() - '0');
				parser.advance();
			}
			return factory.createConstant(value);
		} else {
			throw new IllegalArgumentException("Expected '(' or number at position " + parser.getPosition());
		}
	}

	/**
	 * Helper class that is used to look one character ahead when parsing the expressions.
	 */
	private static final class Buffer {

		private String text;
		private int position;

		public Buffer(String text) {
			this.text = text;
			this.position = -1;
			advance();
		}

		private char safeAccess(int i) {
			if (i >= text.length() || i < 0) {
				return 0;
			}

			return text.charAt(position);
		}

		public char getCurrent() {
			return safeAccess(position);
		}

		public char advance() {
			char result = getCurrent();
			position++;
			while (!atEnd() && Character.isWhitespace(getCurrent())) {
				position++;
			}
			return result;
		}

		public boolean atEnd() {
			return position >= text.length();
		}

		public int getPosition() {
			return position;
		}
	}
}
