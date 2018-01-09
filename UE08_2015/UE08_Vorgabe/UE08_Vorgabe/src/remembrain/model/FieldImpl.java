package remembrain.model;

public class FieldImpl implements Field {

	//constructor that allows to establish a certain state to start from for testing purposes
	public FieldImpl(final CellState initialFieldToRemember[][], final CellState initalFieldRemembered[][]) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GameState getGameState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getGameResult() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CellState getCellStateToRemember(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CellState getRememberedCellState(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void select(int row, int col) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void timeToRemember(int millis) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidMove(int row, int col) {
		// TODO Auto-generated method stub
		return false;
	}
	



}
