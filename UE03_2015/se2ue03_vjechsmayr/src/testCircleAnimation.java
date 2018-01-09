
public class testCircleAnimation {

	public static void main(String[] args) {

		Circle c = new Circle(150,150,5);
		Controller.addFigure(c);
		
		Rectangle r = new Rectangle(100,100,100,100);
		Controller.addFigure(r);
		Controller.addAnimation(r.createCircleAnimation(50, 50));
		Controller.display();
	}
}
