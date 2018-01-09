package mdraw.shapes;

import java.util.ArrayList;
import java.util.List;

/**
 * Class with useful utility functions for dealing with shapes. Contains a set
 * of static methods.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
public class ShapeUtil {

	/** Tolerance value for selections */
	private static double TOL = 4.0;

	/**
	 * Determines of two provided values are close (within TOL).
	 * 
	 * @param v1
	 *            the first value
	 * @param v2
	 *            the second value
	 * @return <code>true</code> if first and second value differ less than
	 *         <code>TOL</code>
	 */
	public static boolean areClose(double v1, double v2) {
		return Math.abs(v1 - v2) <= TOL;
	}

	/**
	 * Tests if value <code>v</code> is within <code>min</code> and
	 * <code>max</code>.
	 * 
	 * @param v
	 *            the value to test
	 * @param min
	 *            the mimimum value
	 * @param max
	 *            the maximum value
	 * @return <code>true</code> if value is within <code>min</code> and
	 *         <code>max</code>
	 */
	public static boolean isWithin(double v, double min, double max) {
		return v >= min && v <= max;
	}

	/**
	 * Gets the leftmost coordinate of the given shapes.
	 * 
	 * @param shapes
	 *            the array of shapes
	 * @return the leftmost coordinate of all the shapes
	 */
	public static int getLeft(Shape[] shapes) {
		int min = Integer.MAX_VALUE;
		for (Shape s : shapes) {
			if (s.getLeft() < min) {
				min = s.getLeft();
			}
		}
		return min;
	}

	/**
	 * Gets the topmost coordinate of the given shapes.
	 * 
	 * @param shapes
	 *            the array of shapes
	 * @return the topmost coordinate of all the shapes
	 */
	public static int getTop(Shape[] shapes) {
		int min = Integer.MAX_VALUE;
		for (Shape s : shapes) {
			if (s.getTop() < min) {
				min = s.getTop();
			}
		}
		return min;
	}

	/**
	 * Gets the with of a set of shapes. It is computed by the difference of the
	 * leftmost coordinate and the rightmost coordinate of the given shapes.
	 * 
	 * @param shapes
	 *            the array of shapes
	 * @return the with of all the shapes
	 */
	public static int getWidth(Shape[] shapes) {
		int right = 0;
		for (Shape s : shapes) {
			int subRight = s.getLeft() + s.getWidth();
			if (right < subRight) {
				right = subRight;
			}
		}
		return right - getLeft(shapes);
	}

	/**
	 * Gets the height of a set of shapes. It is computed by the difference of
	 * the topmost coordinate and the bottom-most coordinate of the given
	 * shapes.
	 * 
	 * @param shapes
	 *            the array of shapes
	 * @return the height of all the shapes
	 */
	public static int getHeight(Shape[] shapes) {
		int bottom = 0;
		for (Shape s : shapes) {
			int subBottom = s.getTop() + s.getHeight();
			if (bottom < subBottom) {
				bottom = subBottom;
			}
		}
		return bottom - getTop(shapes);
	}

	/**
	 * Copies all shapes in an array of shapes.
	 * 
	 * @param shapes
	 *            the shapes to copy.
	 * @return an array containing the copied shapes.
	 */
	public static Shape[] copyShapes(Shape[] shapes) {
		Shape[] copy = new Shape[shapes.length];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = shapes[i].copy();
		}
		return copy;
	}

	/**
	 * Finds the shapes from an set of shapes which are fully contained within
	 * the given coordinates.
	 * 
	 * @param shapes
	 *            the shapes to test for containment
	 * @param left
	 *            the left coordinate
	 * @param top
	 *            the top coordinate
	 * @param right
	 *            the right coordinate
	 * @param bottom
	 *            the bottom coordinate
	 * @return the shapes which are fully contained within the given coordinates
	 */
	public static Shape[] getSelected(Shape[] shapes, int left, int top,
			int right, int bottom) {
		List<Shape> selected = new ArrayList<>();
		for (Shape s : shapes) {
			if (isWithin(s, left, top, right, bottom)) {
				selected.add(s);
			}
		}
		return selected.toArray(new Shape[selected.size()]);
	}

	/**
	 * Tests if the given shape is fully contained within the given coordinates.
	 * 
	 * @param s
	 *            the shape to test
	 * @param left
	 *            the left coordinate
	 * @param top
	 *            the top coordinate
	 * @param right
	 *            the right coordinate
	 * @param bottom
	 *            the bottom coordinate
	 * @return <code>true</code> if shape is fully contained within the given
	 *         coordinates
	 */
	private static boolean isWithin(Shape s, int left, int top, int right,
			int bottom) {
		return s.getLeft() >= left && s.getTop() >= top
				&& s.getLeft() + s.getWidth() <= right
				&& s.getTop() + s.getHeight() <= bottom;
	}

}
