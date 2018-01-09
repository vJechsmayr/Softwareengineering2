package tree;

import inout.Window;
import expression.Expression;

public abstract class BinaryExpression implements Expression {
	
	private final Expression left;
	private final Expression right;
	private static final int BOX_HEIGHT = 80;
	
	public BinaryExpression(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	public Expression getLeft(){
		return left;
	}
	
	public Expression getRight(){
		return right;
	}
	
	public int getWidth(){
		int value = evaluate();
		int width = left.getWidth() + right.getWidth();
		int textWidth = Window.getTextWidth(Integer.toString(value));
		
		return Math.max(width, textWidth);
	}
	
	
	public int getCenter() {
		 int centerLeft = getLeft().getCenter();
		  int centerRight = right.getCenter() + left.getWidth();
		  return (centerRight - centerLeft) / 2 + centerLeft;
	}
	
	public void draw(char op, int x, int y){
		int cX = x + this.getCenter();
		int cL = x + getLeft().getCenter();
		int cR = x + getRight().getCenter() + getLeft().getWidth();
		int textH = Window.getTextHeight();
		int vY = y + textH / 2;
		int opY = vY + textH;
		int lineStartY = opY + 15;
		int lineEndY = y + BOX_HEIGHT;
		
		int value = evaluate();
		
		Window.drawTextCentered(Integer.toString(value), cX , vY);
		Window.drawTextCentered("" + op, cX, opY);
		Window.drawLine(cX, lineStartY, cL, lineEndY);
		Window.drawLine(cX, lineStartY, cR, lineEndY);
		
		getLeft().draw(x, y + BOX_HEIGHT);
		getRight().draw(x + getLeft().getWidth(), y + BOX_HEIGHT);
	}
}
