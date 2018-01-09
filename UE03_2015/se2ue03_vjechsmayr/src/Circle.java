
public class Circle extends Figure{
	
	int radius = 10;
	
	public Circle()
	{
		super();
	}
	
	public Circle(int r)
	{
		super();
		this.radius = r;
	}
	
	public Circle(int x, int y)
	{
		super(x,y);
	}
	
	public Circle(int x, int y, int r)
	{
		super(x,y);
		this.radius = r;
	}

	@Override
	public void draw(int xOrigin, int yOrigin) {
		Window.drawCircle(super.getX() + xOrigin, super.getY() + yOrigin, this.radius);
	}
}
