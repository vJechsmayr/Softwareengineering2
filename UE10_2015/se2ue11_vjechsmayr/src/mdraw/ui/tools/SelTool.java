package mdraw.ui.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.ImageIcon;

import mdraw.model.ShapeModel;
import mdraw.shapes.Shape;
import mdraw.shapes.ShapeUtil;
import mdraw.ui.DrawPanel;

/**
 * Tool implementation for handling selections. Either the tool selects objects,
 * moves selected objects, or resizes a single selected object.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
@SuppressWarnings("serial")
public class SelTool extends Tool {

	/** Enumeration for encoding the different selection states of this tool */
	private enum State {
		IDLE, MOVING, RESIZING, SELECTING
	}

	/**
	 * A constant used for testing for selections and painting selections. Same
	 * as {@link DrawPanel#SEL_WIDTH}
	 */
	private static final int SEL_WIDTH = DrawPanel.SEL_WIDTH;

	/** The shape model */
	private final ShapeModel model;

	/** The current state of this tool */
	private State state = State.IDLE;

	/** Position of the shape used in resizing */
	private Point pos;

	/** Mouse position representing the start of a dragging operation */
	private Point startPoint;

	/** Mouse position representing the end position of a selection action */
	private Point selectionPoint;

	/** Selected shapes by current selection gesture */
	private Shape[] selection;

	/** Shapes moved in a moving gesture */
	private Shape[] movedShapes = null;

	/** Single shape which should be resized */
	private Shape shapeToResize = null;
	
	/** Copy of the <code>shapeToResize</code> resized for feedback */
	private Shape shapeToResizeCopy = null;
	
	/**
	 * Constructor initializing tool palette and shape model
	 * 
	 * @param palette
	 *            the tool palette
	 * @param model
	 *            the shape model
	 */
	public SelTool(ToolPalette palette, ShapeModel model) {
		super("Sel", new ImageIcon("sel.png"), palette);
		this.model = model;
		putValue(Action.SHORT_DESCRIPTION, "Selection tool");
	}

	/**
	 * Paints the feedback for the current gesture. Called by
	 * {@link DrawPanel#paintComponent(Graphics)}. Feedback is dependent of the
	 * state of this tool.
	 * 
	 * @param g
	 *            the graphics context
	 */
	@Override
	public void paintToolFeedBack(Graphics g) {
		if (state == State.IDLE) {
			return;
		}
		if (state == State.SELECTING) {
			// showing selection rectangle
			Color prev = g.getColor();
			g.setColor(Color.GRAY);
			int w = selectionPoint.x - startPoint.x;
			int h = selectionPoint.y - startPoint.y;
			if (w < 0) {
				w = 0;
			}
			if (h < 0) {
				h = 0;
			}
			g.drawRect(startPoint.x, startPoint.y, w, h);
			g.setColor(prev);

		} else if (state == State.MOVING) {
			// dragging moved shapes
			Color prev = g.getColor();
			g.setColor(Color.GRAY);
			for (Shape s : movedShapes) {
				s.draw(g);
			}
			g.setColor(prev);
		} else if (state == State.RESIZING) {
			// dragging resized shape
			Color prev = g.getColor();
			g.setColor(Color.GRAY);
			shapeToResizeCopy.draw(g);
			g.setColor(prev);
		}
	}

	// selection

	/**
	 * Handles mouse clicked events (forwarded from drawing panel) by finding a
	 * selected shape and making it the single selected shape or, if shift down
	 * is pressed, changes the selection of this shape (select when not selected,
	 * deselect when is selected).
	 * 
	 * @param me
	 *            the mouse event object
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		for (Shape shape : model.getShapes()) {
			if (shape.isSelection(me.getX(), me.getY())) {
				if (me.isShiftDown()) {
					changeSelection(shape);
				} else {
					model.setSelection(new Shape[] { shape });
				}
				return;
			}
		}
		// no shape selected
		model.clearSelection();
	}

	// move, resize, select

	/**
	 * Handles mouse pressed events (forwarded from drawing panel). Finds out
	 * the selected element. Initiates a resizing, moving or selection process
	 * dependent on the selection.
	 * 
	 * @param me
	 *            the mouse event object
	 */
	@Override
	public void mousePressed(MouseEvent me) {
		if (isResizeSelection(me)) {
			state = State.RESIZING;
			shapeToResize = model.getSelected()[0];
			pos = new Point(shapeToResize.getLeft(), shapeToResize.getTop());
			startPoint = me.getPoint();
			shapeToResizeCopy = shapeToResize.copy();
		} else if (isMoveSelection(me)) {
			state = State.MOVING;
			startPoint = me.getPoint();
			movedShapes = ShapeUtil.copyShapes(model.getSelected());
		} else if (model.getSelected().length == 0 || me.isShiftDown()) {
			state = State.SELECTING;
			startPoint = me.getPoint();
			selectionPoint = me.getPoint();
			selection = new Shape[0];
		} else {
			state = State.IDLE;
		}
	}

	/**
	 * Handles mouse dragged events (forwarded from drawing panel). Dependent of
	 * the state of this tool will either show resizing, moving, or selecting
	 * shapes. Does not actually execute the operation but will show a visual
	 * feedback.
	 * 
	 * @param me
	 *            the mouse event object
	 */
	@Override
	public void mouseDragged(MouseEvent me) {
		if (state == State.IDLE) {
			return;
		}
		if (state == State.RESIZING) {
			Point end = me.getPoint();
			// required to create new copy because otherwise rounding errors when resizing 
			shapeToResizeCopy = shapeToResize.copy();
			shapeToResizeCopy.setSize(end.x - pos.x, end.y - pos.y);
		} else if (state == State.MOVING) {
			Point currPoint = me.getPoint();
			int dx = currPoint.x - startPoint.x;
			int dy = currPoint.y - startPoint.y;
			for (int i = 0; i < movedShapes.length; i++) {
				Shape original = model.getSelected()[i];
				movedShapes[i].setPos(original.getLeft() + dx,
						original.getTop() + dy);
			}
		} else if (state == State.SELECTING) {
			selectionPoint = me.getPoint();
			Shape[] oldSelection = selection;
			selection = ShapeUtil.getSelected(model.getShapes(), startPoint.x,
					startPoint.y, selectionPoint.x, selectionPoint.y);
			for (Shape s : oldSelection) {
				changeSelection(s);
			}
			for (Shape s : selection) {
				changeSelection(s);
			}
		}
	}

	/**
	 * Handles mouse released events (forwarded from drawing panel). Dependent
	 * of the state of this tool will either resize a selected shape, move
	 * selected shape, or change the selections of shapes.
	 * 
	 * @param me
	 *            the mouse event object
	 */
	@Override
	public void mouseReleased(MouseEvent me) {
		if (state == State.IDLE) {
			return;
		}
		Point end = me.getPoint();
		if (state == State.RESIZING) {
			Shape selected = model.getSelected()[0];
			model.resizeShape(selected, end.x - selected.getLeft(), end.y
					- selected.getTop());
			state = State.IDLE;
		} else if (state == State.MOVING) {
			Point currPoint = me.getPoint();
			int dx = currPoint.x - startPoint.x;
			int dy = currPoint.y - startPoint.y;
			for (Shape s : model.getSelected()) {
				model.moveShape(s, dx, dy);
			}
			state = State.IDLE;
		} else if (state == State.SELECTING) {
			state = State.IDLE;
		}
	}

	/**
	 * Changes the selection state of a shape. That means, when not selected will
	 * select the shape, when selected will deselect the shape.
	 * 
	 * @param s
	 *            the shape to change the selections state.
	 */
	private void changeSelection(Shape s) {
		if (model.isSelected(s)) {
			model.removeSelection(s);
		} else {
			model.addSelection(s);
		}
	}

	/**
	 * Tests if the event should result in move of shapes.
	 * 
	 * @param me
	 *            the mouse event
	 * @return <code>true</code> if this event should result in a move of shapes
	 */
	private boolean isMoveSelection(MouseEvent me) {
		int x = me.getX();
		int y = me.getY();
		for (Shape s : model.getSelected()) {
			if (s.isSelection(x, y)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tests if the event should result in resize of a single selected shape.
	 * 
	 * @param me
	 *            the mouse event
	 * @return <code>true</code> if this event should result in a resize of a
	 *         shape
	 */
	private boolean isResizeSelection(MouseEvent me) {
		Shape[] selected = model.getSelected();
		if (selected.length != 1) {
			// can only resize single shapes
			return false;
		}
		int x = me.getX();
		int y = me.getY();
		for (Shape shape : model.getSelected()) {
			int right = shape.getLeft() + shape.getWidth();
			int bottom = shape.getTop() + shape.getHeight();
			if (x >= right - SEL_WIDTH * 2 && x <= right + SEL_WIDTH
					&& y >= bottom - SEL_WIDTH * 2 && y <= bottom + SEL_WIDTH) {
				return true;
			}
		}
		return false;
	}

}
