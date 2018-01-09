package mdraw.ui.tools;

import java.awt.event.MouseEvent;




import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ImageIcon;

import mdraw.model.ShapeModel;
import mdraw.shapes.Rect;
import mdraw.undoredo.AddShapeAction;
import mdraw.undoredo.IUndoRedoAction;


/**
 * Tool for adding a {@link Rect} object. Object is added on
 * {@link RectTool#mouseClicked(MouseEvent)} events.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
@SuppressWarnings("serial")
public class RectTool extends Tool{

	/** Default width of added rectangle */
	private static final int DEFAULT_WIDTH = 40;
	
	/** Default height of added rectangle */
	private static final int DEFAULT_HEIGHT = 40;

	/** Shape model where the rectangle is added */
	private final ShapeModel model;

	/**
	 * Constructor setting palette and model.
	 * 
	 * @param palette
	 *            the tool palette where this tool should be added
	 * @param model
	 *            the shape model where the rectangle is added
	 */
	public RectTool(ToolPalette palette, ShapeModel model) {
		super("Rect", new ImageIcon("rect.png"), palette);
		this.model = model;
		putValue(Action.SHORT_DESCRIPTION, "Create a rectangle");
	}

	/**
	 * Handles mouse clicked events (forwarded from drawing panel) and adds a
	 * {@link Rect} object at current mouse position and with default width and
	 * height to the shape model.
	 * 
	 * @param me
	 *            the mouse event object
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		Rect r = new Rect(me.getX(), me.getY(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
		model.clearSelection();
		model.addShape(r);
	}
}
