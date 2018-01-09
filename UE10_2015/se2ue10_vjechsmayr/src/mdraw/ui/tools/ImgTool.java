package mdraw.ui.tools;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.Action;
import javax.swing.ImageIcon;

import mdraw.model.ShapeModel;
import mdraw.shapes.ImageAdapter;

/**
 * Tool for adding a {@ Image } object. Object is added on
 * {@link ImgTool#mouseClicked(MouseEvent)} events.
 * 
 * @author VJ
 * 
 * */
@SuppressWarnings("serial")
public class ImgTool extends Tool {

	private final ShapeModel model;
	private BufferedImage img;
	
	/**
	 * Constructor setting palette and model.
	 * 
	 * @param palette
	 *            the tool palette where this tool should be added
	 * @param model
	 *            the shape model where the image is added
	 * 
	 * */
	public ImgTool(ToolPalette palette, ShapeModel model, BufferedImage img)
	{
		super("Image", new ImageIcon("img.png"), palette);
		this.model = model;
		this.img = img;
		putValue(Action.SHORT_DESCRIPTION, "Place Image");
	}
	
	/**
	 * Handles mouse clicked events (forwarded from drawing panel) and adds a
	 * {@link Image} object at current mouse position to the shape model.
	 * 
	 * @param me
	 *            the mouse event object
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		ImageAdapter i = new ImageAdapter(this.img, me.getX(), me.getY());
		model.clearSelection();
		model.addShape(i);
	}

}
