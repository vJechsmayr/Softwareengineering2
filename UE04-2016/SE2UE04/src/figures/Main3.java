package figures;

/**
 * Vorgabe
 */

public class Main3 {

	public static void main(String[] args) {
		testStage3();
	}

	public static void testStage3() {
		CompoundFigure f = new CompoundFigure(0, 0);
		f.add(new Circle(250, 50, 50));
		f.add(new Circle(250, 200, 100));
		createArm(f, 200, 100, -10, 0, -1);
		createArm(f, 300, 100, 10, 0, 1);
		createArm(f, 200, 300, -10, 20, 1);
		createArm(f, 300, 300, 10, 20, -1);
		Controller.addAnimation(f.createXAnimation());
		Controller.addAnimation(f.createYAnimation());
		Controller.addFigure(f);
		Controller.display();
	}

	private static void createArm(CompoundFigure parent, int x, int y, int xOff, int yOff, int dir) {
		for (int i = 0; i < 10; i++) {
			Rectangle c = new Rectangle(x + i * xOff, y + i * yOff, 10, 10);
			parent.add(c);
		}
	}
}
