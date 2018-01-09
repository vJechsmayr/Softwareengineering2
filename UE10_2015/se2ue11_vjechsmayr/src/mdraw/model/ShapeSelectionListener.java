package mdraw.model;

import java.util.EventListener;

/**
 * Listener interface for changes in shape selections.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
public interface ShapeSelectionListener extends EventListener {

	/**
	 * Reacts to changes in shapes selections.
	 * 
	 * @param evt
	 *            the shape selection event object
	 */
	public void shapeSelectionChanged(ShapeSelectionEvent evt);

}
