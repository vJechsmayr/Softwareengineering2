package mdraw.undoredo;

/**
 * Interface for Undo/Redo Methods
 * 
 * @author VJ
 * @version 1.0
 */
public interface IUndoRedoAction {

	public void undo();
	public void redo();

}
