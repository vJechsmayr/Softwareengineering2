package mdraw.visitor;

import java.awt.event.MouseEvent;

import mdraw.shapes.Group;
import mdraw.shapes.Image;
import mdraw.shapes.ImageAdapter;
import mdraw.shapes.Oval;
import mdraw.shapes.Rect;
import mdraw.shapes.Shape;
import mdraw.ui.tools.RectTool;


/**
 * Compute Area of Shapes 
 * 
 * @author VJ
 * @version 1.0
 */
public class ComputeAreaVisitor implements ShapeVisitor{

	
	/**
	 * Compute Rectangle Area
	 * 
	 * @param r
	 *            The Rectangle r to compute the Area
	 */
	@Override
	public Object visitRect(Rect r) {
		float a = r.getHeight() * r.getWidth();
		//System.out.println(a);
		return a;
	}

	/**
	 * Compute Oval Area
	 * 
	 * @param o
	 *            The Oval o to compute the Area
	 */
	@Override
	public Object visitOval(Oval o) {
		float a = (o.getHeight()/2) * (o.getWidth()/2) * (float)Math.PI;
		//System.out.println(a);
		return a;
	}

	/**
	 * Compute Group Area
	 * 
	 * @param g
	 *            The Group g to compute the Area of all Shapes in it
	 */
	@Override
	public Object visitGroup(Group g) {
		Shape[] s = g.getElements();
		float summe = 0;
		float a = 0;
		
		for(int i = 0; i < s.length ;i++)
		{
			if(s[i] instanceof Oval)
			{
				a = (s[i].getHeight()/2) * (s[i].getWidth()/2) * (float)Math.PI;
				summe = summe + a;
				a = 0;
			}else if(s[i] instanceof Rect)
			{
				a = s[i].getHeight() * s[i].getWidth();
				summe = summe + a;
				a = 0;
			}else if(s[i] instanceof ImageAdapter)
			{
				a = s[i].getHeight() * s[i].getWidth();
				summe = summe + a;
				a = 0;
			}
		}
		System.out.println(summe);
		return summe;
	}

	/**
	 * Compute Adapter Area
	 * 
	 * @param a
	 *            The ImageAdapter a to compute the Area
	 */
	@Override
	public Object visitAdapter(ImageAdapter a) {
		float b = a.getHeight() * a.getWidth();
		return b;
	}

}
