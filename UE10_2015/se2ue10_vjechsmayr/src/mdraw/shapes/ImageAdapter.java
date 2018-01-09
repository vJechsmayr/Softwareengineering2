package mdraw.shapes;

import static mdraw.shapes.ShapeUtil.areClose;
import static mdraw.shapes.ShapeUtil.isWithin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

import mdraw.ui.tools.RectTool;

/**
 * Tool for adding a {@link Image} object.
 * 
 * @author VJ
 * @version 1.0
 */
public class ImageAdapter extends PrimitiveShape implements Image{

	/** Image to add to Shapes */
	private BufferedImage img;
	/** x-Coordinate where to place the image */
	private static int x;
	/** y-Coordinate where to place the imgage */
	private static int y;
	
	
	/**
	 * Constructor setting img, x and y.
	 * 
	 * @param img
	 *            the Image img which should be added
	 * @param x
	 *            the x-Coordinate where the Image should be added
	 * @param y
	 *            the y-Coordinate where the Image should be added
	 */
	public ImageAdapter(BufferedImage img, int x, int y)
	{
		super(x, y, img.getWidth(), img.getHeight());
		this.img = img;
		this.x = x;
		this.y = y;
	}
	
	/**
	 *  The set-Method to set the x-Coordinate
	 *  
	 *  @param x
	 *  			the new x-Coordinate 
	 */
	@Override
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 *  The set-Method to set the y-Coordinate
	 *  
	 *  @param y
	 *  			the new y-Coordinate 
	 */
	@Override
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 *  The set-Method to set the Image
	 *  
	 *  @param i
	 *  			the new BufferedImage to set  
	 */
	public void setImage(BufferedImage i)
	{
		this.img = i;
	}
	
	/**
	 *  The get-Method to get the current x-Coordinate
	 */
	@Override
	public int getX()
	{
		return x;
	}
	
	/**
	 *  The get-Method to get the current y-Coordinate
	 */
	@Override
	public int getY()
	{
		return y;
	}
	
	/**
	 *  The get-Method to get the current Image
	 */
	public BufferedImage getImage()
	{
		return img;
	}
	
	/**
	 *  The get-Method to get the current Height
	 */
	@Override
	public int getHeight() 
	{
		return img.getWidth();
	}
	
	/**
	 *  The get-Method to get the current Width
	 */
	@Override
	public int getWidth() 
	{
		return img.getHeight();
	}
	
	/**
	 *  The draw-Method to draw the Image to the x and y Coordinate
	 */
	@Override
	public void draw(Graphics g) {
		ImageObserver imgObs = new JPanel();
		//g.drawImage(img, x, y, imgObs);
		g.drawImage(img, x, y, img.getWidth(), img.getHeight(), imgObs);
	}
	
	/**
	 *  The getComponent Method to return this ImapeAdapter
	 */
	@Override
	public Image getComponent() {
		return this;
	}

	/**
	 *  The fill method to fill Graphic with the Color
	 *  
	 *  @param g
	 *  		Graphic which is meant to fill
	 *  
	 *  @param c
	 *  		Color to fill
	 */
	@Override
	public void fill(Graphics g, Color c) {
		Color prev = g.getColor();
		g.setColor(c);
		g.fillRect(getLeft(), getTop(), getWidth(), getHeight());
		g.setColor(prev);
	}

	/**
	 *  return true if this Object is selected by the Coordinates
	 *  
	 *  @param x
	 *  		the x Coordinate to check
	 *  
	 *  @param y
	 *  		the y Coordinate to check
	 */
	@Override
	public boolean isSelection(int x, int y) {
		return ((areClose(x, getLeft()) || areClose(x, getLeft() + getWidth())) && isWithin(
				y, getTop(), getTop() + getHeight()))
				|| ((areClose(y, getTop()) || areClose(y, getTop()
						+ getHeight())) && isWithin(x, getLeft(), getLeft()
						+ getWidth()));
	}
}
