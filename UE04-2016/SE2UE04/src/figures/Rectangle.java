package figures;

/**
 * 
 * @author V.Jechsmayr Datum: 07.04.2016
 *
 */

public class Rectangle extends Figure {

	private int w = 10;
	private int h = 10;

	public Rectangle() {
		super();
	}

	public Rectangle(int w, int h) {
		super();
		this.w = w;
		this.h = h;
	}

	public Rectangle(int x, int y, int w, int h) {
		super(x, y);
		this.w = w;
		this.h = h;
	}

	@Override
	public void draw(int xOrigin, int yOrigin) {
		Window.drawRectangle(super.getX() + xOrigin, super.getY() + yOrigin, this.w, this.h);
	}

	@Override
	public double getArea() {
		return (double) w * h;
	}
}
