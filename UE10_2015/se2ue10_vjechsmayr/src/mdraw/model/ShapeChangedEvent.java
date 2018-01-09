package mdraw.model;

import java.util.EventObject;

import mdraw.shapes.Shape;

/**
 * Event object for shape changed events. Contains a string with the change type
 * and the added, removed or changed shapes.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ShapeChangedEvent extends EventObject {

	/** The change type; one of "added", "removed" or "changed" */
	private final String change;

	/** the shapes added, removed or changed */
	private final Shape[] shapes;

	/**
	 * Constructor initializing event source, change type and shapes added,
	 * removed or changed.
	 * 
	 * @param source
	 *            the source of the event
	 * @param change
	 *            the change type; one of "added", "removed", or "changed"
	 * @param shapes
	 *            the shapes added, removed or changed
	 */
	public ShapeChangedEvent(Object source, String change, Shape... shapes) {
		super(source);
		this.change = change;
		this.shapes = shapes;
	}
	
	/**
	 * Gets the change type which should be "added", "removed", or "changed".
	 * 
	 * @return the change type
	 */
	public String getChange() {
		return change;
	}

	/**
	 * Gets the shapes added, removed or changed
	 * 
	 * @return the shapes added, removed or changed
	 */
	public Shape[] getShapes() {
		return shapes;
	}

}