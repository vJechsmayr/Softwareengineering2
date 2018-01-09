package remembrain.model;

public class FieldImpl implements Field {
	
	private int width = -1;
	private int height = -1;
	private CellState[][] bombs;
	private CellState[][] points;
	private GameState gameState;
	private int aktR = -1;
	private int aktC = -1;
	
	//constructor that allows to establish a certain state to start from for testing purposes
	public FieldImpl(final CellState initialFieldToRemember[][], final CellState initalFieldRemembered[][]) {
		this.bombs = initialFieldToRemember;
		this.points = initalFieldRemembered;
		this.height = bombs.length;
		this.width = bombs[0].length;
		this.gameState = GameState.INITIAL;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public GameState getGameState() {
		return this.gameState;
	}

	@Override
	public boolean getGameResult() {
		boolean result = true;
		if(this.gameState != GameState.END)
		{
			throw new IllegalStateException();
		}else
		{
			for(int i = 0; i<this.height;i++)
			{
				for(int j = 0; j<this.width;j++)
				{
					if(points[i][j] == CellState.WRONG)
					{
						result = false;
						break;
					}
				}
			}
		}
		return result;
	}

	@Override
	public CellState getCellStateToRemember(int row, int col) {
		if(this.gameState != GameState.INITIAL)
		{
			throw new IllegalStateException();
		}else
		{
			if(row < 0 || col < 0 || row >= this.height || col >= this.width)
			{
				throw new IllegalArgumentException();
			}else
			{
				return bombs[row][col];
			}
		}
	}

	@Override
	public CellState getRememberedCellState(int row, int col) {
		if(this.gameState != GameState.SELECTION && this.gameState != GameState.END)
		{
			throw new IllegalStateException();
		}else
		{
			if(row < 0 || col < 0 || row >= this.height || col >= this.width)
			{
				throw new IllegalArgumentException();
			}else
			{
				return points[row][col];
			}
		}
	}

	@Override
	public void select(int row, int col) {
		if(this.gameState != GameState.SELECTION)
		{
			throw new IllegalStateException();
		}else
		{	if(row < 0 || col < 0 || row >= this.height || col >= this.width)
			{
				throw new IllegalArgumentException();
			}else
			{
				points[row][col] = CellState.PATH;
				aktR = row;
				aktC = col;
				checkStatus();
			}
		}
	}
	
	private void checkStatus()
	{
		int p1_h = -1;
		int p1_w = -1;
		int p2_h = -1;
		int p2_w = -1;
		
		for(int i = 0; i<this.height;i++)
		{
			for(int j = 0; j<this.width;j++)
			{
				if(points[i][j] == CellState.POINT)
				{
					if(p1_h == -1)
					{
						p1_h = i;
						p1_w = j;
					}else
					{
						p2_h = i;
						p2_w = j;
						break;
					}
				}
			}
		}
		
		boolean endP1 = false;
		boolean endP2 = false;
		if(p1_h > 0)
		{
			if(points[p1_h-1][p1_w] == CellState.PATH)
			{
				endP1 = true;
			}
		}
		if(p1_w > 0)
		{
			if(points[p1_h][p1_w-1] == CellState.PATH)
			{
				endP1 = true;
			}
		}
		if(p1_h < this.height-1)
		{
			if(points[p1_h+1][p1_w] == CellState.PATH)
			{
				endP1 = true;
			}
		}
		if(p1_w < this.width-1)
		{
			if(points[p1_h][p1_w+1] == CellState.PATH)
			{
				endP1 = true;
			}
		}
		
		if(p2_h > 0)
		{
			if(points[p2_h-1][p2_w] == CellState.PATH)
			{
				endP2 = true;
			}
		}
		if(p2_w > 0)
		{
			if(points[p2_h][p2_w-1] == CellState.PATH)
			{
				endP2 = true;
			}
		}
		if(p2_h < this.height-1)
		{
			if(points[p2_h+1][p2_w] == CellState.PATH)
			{
				endP2 = true;
			}
		}
		if(p2_w < this.width-1)
		{
			if(points[p2_h][p2_w+1] == CellState.PATH)
			{
				endP2 = true;
			}
		}
		
		if(endP1 == true && endP2 == true)
		{
			this.gameState = GameState.END;
			
			for(int i = 0; i<this.height;i++)
			{
				for(int j = 0; j<this.width;j++)
				{
					if(points[i][j] == CellState.PATH)
					{
						if(bombs[i][j] == CellState.BOMB)
						{
							points[i][j] = CellState.WRONG;
						}else
						{
							points[i][j] = CellState.CORRECT;
						}
					}
				}
			}
			
		}
	}

	@Override
	public void timeToRemember(int millis) {
		if(this.gameState != GameState.INITIAL)
		{
			throw new IllegalStateException();
		}else
		{
			try {
				Thread.sleep(millis);
				this.gameState = GameState.SELECTION;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean isValidMove(int row, int col) {
		if(this.gameState != GameState.SELECTION)
		{
			throw new IllegalStateException();
		}else
		{
			boolean hasPath = true;
			
			if(aktR == -1 && aktC == -1)
			{
				hasPath = false;
			}
			if(hasPath == true)
			{
				if(row > 0)
				{
					if(aktR == row-1  && aktC == col)
					{
						return true;
					}
				}
				if(col > 0)
				{
					if(aktR == row && aktC == col-1)
					{
						return true;
					}
				}
				if(row < this.height-1)
				{
					if(aktR == row+1 && aktC == col)
					{
						return true;
					}
				}
				if(col < this.width-1)
				{
					if(aktR == row && aktC == col+1)
					{
						return true;
					}
				}
			}else
			{
				if(row > 0)
				{
					if(points[row-1][col] == CellState.POINT)
					{
						return true;
					}
				}
				if(col > 0)
				{
					if(points[row][col-1] == CellState.POINT)
					{
						return true;
					}
				}
				if(row < this.height-1)
				{
					if(points[row+1][col] == CellState.POINT)
					{
						return true;
					}
				}
				if(col < this.width-1)
				{
					if(points[row][col+1] == CellState.POINT)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
}