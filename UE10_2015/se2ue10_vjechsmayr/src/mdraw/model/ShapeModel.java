package mdraw.model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.EventListenerList;

import mdraw.shapes.ImageAdapter;
import mdraw.shapes.Shape;

/**
 * Model maintaining a list of shapes in a drawing application and selection of
 * shapes. Allows accessing shapes, adding and removing shapes, and moving and
 * resizing shapes. Allows setting, adding, and removing selected shapes. Will
 * signal <code>shapeChangeEvents</code> when changing the set of shapes. Will
 * signal <code>shapeSelectionEvents</code> when changing the selection of
 * shapes.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
public class ShapeModel {

	/** List of shapes */
	private final List<Shape> shapes;

	/** List of selected shapes */
	private final List<Shape> selected;

	/** List for event listeners */
	private final EventListenerList listeners;

	/** Default constructor */
	public ShapeModel() {
		super();
		this.shapes = new ArrayList<>();
		this.selected = new ArrayList<>();
		this.listeners = new EventListenerList();
	}

	// shapes

	/**
	 * Gets the shapes of this model.
	 * 
	 * @return the shapes of this model
	 */
	public Shape[] getShapes() {
		return shapes.toArray(new Shape[shapes.size()]);
	}

	/**
	 * Adds a shape to this shape model. Shape must not be null.
	 * 
	 * @param s
	 *            the shape to add
	 */
	public void addShape(Shape s) {
		assert (s != null);
		shapes.add(s);
		fireShapeAdded(s);
	}

	/**
	 * Removes a shape from this model. Shape must not be null.
	 * 
	 * @param s
	 *            the shape to remove
	 */
	public void removeShape(Shape s) {
		assert (s != null);
		shapes.remove(s);
		fireShapeRemoved(s);
		removeSelection(s);
	}

	/**
	 * Moves a shape to a new position
	 * 
	 * @param s
	 *            the shape to move
	 * @param dx
	 *            the delta in x
	 * @param dy
	 *            the delta in y
	 */
	public void moveShape(Shape s, int dx, int dy) {
		s.setPos(s.getLeft() + dx, s.getTop() + dy);
		fireShapeChanged(s);
	}

	/**
	 * Resizes the shape to new width and height
	 * 
	 * @param s
	 *            the shape to resize
	 * @param w
	 *            the new width
	 * @param h
	 *            the new height
	 */
	public void resizeShape(Shape s, int w, int h) {
		s.setSize(w, h);
		fireShapeChanged(s);
	}

	/**
	 * Adds a shape change listener
	 * 
	 * @param l
	 *            the shape change listener to add
	 */
	public void addShapeChangedListener(ShapeChangedListener l) {
		listeners.add(ShapeChangedListener.class, l);
	}

	/**
	 * Removes a shape change listener
	 * 
	 * @param l
	 *            the shape change listener to remove
	 */
	public void removeShapeChangedListener(ShapeChangedListener l) {
		listeners.remove(ShapeChangedListener.class, l);
	}

	// selections

	/**
	 * Gets the currently selected shapes.
	 * 
	 * @return the selected shapes
	 */
	public Shape[] getSelected() {
		return selected.toArray(new Shape[selected.size()]);
	}

	/**
	 * Sets the selected shapes. Selected shapes must be a subset of all the
	 * shapes in model.
	 * 
	 * @param shapes
	 *            the selected shapes
	 */
	public void setSelection(Shape[] shapes) {
		assert (shapes != null);
		selected.clear();
		selected.addAll(Arrays.asList(shapes));
		fireSelectionChanged(selected);
	}

	/**
	 * Adds a shape to the currently selected shapes.
	 * 
	 * @param s
	 *            the shape to add to the selected shapes
	 */
	public void addSelection(Shape s) {
		assert (s != null);
		if (selected.add(s)) {
			fireSelectionChanged(selected);
		}
	}

	/**
	 * Removes a shape to the currently selected shapes.
	 * 
	 * @param s
	 *            the shape to remove to the selected shapes
	 */
	public void removeSelection(Shape s) {
		assert (s != null);
		if (selected.remove(s)) {
			fireSelectionChanged(selected);
		}
	}

	/**
	 * Clears the selected shapes. Selected shapes will be empty afterwards.
	 */
	public void clearSelection() {
		selected.clear();
		fireSelectionChanged(selected);
	}

	/**
	 * Tests if the given shape is in the set of selected shapes.
	 * 
	 * @param shape
	 *            the shape to test if in the set of selected shapes.
	 * @return <code>true</code> if shape is in the set of selected shapes
	 */
	public boolean isSelected(Shape shape) {
		for (Shape s : selected) {
			if (s == shape) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a shape selection listener
	 * 
	 * @param l
	 *            the shape selection listener to add
	 */
	public void addShapeSelectionListener(ShapeSelectionListener l) {
		listeners.add(ShapeSelectionListener.class, l);
	}

	/**
	 * Removes a shape selection listener
	 * 
	 * @param l
	 *            the shape selection listener to remove
	 */
	public void removeShapeSelectionListener(ShapeSelectionListener l) {
		listeners.remove(ShapeSelectionListener.class, l);
	}

	// private

	/**
	 * Fires a shape added event for the given shape.
	 * 
	 * @param s
	 *            the shape which has been added
	 */
	private void fireShapeAdded(Shape s) {
		ShapeChangedEvent evt = new ShapeChangedEvent(this, "added",
				new Shape[] { s });
		for (ShapeChangedListener l : listeners
				.getListeners(ShapeChangedListener.class)) {
			l.shapeAdded(evt);
		}
	}
	

	/**
	 * Fires a shape removed event for the given shape.
	 * 
	 * @param s
	 *            the shape which has been removed
	 */
	private void fireShapeRemoved(Shape s) {
		ShapeChangedEvent evt = new ShapeChangedEvent(this, "removed",
				new Shape[] { s });
		for (ShapeChangedListener l : listeners
				.getListeners(ShapeChangedListener.class)) {
			l.shapeRemoved(evt);
		}
	}

	/**
	 * Fires a shape changed event for the given shape.
	 * 
	 * @param shapes
	 *            the shapes which have been changed
	 */
	private void fireShapeChanged(Shape... shapes) {
		ShapeChangedEvent evt = new ShapeChangedEvent(this, "changed", shapes);
		for (ShapeChangedListener l : listeners
				.getListeners(ShapeChangedListener.class)) {
			l.shapeChanged(evt);
		}
	}

	/**
	 * Signals a change in the selected shapes.
	 * 
	 * @param selected
	 *            the shapes which are now selected
	 */
	private void fireSelectionChanged(List<Shape> selected) {
		ShapeSelectionEvent evt = new ShapeSelectionEvent(this, getSelected());
		for (ShapeSelectionListener l : listeners
				.getListeners(ShapeSelectionListener.class)) {
			l.shapeSelectionChanged(evt);
		}
	}

}
