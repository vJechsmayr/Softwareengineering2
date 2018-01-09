package figures;

/**
 * Vorgabe
 * 
 * edited by V.Jechsmayr
 * Datum: 10.04.2016
 * 
 */

import java.util.Comparator;

public class Main5 {

	public static void main(String[] args) {
		testStage5();
	}

	public static void testStage5() {

		Controller.addFigure(new Rectangle(0, 0, 30, 30));
		Controller.addFigure(new Rectangle(0, 0, 40, 40));
		Controller.addFigure(new Circle(0, 0, 50));
		Controller.addFigure(new Rectangle(0, 0, 60, 60));
		Controller.addFigure(new Circle(0, 0, 25));
		Controller.displaySorted(new AgeComparator());
	}
}

class AgeComparator implements Comparator<Figure> {

	@Override
	public int compare(Figure o1, Figure o2) {

		double a1 = o1.getArea();
		double a2 = o2.getArea();

		if (a1 > a2) {
			return 1;
		} else if (a1 < a2) {
			return -1;
		} else {
			return 0;
		}
	}
}
