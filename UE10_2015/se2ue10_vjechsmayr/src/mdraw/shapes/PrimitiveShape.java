package mdraw.shapes;

/**
 * Base class for primitive shapes. <code>PrimitiveShape</code> defines position
 * and width and height {@link #getHeight()}.
 * 
 * @see Shape#copy()
 * @see #getLeft()
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
abstract public class PrimitiveShape extends Object implements Shape {

	/** x-coordinate of the shape. */
	protected int x;

	/** y-coordinate of the shape. */
	protected int y;

	/** Width of the shape. */
	private int w;

	/** Height of the shape. */
	private int h;

	/**
	 * Constructor setting x- and y- coordinates and width and height.
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param w
	 *            the width
	 * @param h
	 *            the height
	 */
	public PrimitiveShape(int x, int y, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	/* (non-Javadoc)
	 * @see mdraw.shapes.Shape#getLeft()
	 */
	@Override
	public int getLeft() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.shapes.Shape#getTop()
	 */
	@Override
	public int getTop() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.shapes.Shape#getWidth()
	 */
	@Override
	public int getWidth() {
		return w;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.shapes.Shape#getHeight()
	 */
	@Override
	public int getHeight() {
		return h;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.shapes.Shape#move(int, int)
	 */
	@Override
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.shapes.Shape#resize(int, int)
	 */
	@Override
	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
	}

	/**
	 * Creates a (deep) copy of this shape by creating a shallow clone.
	 * 
	 * @return a clone of this object
	 * 
	 * @see Object#clone
	 */
	public Shape copy() {
		return (Shape)clone();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error();
		}
	}	

}
