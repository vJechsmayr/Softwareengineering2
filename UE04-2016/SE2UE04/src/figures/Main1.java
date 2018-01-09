package figures;

/**
 * Vorgabe
 *
 * @author Thomas Wuerthinger
 */
public class Main1 {

	public static void main(String[] args) {
		testStage1();
	}

	public static void testStage1() {
		Controller.addFigure(new Circle(200, 200, 40));
		Controller.addFigure(new Rectangle(20, 20, 100, 50));
		Controller.display();
	}

}
