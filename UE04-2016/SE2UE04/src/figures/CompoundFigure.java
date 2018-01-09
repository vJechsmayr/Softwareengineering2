package figures;

import java.util.Iterator;

/**
 * 
 * @author V.Jechsmayr Datum: 07.04.2016
 *
 */

public class CompoundFigure extends Figure {

	private FigureList head = null;

	public CompoundFigure(int x, int y) {
		super(x, y);
	}

	private static class FigureList implements Iterable<Figure> {
		private Figure node;
		private FigureList next;

		public FigureList(Figure node, FigureList next) {
			this.node = node;
			this.next = next;
		}

		@Override
		public Iterator<Figure> iterator() {
			Iterator<Figure> it = new Iterator<Figure>() {

				@Override
				public Figure next() {
					return this.next();
				}

				@Override
				public boolean hasNext() {
					if (this.next() != null) {
						return true;
					}
					return false;
				}

			};
			return it;
		}

	}

	public void add(Figure figure) {
		head = new FigureList(figure, head);
	}

	@Override
	public void draw(int xOrigin, int yOrigin) {
		FigureList list = this.head;

		while (list != null) {
			list.node.draw(xOrigin + this.getX(), yOrigin + this.getY());
			list = list.next;
		}

	}

	@Override
	public double getArea() {
		FigureList list = this.head;
		double sum = 0;

		while (list != null) {
			sum = sum + list.node.getArea();
			list = list.next;
		}
		return sum;
	}

}
