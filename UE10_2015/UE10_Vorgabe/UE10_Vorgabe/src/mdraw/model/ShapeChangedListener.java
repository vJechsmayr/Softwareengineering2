package mdraw.model;

import java.util.EventListener;

/**
 * Listener interface for shape change events. Defines methods for reacting to
 * addition, removal and changes of shapes.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
public interface ShapeChangedListener extends EventListener {

	/**
	 * Reacts to addition of shapes to model.
	 * 
	 * @param evt
	 *            the shape changed event object
	 */
	public void shapeAdded(ShapeChangedEvent evt);

	/**
	 * Reacts to removal of shapes from model.
	 * 
	 * @param evt
	 *            the shape changed event object
	 */
	public void shapeRemoved(ShapeChangedEvent evt);

	/**
	 * Reacts to changes of shapes in model.
	 * 
	 * @param evt
	 *            the shape changed event object
	 */
	public void shapeChanged(ShapeChangedEvent evt);

}
