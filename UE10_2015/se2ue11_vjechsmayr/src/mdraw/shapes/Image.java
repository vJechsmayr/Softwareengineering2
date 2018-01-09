package mdraw.shapes;

import java.awt.Graphics;

/**
 * Image Interface 
 * 
 * @author VJ
 * @version 1.0
 */
public interface Image{

	public void setX(int x);
	public void setY(int y);
	public int getX();
	public int getY();
	
	public int getWidth();
	public int getHeight();
	
	public Image getComponent();
	public void draw(Graphics g);
}
