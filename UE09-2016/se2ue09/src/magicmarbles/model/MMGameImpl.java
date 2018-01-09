package magicmarbles.model;

/**
 * Implementation of the magic marbles game
 */
public class MMGameImpl implements MMGame {

	private MMFieldState field[][];
	private MMFieldState tempState;
	private int cols;
	private int rows;
	private int pts;
	private MMState state;

	public MMGameImpl(MMFieldState f[][]) {
		this.field = f;

		this.cols = this.field[0].length;
		this.rows = this.field.length;

		state = MMState.RUNNING;

		if (!movesPossible()) {
			int negative = countMarblesLeft() * 10;
			pts = pts - negative;
			state = MMState.END;
		}
	}

	/**
	 * Constructor
	 * 
	 * @param width
	 *            the width of the game board
	 * @param height
	 *            the height of the game board
	 */
	public MMGameImpl(int width, int height) {
		// w >= 3 && < 100
		// h >= 3 && < 100 sinnvoll?

		cols = width;
		rows = height;
		state = MMState.RUNNING;

		this.field = new MMFieldState[rows][cols];
		double random = Math.random();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (random < 0.3) {
					field[i][j] = MMFieldState.RED;
				} else if (random < 0.6) {
					field[i][j] = MMFieldState.GREEN;
				} else {
					field[i][j] = MMFieldState.BLUE;
				}
				random = Math.random();
			}
		}

		if (!movesPossible()) {
			int negative = countMarblesLeft() * 10;
			pts = pts - negative;
			state = MMState.END;
		}
	}

	private boolean movesPossible() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (hasNext(i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean hasNext(int row, int col) {
		MMFieldState c = getFieldState(row, col);
		if (c == MMFieldState.EMPTY) {
			return false;
		}
		return ((getFieldState(row, col - 1) == c) || (getFieldState(row - 1, col) == c)
				|| (getFieldState(row, col + 1) == c) || (getFieldState(row + 1, col) == c));
	}

	@Override
	public int getWidth() {
		return this.cols;
	}

	@Override
	public int getHeight() {
		return this.rows;
	}

	@Override
	public MMState getGameState() {
		return this.state;
	}

	@Override
	public int getGamePoints() {
		return this.pts;
	}

	@Override
	public MMFieldState getFieldState(int row, int col) {
		try {
			return field[row][col];
		} catch (ArrayIndexOutOfBoundsException ex) {
			return null;
		}
	}

	@Override
	public void select(int row, int col) throws MMException {
		try {
			tempState = field[row][col];
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new MMException();
		}

		if (tempState == MMFieldState.EMPTY) {
			throw new MMException();
		}

		if (getFieldState(row, col - 1) == tempState || getFieldState(row - 1, col) == tempState
				|| getFieldState(row, col + 1) == tempState || getFieldState(row + 1, col) == tempState) {
			pts += Math.pow(connectMarbles(row, col), 2);
		}

		mixItUp();

		if (!movesPossible()) {
			int negative = countMarblesLeft() * 10;
			pts = pts - negative;
			state = MMState.END;
		}
	}

	private void mixItUp() {
		switchRows();
		switchCols();
	}

	private void switchCols() {
		for (int x = cols - 1; x >= 0; x--) {
			if (isEmptyCol(x)) {
				for (int j = x - 1; j >= 0; j--) {
					for (int i = 0; i < rows; i++) {
						field[i][j + 1] = field[i][j];
						field[i][j] = MMFieldState.EMPTY;
					}
				}
			}
		}

	}

	private void switchRows() {
		for (int i = cols - 1; i >= 0; i--) {
			for (int x = rows - 1; x >= 0; x--) {
				if (field[x][i] == MMFieldState.EMPTY) {
					for (int j = x - 1; j >= 0; j--) {
						field[j + 1][i] = field[j][i];
						field[j][i] = MMFieldState.EMPTY;
					}
					if (!isEmptyColAbove(i, x)) {
						x++;
					}
				}
			}
		}
	}

	private boolean isEmptyCol(int col) {
		for (int i = 0; i < rows; i++) {
			if (field[i][col] != MMFieldState.EMPTY) {
				return false;
			}
		}
		return true;
	}

	private boolean isEmptyColAbove(int col, int row) {
		for (int i = row - 1; i >= 0; i--) {
			if (field[i][col] != MMFieldState.EMPTY) {
				return false;
			}
		}
		return true;
	}

	private int connectMarbles(int row, int col) {
		MMFieldState src = getFieldState(row, col);

		if (src == tempState) {
			field[row][col] = MMFieldState.EMPTY;
			return connectMarbles(row, col - 1) + connectMarbles(row - 1, col) + connectMarbles(row, col + 1)
					+ connectMarbles(row + 1, col) + 1;
		}
		return 0;
	}

	private int countMarblesLeft() {
		int x = 0;

		for (int i = rows - 1; i >= 0; i--) {
			for (int j = cols - 1; j >= 0; j--) {
				if (field[i][j] != MMFieldState.EMPTY) {
					x++;
				}
			}
		}

		return x;
	}
}
