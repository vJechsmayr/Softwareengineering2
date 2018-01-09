package figures;

/**
 * 
 * @author V.Jechsmayr Datum: 07.04.2016
 *
 */

public class Circle extends Figure {

	private int r = 10;

	public Circle() {
		super();
	}

	public Circle(int r) {
		super();
		this.r = r;
	}

	public Circle(int x, int y) {
		super(x, y);
	}

	public Circle(int x, int y, int r) {
		super(x, y);
		this.r = r;
	}

	@Override
	public void draw(int xOrigin, int yOrigin) {
		Window.drawCircle(super.getX() + xOrigin, super.getY() + yOrigin, this.r);
	}

	@Override
	public double getArea() {
		return (double) r * r * Math.PI;
	}
}
