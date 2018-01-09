package mdraw.ui.tools;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import mdraw.ui.DrawPanel;

/**
 * Class implementing a tool palette for the drawing applications. Extend
 * {@link JToolBar} and therefore can be used as a tool bar in the
 * {@link JFrame} of the application.
 * <p>
 * Maintains a set of tools, allows adding tools and has a currently selected
 * tool. The currently selected tool will receive the mouse events from the
 * drawing panel.
 * </p>
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 * 
 * @see DrawPanel
 */
@SuppressWarnings("serial")
public class ToolPalette extends JToolBar {

	/** Map maintaining tools with their names as key */
	private final Map<String, Tool> tools;

	/** The currently selected tool */
	private Tool selected;

	/** Botton group to allow a single tool to be selected in the tool bar */
	private ButtonGroup btnGroup;

	/** Default constructor */
	public ToolPalette() {
		super();
		this.tools = new HashMap<>();
		this.btnGroup = new ButtonGroup();
	}

	/**
	 * Adds an tool to this tool palette.
	 * 
	 * @param tool
	 *            the tool to add
	 */
	public void addTool(Tool tool) {
		tools.put(tool.getName(), tool);
		JToggleButton btn = new JToggleButton(tool);
		add(btn);
		btnGroup.add(btn);
	}

	/**
	 * Gets the currently selected tool.
	 * 
	 * @return the currently selected tool
	 */
	public Tool getSelectedTool() {
		return selected;
	}

	/**
	 * Sets the currently selected tool.
	 * 
	 * @param name
	 *            the name of the tool which should be selected.
	 */
	public void setSelectedTool(String name) {
		Tool t = tools.get(name);
		if (t != null) {
			selected = t;
		}
	}

}
