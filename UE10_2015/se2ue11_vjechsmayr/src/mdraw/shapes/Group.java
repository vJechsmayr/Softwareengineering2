package mdraw.shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class for grouping shapes into a group which is again a {@link Shape}. Has a
 * set of sub-shapes.
 * 
 * <code>Group</code> implements the Composite pattern for shapes.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
public class Group implements Shape {

	/** Elements of this group */
	private Shape[] elements;

	/**
	 * Constructor initializing elements.
	 * 
	 * @param shapes
	 *            a number of elements
	 */
	public Group(Shape... shapes) {
		super();
		this.elements = shapes.clone();
	}

	/**
	 * Gets the elements of this group.
	 * 
	 * @return the array of shape elements
	 */
	public Shape[] getElements() {
		return elements;
	}

	/**
	 * Gets the left border coordinate of this group by computing the minimum of
	 * the left borders of all the elements.
	 * 
	 * @return the left border coordinate of this group
	 */
	@Override
	public int getLeft() {
		return ShapeUtil.getLeft(elements);
	}

	/**
	 * Gets the top border coordinate of this group by computing the minimum of
	 * the top borders of all the elements.
	 * 
	 * @return the top border coordinate of this group
	 */
	@Override
	public int getTop() {
		return ShapeUtil.getTop(elements);
	}

	/**
	 * Gets the width of this group by computing the difference of the left and
	 * the right coordinate.
	 * 
	 * @return the width of this group
	 */
	@Override
	public int getWidth() {
		return ShapeUtil.getWidth(elements);
	}

	/**
	 * Gets the height of this group by computing the difference of the top and
	 * the bottom coordinate.
	 * 
	 * @return the width of this group
	 */
	@Override
	public int getHeight() {
		return ShapeUtil.getHeight(elements);
	}

	/**
	 * Sets the position of this group. This is accomplished by moving the
	 * positions of all the elements.
	 * 
	 * @param x
	 *            the new x position
	 * @param y
	 *            the new y position
	 */
	@Override
	public void setPos(int x, int y) {
		int dx = x - getLeft();
		int dy = y - getTop();
		for (Shape s : elements) {
			s.setPos(s.getLeft() + dx, s.getTop() + dy);
		}
	}

	/**
	 * Sets the size of this group. This is accomplished by resizing of all the
	 * elements.
	 * 
	 * @param w
	 *            the new width of this shape
	 * @param h
	 *            the new height of this shape
	 */
	@Override
	public void setSize(int w, int h) {
		double wFactor = (double) w / getWidth();
		double hFactor = (double) h / getHeight();
		int x = this.getLeft();
		int y = this.getTop();
		for (Shape s : elements) {
			int sLeft = (int) ((s.getLeft() - x) * wFactor + x);
			int sTop = (int) ((s.getTop() - y) * hFactor + y);
			int sWidth = (int) (s.getWidth() * wFactor);
			int sHeight = (int) (s.getHeight() * hFactor);
			s.setPos(sLeft, sTop);
			s.setSize(sWidth, sHeight);
		}

	}

	/**
	 * Draws this group by drawing all elements in the group.
	 * 
	 * @param g
	 *            the graphics context
	 */
	@Override
	public void draw(Graphics g) {
		for (Shape s : elements) {
			s.draw(g);
		}
	}

	/**
	 * Fills this group by fill all elements in the group.
	 * 
	 * @param g
	 *            the graphics context
	 * @param c
	 *            the fill color
	 */
	@Override
	public void fill(Graphics g, Color c) {
		for (Shape s : elements) {
			s.fill(g, c);
		}
	}

	/**
	 * Determines if the x/y coordinate means a selection of this group. The
	 * group is selected if one of its elements is selected by the mouse
	 * position.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @return true if x/y means a selection of this shape, false otherwise
	 */
	@Override
	public boolean isSelection(int x, int y) {
		for (Shape s : elements) {
			if (s.isSelection(x, y)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Makes a deep copy of this group by making copies of all the
	 * elements of the group and then returning a new group with the copied
	 * elements.
	 * 
	 * @return a deep copy of this group.
	 */
	@Override
	public Shape copy() {
		Group copy;
		copy = new Group(ShapeUtil.copyShapes(this.elements));
		return copy;
	}

}
