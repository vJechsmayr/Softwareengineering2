package mdraw.shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Interface for shapes. Defines method to get and set position and size, to
 * draw and fill the shape, test for selection and make a copy of the shape.
 * Must be implemented by all shapes.
 * <p>
 * All shapes implement {@link Cloneable}.
 * </p>
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 * 
 * @see Cloneable
 */
public interface Shape extends Cloneable {

	/**
	 * Gets the left border coordinate of this shape.
	 * 
	 * @return the left border coordinate
	 */
	public abstract int getLeft();

	/**
	 * Gets the top border coordinate of this shape.
	 * 
	 * @return the top coordinate
	 */
	public abstract int getTop();

	/**
	 * Gets the width of this shape.
	 * 
	 * @return the width
	 */
	public abstract int getWidth();

	/**
	 * Gets the height of this shape.
	 * 
	 * @return the height
	 */
	public abstract int getHeight();

	/**
	 * Sets the position of this shape
	 * 
	 * @param x
	 *            the new x position
	 * @param y
	 *            the new y position
	 */
	public abstract void setPos(int x, int y);

	/**
	 * Sets the size of this shape.
	 * 
	 * @param w
	 *            the new width of this shape
	 * @param h
	 *            the new height of this shape
	 */
	public abstract void setSize(int w, int h);

	/**
	 * Draws this shape on Graphics g
	 * 
	 * @param g
	 *            the graphics context
	 */
	public abstract void draw(Graphics g);

	/**
	 * Fills this shape with a color
	 * 
	 * @param g
	 *            the graphics context
	 * @param c
	 *            the fill color
	 */
	public abstract void fill(Graphics g, Color c);

	/**
	 * Determines if the x/y coordinate means a selection of this shape.
	 * Selection is done by clicking near the border of the shape.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @return true if x/y means a selection of this shape, false otherwise
	 */
	public abstract boolean isSelection(int x, int y);

	/**
	 * Makes a deep copy of this shape object.
	 * 
	 * @return the copy of this shape.
	 */
	public abstract Shape copy();

}