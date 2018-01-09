package remembrain.model;

/**
 * Definition of a magic marbles game field.
 */
public interface Field {
	/**
	 * Width of the field, i.e. the number of columns.
	 * 
	 * @return The width of the field.
	 */
	public int getWidth();

	/**
	 * Height of the field, i.e. the number of rows.
	 * 
	 * @return The width of the field.
	 */
	public int getHeight();

	/**
	 * The overall state of the game.
	 * 
	 * @return The game state.
	 */
	public GameState getGameState();

	/**
	 * The final game state - win or lose. May only be called at the end of the game.
	 * 
	 * @return The final game state.
	 */
	public boolean getGameResult();
	
	/**
	 * The state of an individual field. 
	 * 
	 * @param col
	 *            The column of the field to query.
	 * @param row
	 *            The row of the field to query.
	 * @return The state of the specified field.
	 */
	CellState getCellStateToRemember(int row, int col);

	/**
	 * The state of an individual field. 
	 * 
	 * @param col
	 *            The column of the field to query.
	 * @param row
	 *            The row of the field to query.
	 * @return The state of the specified field.
	 */
	CellState getRememberedCellState(int row, int col);

	/**
	 * Selects the specified field. 
	 * 
	 * @param col
	 *            The column of the field to select.
	 * @param row
	 *            The row of the field to select.
	 */
	public void select(int row, int col);

	/**
	 * Specifies the time that the user gets to remember the field. 
	 * 
	 * @param millis
	 *            Time to remember in milliseconds.
	 */
	public void timeToRemember(int millis);

	/**
	 * Calculates whether the intended move is a valid one.
	 * 
	 * @param col
	 *            The column of the field to select.
	 * @param row
	 *            The row of the field to select.
	 */
	public boolean isValidMove(int row, int col);

}
