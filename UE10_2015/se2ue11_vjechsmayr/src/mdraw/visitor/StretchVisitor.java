package mdraw.visitor;

import mdraw.shapes.Group;
import mdraw.shapes.ImageAdapter;
import mdraw.shapes.Oval;
import mdraw.shapes.Rect;
import mdraw.shapes.Shape;

/**
 * Stretch Shapes 
 * 
 * @author VJ
 * @version 1.0
 */
public class StretchVisitor implements ShapeVisitor{

	/**
	 * Stretch Rectangle
	 * 
	 * @param r
	 *            The Rectangle r to stretch height to the double
	 */
	@Override
	public Object visitRect(Rect r) {
		Rect rnew = r;
		rnew.setSize(r.getWidth(), r.getHeight()*2);

		return rnew;
	}

	/**
	 * Stretch Oval
	 * 
	 * @param o
	 *            The oval o to stretch width to the double
	 */
	@Override
	public Object visitOval(Oval o) {
		Oval onew = o;
		onew.setSize(o.getWidth()*2, o.getHeight());
		return onew;
	}

	/**
	 * Stretch Group
	 * 
	 * @param g
	 *            The Group g to stretch width and height both to double
	 */
	@Override
	public Object visitGroup(Group g) {
		
		Group gnew = g;
		Shape[] s = g.getElements();
		Shape[] snew = gnew.getElements();
		
		for(int i = 0; i < s.length ;i++)
		{
			int h = s[i].getHeight()*2;
			int w = s[i].getWidth()*2;
			snew[i].setSize(w, h);
		}
		return gnew;
	}
	
	/**
	 * Stretch Adapter
	 * 
	 * @param a
	 *            The ImageAdapter a to stretch -> no Stretching!
	 */
	@Override
	public Object visitAdapter(ImageAdapter a) {
		return a;
	}

}
