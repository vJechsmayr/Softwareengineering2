package mdraw.ui.tools;

import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.ImageIcon;

import mdraw.model.ShapeModel;
import mdraw.shapes.Oval;

/**
 * Tool for adding a {@link Oval} object. Object is added on
 * {@link RectTool#mouseClicked(MouseEvent)} events.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
@SuppressWarnings("serial")
public class OvalTool extends Tool {

	/** Default width of added oval */
	private static final int DEFAULT_WIDTH = 50;
	
	/** Default height of added oval */
	private static final int DEFAULT_HEIGHT = 40;

	/** Shape model where the rectangle is added */
	private final ShapeModel model;

	/**
	 * Constructor setting palette and model.
	 * 
	 * @param palette
	 *            the tool palette where this tool should be added
	 * @param model
	 *            the shape model where the oval is added
	 */
	public OvalTool(ToolPalette palette, ShapeModel model) {
		super("Oval", new ImageIcon("oval.png"), palette);
		this.model = model;
		putValue(Action.SHORT_DESCRIPTION, "Create an oval");
	}

	/**
	 * Handles mouse clicked events (forwarded from drawing panel) and adds a
	 * {@link Oval} object at current mouse position and with default width and
	 * height to the shape model.
	 * 
	 * @param me
	 *            the mouse event object
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		Oval o = new Oval(me.getX(), me.getY(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
		model.clearSelection();
		model.addShape(o);
	}

}
