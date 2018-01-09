package mdraw.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import mdraw.model.ShapeModel;
import mdraw.shapes.Group;
import mdraw.shapes.ImageAdapter;
import mdraw.shapes.Oval;
import mdraw.shapes.Rect;
import mdraw.shapes.Shape;
import mdraw.ui.tools.ImgTool;
import mdraw.ui.tools.OvalTool;
import mdraw.ui.tools.RectTool;
import mdraw.ui.tools.SelTool;
import mdraw.ui.tools.ToolPalette;
import mdraw.visitor.ComputeAreaVisitor;
import mdraw.visitor.StretchVisitor;
import mdraw.visitor.UnstretchVisitor;


/**
 * Application object for micro drawing tool.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
public class DrawApp {

	/**
	 * Starts the drawing application with new drawing model.
	 * 
	 * @param args the program arguments (not used)
	 */
	public static void main(String[] args) {
		ShapeModel model = new ShapeModel();
		DrawApp app = new DrawApp(model);
		app.start();
	}

	/** Drawing model */
	private final ShapeModel model;
	
	/** Frame object */
	private final JFrame frame;
	
	/** Tool palette representing tools in tool bar */
	private ToolPalette toolPalette;
	
	/** Drawing panel object */
	private DrawPanel drawPanel;

	/** 
	 * Constructor initializing model and setting up application. 
	 * @param model the model for this application
	 */
	public DrawApp(ShapeModel model) {
		super();
		this.model = model;
		frame = new JFrame("Micro Draw");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUpUI();
	}

	/**
	 * Creates UI components and tool palette.
	 */
	private void setUpUI() {

		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());

		// menu bar
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("Menu");
		menuBar.add(fileMenu);
		JMenuItem exitMenu = new JMenuItem(exitAction);
		fileMenu.add(exitMenu);
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		editMenu.add(delAction);
		editMenu.addSeparator();
		editMenu.add(groupAction);
		editMenu.add(ungroupAction);
		editMenu.addSeparator();
		editMenu.addSeparator();
		editMenu.add(undoAction);
		editMenu.add(redoAction);

		// tools and tool palette
		toolPalette = new ToolPalette();
		contentPane.add(toolPalette, BorderLayout.PAGE_START);
		toolPalette.addTool(new SelTool(toolPalette, model));
		toolPalette.addTool(new RectTool(toolPalette, model));
		toolPalette.addTool(new OvalTool(toolPalette, model));
		toolPalette.addSeparator(new Dimension(8, 12));
		toolPalette.add(delAction);
		toolPalette.addSeparator(new Dimension(8, 12));
		toolPalette.add(groupAction);
		toolPalette.add(ungroupAction);
		toolPalette.addSeparator(new Dimension(8, 12));
		toolPalette.add(undoAction);
		toolPalette.add(redoAction);
		
		toolPalette.addSeparator(new Dimension(8,12));
		toolPalette.add(imageAction);
		
		toolPalette.addSeparator(new Dimension(8,12));
		toolPalette.add(areaAction);
		toolPalette.add(stretchAction);
		toolPalette.add(unstretchAction);
		

		// drawing panel
		drawPanel = new DrawPanel(model, toolPalette);
		contentPane.add(drawPanel, BorderLayout.CENTER);

		frame.pack();
	}

	/**
	 * Starts application by opening frame.
	 */
	private void start() {
		frame.setVisible(true);
	}

	// Action objects

	/** Handler for exit actions */
	@SuppressWarnings("serial")
	private Action exitAction = new AbstractAction("Exit") {
		{
			putValue(Action.SHORT_DESCRIPTION, "Exit application");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	};

	/** Handler for delete actions */
	@SuppressWarnings("serial")
	private Action delAction = new AbstractAction("Del") {
		{
			putValue(Action.ACCELERATOR_KEY,
					KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
			putValue(Action.SHORT_DESCRIPTION, "Delete selected shapes");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Shape[] selected = model.getSelected();
			if (selected.length == 0) {
				return;
			}
			for (Shape s : selected) {
				model.removeShape(s);
			}
			model.clearSelection();
		}
	};

	/** Handler for group actions */
	@SuppressWarnings("serial")
	private Action groupAction = new AbstractAction("Group") {
		{
			putValue(Action.ACCELERATOR_KEY,
					KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_MASK));
			putValue(Action.SHORT_DESCRIPTION, "Group selected shapes");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Shape[] selected = model.getSelected();
			if (selected.length <= 1) {
				JOptionPane.showMessageDialog(frame,
						"Must select at least two elements to build group");
				return;
			}
			for (Shape s : selected) {
				model.removeShape(s);
			}
			Group group = new Group(selected);
			model.addShape(group);
			model.clearSelection();
			model.addSelection(group);
		}
	};

	/** Handler for ungroup actions */
	@SuppressWarnings("serial")
	private Action ungroupAction = new AbstractAction("Ungroup") {
		{
			putValue(Action.ACCELERATOR_KEY,
					KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_MASK));
			putValue(Action.SHORT_DESCRIPTION, "Ungroup selected group");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Shape[] selected = model.getSelected();
			if (selected.length != 1 || !(selected[0] instanceof Group)) {
				JOptionPane.showMessageDialog(frame,
						"Must select a single group to ungroup");
				return;
			}
			Group group = (Group) selected[0];
			model.removeShape(group);
			model.clearSelection();
			for (Shape s : group.getElements()) {
				model.addShape(s);
				model.addSelection(s);
			}
		}
	};

	/** Handler for undo actions */
	@SuppressWarnings("serial")
	private Action undoAction = new AbstractAction("Undo") {
		{
			putValue(Action.ACCELERATOR_KEY,
					KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
			putValue(Action.SHORT_DESCRIPTION, "Undo last operation");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			throw new UnsupportedOperationException();
		}
	};

	/** Handler for redo actions */
	@SuppressWarnings("serial")
	private Action redoAction = new AbstractAction("Redo") {
		{
			putValue(Action.ACCELERATOR_KEY,
					KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
			putValue(Action.SHORT_DESCRIPTION, "Redo last undone operation");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			throw new UnsupportedOperationException();
		}
	};
	
	/** Handler for image actions */
	@SuppressWarnings("serial")
	private Action imageAction = new AbstractAction("Load Image") {
		{
			putValue(Action.ACCELERATOR_KEY,
					KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK));
			putValue(Action.SHORT_DESCRIPTION, "Laod a new Image");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(".");
			int code = chooser.showOpenDialog(null);
			if(code == 0)
			{
				File imgFile = chooser.getSelectedFile();
				BufferedImage img = null;
				try {
					img = ImageIO.read(imgFile);
					toolPalette.addTool(new ImgTool(toolPalette, model, img));
				} catch (IOException e1) {
					System.out.println("No Image Found!");
					e1.printStackTrace();
				}
			}
		}
	};
	
	/** Handler for ComputeArea actions */
	@SuppressWarnings("serial")
	private Action areaAction = new AbstractAction("Compute Area") {
		{
			putValue(Action.SHORT_DESCRIPTION, "Compute Area");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			ComputeAreaVisitor cav = new ComputeAreaVisitor();
			Group g = new Group(model.getShapes());
			
			Shape[] s = g.getElements();
			float summeOval = 0;
			float summeRect = 0;
			 
			for(int i = 0; i<s.length;i++)
			{
				if(s[i] instanceof Oval)
				{
					summeOval = summeOval + (float) cav.visitOval((Oval) s[i]);
				}else if(s[i] instanceof Rect)
					{
						summeRect = summeRect + (float) cav.visitRect((Rect) s[i]);
					}
			}
			
			System.out.println("Ovals: " + summeOval);
			System.out.println("Rect: " + summeRect);
		}
	};
	
	
	/** Handler for Stretch actions */
	@SuppressWarnings("serial")
	private Action stretchAction = new AbstractAction("Stretch Shape") {
		{
			putValue(Action.SHORT_DESCRIPTION, "Stretch Shape");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			StretchVisitor sv = new StretchVisitor();
			Group g = new Group(model.getShapes());
			
			sv.visitGroup(g);
			
		}
	};
	
	/** Handler for unStretch actions */
	@SuppressWarnings("serial")
	private Action unstretchAction = new AbstractAction("Unstretch Shape") {
		{
			putValue(Action.SHORT_DESCRIPTION, "Unstretch Shape");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			UnstretchVisitor usv = new UnstretchVisitor();
			Group g = new Group(model.getShapes());
			
			System.out.println(usv.visitGroup(g));
			
		}
	};

}
