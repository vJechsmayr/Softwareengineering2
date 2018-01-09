
public class CompoundFigure extends Figure{

	private FigureList head = null;
	
	public CompoundFigure(int x, int y)
	{
		super(x,y);
	}

	private static class FigureList{
		private Figure figureNode;
		private FigureList next;
		
		public FigureList(Figure f, FigureList next)
		{
			this.figureNode = f;
			this.next = next;
		}
	}
	
	public void add(Figure figure)
	{
		head = new FigureList(figure, head);
	}
	
	@Override
	public void draw(int xOrigin, int yOrigin) {
		FigureList figure = this.head;
		
		while(figure!=null)
		{
			figure.figureNode.draw(this.getX() + xOrigin, this.getY() + yOrigin);
			figure = figure.next;
		}
	}
}
