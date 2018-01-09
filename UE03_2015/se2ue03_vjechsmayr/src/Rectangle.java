
public class Rectangle extends Figure{

	private int width = 10;
	private int height = 10;
	
	public Rectangle()
	{
		super();
	}
	
	public Rectangle(int w, int h)
	{
		super();
		this.width = w;
		this.height = h;
	}
	
	public Rectangle(int x, int y, int w, int h)
	{
		super(x,y);
		this.width = w;
		this.height = h;
	}
	
	@Override
	public void draw(int xOrigin, int yOrigin)
	{
		Window.drawRectangle(super.getX() + xOrigin, super.getY() + yOrigin, this.width, this.height);
	}
}
