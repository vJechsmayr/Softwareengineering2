
public abstract class Figure {

	private int x = 200;
	private int y = 200;
	
	public Figure(){}
	
	public Figure(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public abstract void draw(int xOrigin, int yOrigin);
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	//X-Animation & Y-Animation (Step 3)
	//Kommentar im Abgabedokument
	
	public Animation createXAnimation()
	{
		return new Animation()
		{
			@Override
			public void animate(int frame)
			{
				x++;
			}
		};
	}
	
	public Animation createYAnimation()
	{
		return new YAnimation(this);
	}
	
	private static class YAnimation implements Animation
	{

		Figure figure;
		
		public YAnimation(Figure f)
		{
			this.figure = f;
		}
		
		@Override
		public void animate(int frame) {
			this.figure.y++;
		}		
	}

	//Circle-Animation (Step 4) 
	//Kommentar im Abgabedokument
	
	public Animation createCircleAnimation(int radius, int framesPerRotation)
	{
		return new CircleAnimation(radius, framesPerRotation);
	}
	
	private class CircleAnimation implements Animation
	{
		private int radius = 10;
		private int fpR;
		private int oldX;
		private int oldY;
		
		public CircleAnimation(int radius, int framesPerRotation)
		{
			this.radius = radius;
			this.fpR = framesPerRotation;
			this.oldX = x;
			this.oldY = y;
		}

		@Override
		public void animate(int frame) {
			double deg = Math.PI/this.fpR * (frame%this.fpR) * 2;
			int xOffset = (int) (Math.sin(deg) * this.radius);
			int yOffset = (int) (Math.cos(deg) * this.radius);
			x = this.oldX + xOffset;
			y = this.oldY + yOffset;	
		}
	}
}
