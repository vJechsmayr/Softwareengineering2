package expr.calc;

/**
 * @author V.Jechsmayr
 * Datum: 18.03.2016
 */

import expr.*;
import inout.*;

/**
 * Class for calculator with expressions
 */
public class ExprCalculator {

	/** an array for storing the last 1000 expressions created. */
	private static Expr[] exprs = new Expr[1000];

	/** the number of expressions already created */
	private static int n = 0;

	/**
	 * Main Method starting the calculator.
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		printCommands();
		calculate();
	}

	/**
	 * Prints all available commands.
	 */
	private static void printCommands() {
		Out.println("Expression Calculator Commands");
		Out.println("==============================");
		Out.println("c - new constant");
		Out.println("v - new variable");
		Out.println("+ - new add expression");
		Out.println("- - new sub expression");
		Out.println("* - new mult expression");
		Out.println("/ - new div expression");
		Out.println("a - assign value to variable");
		Out.println("e - evaluate expression");
		Out.println("s - simplify expression");
		Out.println("q - quit");
		Out.println("==============================");
	}

	/**
	 * Implements the calculation loop.
	 */
	private static void calculate() {
		char cmd = readCmd();
		while (cmd != 'q') {
			switch (cmd) {
			case 'c':
				addExpr(newConst());
				break;
			case 'v':
				addExpr(newVar());
				break;
			case '+':
				addExpr(newAdd());
				break;
			case '-':
				addExpr(newSub());
				break;
			case '*':
				addExpr(newMul());
				break;
			case '/':
				addExpr(newDiv());
				break;
			case 'e':
				addExpr(evaluateExpr());
				break;
			case 's':
				addExpr(simplifyExpr());
				break;
			case 'a':
				doAssignment();
				break;
			default:
				Out.println("Invalid command! Repeat command Input");
				break;
			}
			cmd = readCmd();
		}
	}

	/**
	 * Reads in the next command.
	 * 
	 * @return the command character
	 */
	private static char readCmd() {
		Out.print("Command: ");
		char cmd = In.readChar();
		return Character.toLowerCase(cmd);
	}

	/**
	 * Puts an expression object as next expression into the array of
	 * expressions.
	 * 
	 * @param expr
	 *            the expression object to add
	 */
	private static void addExpr(Expr expr) {
		exprs[n] = expr;
		Out.println("  [" + n + "]: " + expr.toInfixString());
		n = (n + 1) % 1000;
	}

	/**
	 * Creates a new constant expression.
	 * 
	 * @return the constant expression
	 */
	private static Expr newConst() {
		Out.print("Const value: ");
		double v = In.readDouble();

		Expr ex = new Const(v);
		// addExpr(ex);
		return ex;
	}

	/**
	 * Creates a new variable expression.
	 * 
	 * @return the variable expression
	 */
	private static Expr newVar() {
		Out.print("Variable name: ");
		String n = In.readWord();

		Expr ex = new Var(n);
		// addExpr(ex);
		return ex;
	}

	/**
	 * Creates a new addition expression.
	 * 
	 * @return the addition expression
	 */
	private static Expr newAdd() {
		Expr left = getExpr("left");
		Expr right = getExpr("right");

		Expr ex = new Add(left, right);
		// addExpr(ex);

		return ex;
	}

	/**
	 * Creates a new subtraction expression.
	 * 
	 * @return the subtration expression
	 */
	private static Expr newSub() {
		Expr left = getExpr("left");
		Expr right = getExpr("right");

		Expr ex = new Sub(left, right);
		// addExpr(ex);

		return ex;
	}

	/**
	 * Creates a new multiplication expression.
	 * 
	 * @return the multiplication expression
	 */
	private static Expr newMul() {
		Expr left = getExpr("left");
		Expr right = getExpr("right");

		Expr ex = new Mul(left, right);
		// addExpr(ex);

		return ex;
	}

	/**
	 * Creates a new division expression.
	 * 
	 * @return the division expression
	 */
	private static Expr newDiv() {
		Expr left = getExpr("left");
		Expr right = getExpr("right");

		Expr ex = new Div(left, right);
		// addExpr(ex);

		return ex;
	}

	/**
	 * Simplifies an expression. The expression is selected from the array of
	 * expressions.
	 * 
	 * @return the simplified expression
	 */
	private static Expr simplifyExpr() {
		Expr expr = getExpr("simplification");

		Expr ex = expr.simplify();
		// addExpr(ex);

		return ex;
	}

	/**
	 * Evaluates an expression. The expression is selected from the array of
	 * expressions.
	 * 
	 * @return the result of the evaluation wrapped in a constant expression
	 */
	private static Expr evaluateExpr() {
		Expr expr = getExpr("evaluation");
		double v = 0.0;

		v = expr.evaluate();
		Expr ex = new Const(v);

		// addExpr(ex);

		return ex;
	}

	/**
	 * Assigns a value to a variable expression. The variable expression is
	 * selected from the available variable expressions in the array of
	 * expressions.
	 */
	private static void doAssignment() {
		Out.print("Variable name: ");
		String vName = In.readWord();

		Expr ex = searchExpr(vName);

		Out.print("Value: ");
		double val = In.readDouble();
		// TODO: set value of variable to val

		Expr ex2 = new Const(val);
		// addExpr(ex2);

		// WTF?

	}

	private static Var searchExpr(String vName) {
		for (Expr e : exprs) {
			if (e instanceof Var && ((Var) e).getName().equalsIgnoreCase(vName)) {
				return (Var) e;
			}
		}
		return null;
	}

	/**
	 * Gets a expressions from the array of already created expressions
	 * 
	 * @param usage
	 *            a string indicating the intended use of the expression
	 * @return an expression selected by index.
	 */
	private static Expr getExpr(String usage) {
		Out.print("Expression index for " + usage + ": ");
		int idx = In.readInt();
		while (idx < 0 || idx >= n) {
			Out.println("Invalid index! Index must be between 0 and " + (n - 1) + "!");
			Out.print("Expression index for " + usage + ": ");
			idx = In.readInt();
		}
		Expr expr = exprs[idx];
		return expr;
	}

}
