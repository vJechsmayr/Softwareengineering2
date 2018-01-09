package mdraw.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import mdraw.model.ShapeChangedEvent;
import mdraw.model.ShapeChangedListener;
import mdraw.model.ShapeModel;
import mdraw.model.ShapeSelectionEvent;
import mdraw.model.ShapeSelectionListener;
import mdraw.shapes.Shape;
import mdraw.ui.tools.ToolPalette;

/**
 * Drawing panel which will display the shapes of shape model and supports
 * adding, selecting, moving and resizing shapes.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
@SuppressWarnings("serial")
public class DrawPanel extends JPanel {

	/** Preferred height of the panel */
	private static final int PREF_HEIGHT = 600;

	/** Preferred width of the panel */
	private static final int PREF_WIDTH = 1000;

	/** Width for testing for selections */
	public static final int SEL_WIDTH = 4;

	/** The shape model operated by this panel */
	private final ShapeModel model;

	/** The tool palette shown in the tool bar */
	private final ToolPalette toolPalette;

	/**
	 * Constructor initializing shape model and setting tool palette
	 * 
	 * @param model
	 *            the shape model
	 * @param toolPalette
	 *            the tool palette for this application
	 */
	public DrawPanel(ShapeModel model, ToolPalette toolPalette) {
		this.model = model;
		this.toolPalette = toolPalette;
		model.addShapeChangedListener(shapeChangedListener);
		model.addShapeSelectionListener(shapeSelectionListener);
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
		this.setBackground(Color.WHITE);
	}

	/**
	 * Paints the shapes in the shape model on this panel Additionally paints
	 * the selected shapes and paints the visual feedback from the current tool.
	 * 
	 * @param g
	 *            the graphics context
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Shape shape : model.getShapes()) {
			shape.draw(g);
		}

		// draw selections
		for (Shape sel : model.getSelected()) {
			drawSelection(sel, g, model.getSelected().length == 1);
		}

		// draw dragging feedback
		if (toolPalette.getSelectedTool() != null) {
			toolPalette.getSelectedTool().paintToolFeedBack(g);
		}
	}

	/**
	 * Draws the selection of shapes
	 * 
	 * @param sel
	 *            the selected shape
	 * @param g
	 *            the graphics context
	 * @param withResizeCorner
	 *            if the right/bottom corner should be shown as a resize anchor
	 */
	private void drawSelection(Shape sel, Graphics g, boolean withResizeCorner) {
		int x = sel.getLeft();
		int y = sel.getTop();
		int w = sel.getWidth();
		int h = sel.getHeight();
		int SEL_WIDTH_2 = SEL_WIDTH / 2;
		g.fillRect(x - SEL_WIDTH_2, y - SEL_WIDTH_2, SEL_WIDTH, SEL_WIDTH);
		g.fillRect(x - SEL_WIDTH_2, y + h - SEL_WIDTH_2, SEL_WIDTH, SEL_WIDTH);
		g.fillRect(x + w - SEL_WIDTH_2, y - SEL_WIDTH_2, SEL_WIDTH, SEL_WIDTH);
		g.fillRect(x + w - SEL_WIDTH_2, y + h - SEL_WIDTH_2, SEL_WIDTH,
				SEL_WIDTH);
		// resize anchor
		if (withResizeCorner) {
			g.fillRect(x + w - SEL_WIDTH_2, y + h - SEL_WIDTH * 2, SEL_WIDTH,
					SEL_WIDTH * 2 + SEL_WIDTH_2);
			g.fillRect(x + w - SEL_WIDTH * 2, y + h - SEL_WIDTH_2, SEL_WIDTH
					* 2 + SEL_WIDTH_2, SEL_WIDTH);
		}
	}

	/**
	 * Listener object for shape changed events. All shape changed events result
	 * in calls to repaint.
	 */
	private ShapeChangedListener shapeChangedListener = new ShapeChangedListener() {

		@Override
		public void shapeRemoved(ShapeChangedEvent evt) {
			repaint();
		}

		@Override
		public void shapeChanged(ShapeChangedEvent evt) {
			repaint();
		}

		@Override
		public void shapeAdded(ShapeChangedEvent evt) {
			repaint();
		}
	};

	/**
	 * Listener object for shape selection events. All shape selections events
	 * result in calls to repaint.
	 */
	private ShapeSelectionListener shapeSelectionListener = new ShapeSelectionListener() {

		@Override
		public void shapeSelectionChanged(ShapeSelectionEvent evt) {
			repaint();
		}

	};

	/**
	 * Handler of mouse events. All mouse events are forwarded to the currently
	 * active tool in the tool palette. Additionally <code>repaint</code> is
	 * called.
	 */
	private MouseAdapter mouseHandler = new MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent me) {
			if (toolPalette.getSelectedTool() != null) {
				toolPalette.getSelectedTool().mouseClicked(me);
			}
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent me) {
			if (toolPalette.getSelectedTool() != null) {
				toolPalette.getSelectedTool().mousePressed(me);
			}
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent me) {
			if (toolPalette.getSelectedTool() != null) {
				toolPalette.getSelectedTool().mouseReleased(me);
			}
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent me) {
			if (toolPalette.getSelectedTool() != null) {
				toolPalette.getSelectedTool().mouseDragged(me);
			}
			repaint();
		}

	};

}
