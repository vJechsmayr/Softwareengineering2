package remembrain.model;

/**
 * The different possible states of a cell.
 */
public enum CellState {

	/** The cell is occupied with a bomb. */
	BOMB{
		public String toString(){
			return "x";
		}
	},	
	/** The cell is empty. */
	EMPTY{
		public String toString(){
			return ".";
		}
	},
	/** The cell contains a point to connect. */
	POINT{
		public String toString(){
			return "p";
		}
	},
	/** The cell contains a part of the remembered path. */
	PATH{
		public String toString(){
			return "-";
		}
	},
	/** The cell has been correctly remembered. */
	CORRECT{
		public String toString(){
			return "c";
		}
	},
	/** The cell has been incorrectly remembered, i.e., a bomb has been hit. */
	WRONG{
		public String toString(){
			return "w";
		}
	},
}


