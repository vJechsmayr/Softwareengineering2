
public class testFigures {

	public static void main(String[] args) {

		Rectangle r = new Rectangle(20,20,90,90);
		r.draw(20, 20);
		
		Circle c = new Circle(40);
		c.draw(300, 100);
		
		Triangle t = new Triangle(50);
		t.draw(100, 200);
	}
}
