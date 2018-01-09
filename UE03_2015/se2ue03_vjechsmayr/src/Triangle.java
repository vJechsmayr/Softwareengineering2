
public class Triangle extends Figure{
	
	private int height = 10;
	
	public Triangle()
	{
		super();
	}
	
	public Triangle(int h)
	{
		super();
		this.height = h;
	}
	
	public Triangle(int x, int y)
	{
		super(x,y);
	}
	
	public Triangle(int x, int y, int h)
	{
		super(x,y);
		this.height = h;
	}

	@Override
	public void draw(int xOrigin, int yOrigin) {
		int tempX = super.getX() + xOrigin;
		int tempY = super.getY() + yOrigin;
		Window.drawLine(tempX, tempY, tempX-this.height, tempY+this.height);
		Window.drawLine(tempX, tempY, tempX+this.height, tempY+this.height);
		Window.drawLine(tempX-this.height, tempY+this.height, tempX+this.height, tempY+this.height);	
	}
}
