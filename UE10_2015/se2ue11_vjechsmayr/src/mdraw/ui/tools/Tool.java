package mdraw.ui.tools;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import mdraw.ui.DrawPanel;

/**
 * Abstract base class for tool implementations in the tool palette. The tools
 * handle all the mouse events from the drawing panel. Class <code>Tool</code>
 * extends {@link AbstractAction} and implements {@link MouseListener} and
 * {@link MouseMotionListener}.
 * <p>
 * Extend this class to implement your own tool. Then add an instance of the
 * tool to the tool palette.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 * 
 * @see ToolPalette
 */
@SuppressWarnings("serial")
public abstract class Tool extends AbstractAction implements MouseListener,
		MouseMotionListener {

	/**
	 * Name of the tool. The name is used to identify this tool in the tool
	 * palette. Tools should have a unique name within the application.
	 */
	private final String name;

	/** Reference to the tool palette containing the tool */
	private final ToolPalette palette;

	/**
	 * Constructor setting name and the tool palette. Also sets properties of
	 * base class {@link AbstractAction}.
	 * 
	 * @param name
	 *            the unique tool name
	 * @param palette
	 *            the tool palette containing this tool
	 */
	public Tool(String name, ToolPalette palette) {
		super(name);
		this.name = name;
		this.palette = palette;
		putValue(Action.NAME, name);
	}

	/**
	 * Constructor setting the name, icon, and the tool palette. Also sets
	 * properties of base class {@link AbstractAction}.
	 * 
	 * @param name
	 *            the unique tool name
	 * @param icon
	 *            the icon for this tool shown in the tool bar
	 * @param palette
	 *            the tool palette containing this tool
	 */
	public Tool(String name, Icon icon, ToolPalette palette) {
		super(name, icon);
		this.name = name;
		this.palette = palette;
		putValue(Action.NAME, name);
		putValue(Action.SMALL_ICON, icon);
	}

	/**
	 * Gets the name of this tool. The name is used to identify this tool in the
	 * tool palette.
	 * 
	 * @return the name of this tool
	 */
	public String getName() {
		return name;
	}

	/**
	 * Paint method for showing feedback of this tools to mouse events, e.g.,
	 * when dragging the tool. Called from {@link DrawPanel}.
	 * 
	 * @param g
	 *            the graphics context
	 */
	public void paintToolFeedBack(Graphics g) {
	}

	/**
	 * Reacts to action events of itself by setting this tool as selected tool
	 * in the tool palette.
	 * 
	 * @param e
	 *            the action event object
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		palette.setSelectedTool(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}

}
