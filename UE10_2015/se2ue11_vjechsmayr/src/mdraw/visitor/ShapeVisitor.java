package mdraw.visitor;

import mdraw.shapes.Group;
import mdraw.shapes.ImageAdapter;
import mdraw.shapes.Oval;
import mdraw.shapes.Rect;

/**
 * Shape Visitor Interface 
 * 
 * @author VJ
 * @version 1.0
 */
public interface ShapeVisitor<T> {
	
	public T visitRect(Rect r);
	public T visitOval(Oval o);
	public T visitGroup(Group g);
	public T visitAdapter(ImageAdapter a);

}
