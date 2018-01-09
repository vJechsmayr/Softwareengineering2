
public class testCompoundFigure {

	public static void main(String[] args) {

		CompoundFigure c = new CompoundFigure(100,100);
		Controller.addFigure(c);
		c.add(new Rectangle(10,10,100,100));
		c.add(new Rectangle(380,50,30,30));
		c.add(new Rectangle(20,260,50,100));
		c.add(new Circle(300,250,150));
		c.add(new Circle(400,400,20));
		c.add(new Triangle(200,400,50));
		c.add(new Triangle(250,300,-80));
		Controller.display();
	}
}
