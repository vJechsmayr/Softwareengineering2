package figures;

/**
 * Vorgabe
 * 
 * @author Thomas Wuerthinger
 */
public class Main2 {

	public static void main(String[] args) {
		testStage2();
	}

	public static void testStage2() {
		CompoundFigure compound1 = new CompoundFigure(10, 10);
		CompoundFigure compound2 = new CompoundFigure(110, 10);
		CompoundFigure compound3 = new CompoundFigure(210, 10);
		createRow(compound1, 0, 20, 10);
		createRow(compound2, 0, 20, 10);
		createRow(compound3, 0, 20, 10);
		Controller.addFigure(compound1);
		Controller.addFigure(compound2);
		Controller.addFigure(compound3);
		Controller.display();
	}

	public static void createRow(CompoundFigure parent, int xOff, int yOff, int size) {
		for (int i = 0; i < 5; i++) {
			parent.add(new Rectangle(i * xOff, i * yOff, size, size));
		}
	}

}
