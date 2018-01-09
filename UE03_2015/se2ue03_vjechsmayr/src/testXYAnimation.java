
public class testXYAnimation {

	public static void main(String[] args) {
		
		Rectangle r = new Rectangle(100,100,100,100);
		Controller.addFigure(r);
		Controller.addAnimation(r.createYAnimation());
		
		Circle c = new Circle(50, 50, 100);
		Controller.addFigure(c);
		Controller.addAnimation(c.createXAnimation());
		
		Triangle t = new Triangle(100);
		Controller.addFigure(t);
		Controller.addAnimation(t.createXAnimation());
		Controller.addAnimation(t.createYAnimation());
		
		Controller.display();
	}
}
