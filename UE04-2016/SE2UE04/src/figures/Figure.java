package figures;

/**
 * 
 * @author V.Jechsmayr Datum: 07.04.2016
 *
 */

public abstract class Figure {

	private int x = 10;
	private int y = 10;

	public Figure() {
		this(10, 10);
	}

	public Figure(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void draw(int xOrigin, int yOrigin);

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public abstract double getArea();

	/**
	 * Advantages and Disadvantages of using an anonymous inner class compared
	 * to a named inner class
	 * 
	 * Anonyme Klassen haben den Vorteil dass der Code kurz ist und keine
	 * Codeverdoppelung entsteht, jedoch nur einmal verwendet werden kann.
	 * 
	 * Named Klassen haben den Vorteil dass sie öfter verwendet werden können,
	 * jedoch ist mehr Code zur Umsetzung erforderlich und der Code im
	 * allgemeinen wird meiner Meinung nach schwerer zu lesen/unleserlicher.
	 * 
	 */

	public Animation createXAnimation() {
		return new Animation() {
			@Override
			public void animate(int frame) {
				x++;
			}
		};
	}

	public Animation createYAnimation() {
		return new YAnimation(this);
	}

	private static class YAnimation implements Animation {
		Figure figure;

		public YAnimation(Figure figure) {
			this.figure = figure;
		}

		@Override
		public void animate(int frame) {
			this.figure.y++;
		}
	}

	/**
	 * 
	 * Advantages and Disadvantages of modeling animations using an interface
	 * compared to an abstract base class
	 * 
	 * Der Vorteil der Verwendung von Interfaces ist dass man mehrere Interfaces
	 * in eine Klasse implementieren und somit mehrere Methoden zusammenfügen
	 * kann.
	 * 
	 * Ein Nachteil bei Interfaces ist dass jede Methode implementiert werden
	 * muss, während bei abstrakten Klassen es schon implementierte Methoden
	 * geben kann, welche nicht nochmal geschrieben werden müssen, somit haben
	 * wir keine Codeverdoppelung.
	 * 
	 * 
	 */

	public Animation createCircleAnimation(int radius, int framesPerRotation) {
		return new CircleAnimation(radius, framesPerRotation);
	}

	private class CircleAnimation implements Animation {
		private int radius = 10;
		private int f;
		private int oX;
		private int oY;

		public CircleAnimation(int r, int f) {
			this.radius = r;
			this.f = f;
			this.oX = x;
			this.oY = y;
		}

		@Override
		public void animate(int frame) {
			double degree = Math.PI / this.f * (frame % this.f) * 2;
			int offsetX = (int) (Math.sin(degree) * this.radius);
			int offsetY = (int) (Math.cos(degree) * this.radius);
			x = this.oX + offsetX;
			y = this.oY + offsetY;
		}
	}

}
