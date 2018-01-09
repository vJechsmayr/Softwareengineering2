package mdraw.undoredo;

import mdraw.model.ShapeModel;
import mdraw.shapes.Shape;

/**
 * Application for the undo/redo when Shape is added
 * 
 * @author hp
 * @version 1.0
 */
public class AddShapeAction implements IUndoRedoAction{

	/** Shape which apply the undo/redo Method */
	private Shape s;
	/** Model which to apply the undo/redo Shape */
	private ShapeModel model;
	
	/**
	 * AddShapeAction Constructor
	 * 
	 * @param s
	 * 			Shape which should be undo/redo
	 * 
	 * @param model
	 * 			Model which to undo/redo the Shape
	 */
	public AddShapeAction(Shape s, ShapeModel model)
	{
		this.s = s;
		this.model = model;
	}

	/**
	 * undo Method
	 */
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		model.removeShape(s);
		System.out.println("UNDO");
	}

	/**
	 * redo Method
	 */
	@Override
	public void redo() {
		// TODO Auto-generated method stub
		model.addShape(s);
		System.out.println("REDO");
	}
}
