package mdraw.model;

import java.util.EventObject;

import mdraw.shapes.Shape;

/**
 * Event object for selection events.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ShapeSelectionEvent extends EventObject {

	/** Selected shapes */
	private final Shape[] selected;

	/**
	 * Constructor initializing event source and selected shapes.
	 * 
	 * @param source
	 *            the event source
	 * @param selected
	 *            the selected shapes
	 */
	public ShapeSelectionEvent(Object source, Shape[] selected) {
		super(source);
		this.selected = selected;
	}

	/**
	 * Gets the selected shapes.
	 * 
	 * @return the selected shapes
	 */
	public Shape[] getSelectedShapes() {
		return selected;
	}

}