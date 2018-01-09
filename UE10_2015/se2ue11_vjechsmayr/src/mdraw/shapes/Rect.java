package mdraw.shapes;

import static mdraw.shapes.ShapeUtil.areClose;
import static mdraw.shapes.ShapeUtil.isWithin;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class representing rectangular shapes.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
public class Rect extends PrimitiveShape {

	/**
	 * Constructor initializing x and y coordinate and width and heigth.
	 * 
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @param w
	 *            the width
	 * @param h
	 *            the height
	 */
	public Rect(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	/* (non-Javadoc)
	 * @see mdraw.shapes.Shape#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		g.drawRect(getLeft(), getTop(), getWidth(), getHeight());
	}

	
	/* (non-Javadoc)
	 * @see mdraw.shapes.Shape#fill(java.awt.Graphics, java.awt.Color)
	 */
	@Override
	public void fill(Graphics g, Color c) {
		Color prev = g.getColor();
		g.setColor(c);
		g.fillRect(getLeft(), getTop(), getWidth(), getHeight());
		g.setColor(prev);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.shapes.Shape#isSelection(int, int)
	 */
	@Override
	public boolean isSelection(int x, int y) {
		return ((areClose(x, getLeft()) || areClose(x, getLeft() + getWidth())) && isWithin(
				y, getTop(), getTop() + getHeight()))
				|| ((areClose(y, getTop()) || areClose(y, getTop()
						+ getHeight())) && isWithin(x, getLeft(), getLeft()
						+ getWidth()));
	}

}
