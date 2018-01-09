package tree;

import inout.Window;
import expression.Expression;

public class ConstantExpression implements Expression{
	
	private int value;
	
	public ConstantExpression(int v)
	{
		this.value = v;
	}
	
	public ConstantExpression(String v)
	{
		this.value = Integer.valueOf(v);
	}
	
	@Override
	public int evaluate() {
		return value;
	}

	@Override
	public void draw(int x, int y) {
		int textX = x + getCenter() - Window.getTextWidth(Integer.toString(value)) / 2;
		int textY = y + (Window.getTextHeight()/2);
		Window.drawText(Integer.toString(value), textX, textY);		
	}

	@Override
	public int getWidth() {
		return Math.max(20, Window.getTextWidth(Integer.toString(value)));
	}

	@Override
	public int getCenter() {
		return getWidth()/2;
		}
}
