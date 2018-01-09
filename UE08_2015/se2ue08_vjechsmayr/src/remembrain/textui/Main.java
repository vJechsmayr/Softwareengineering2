package remembrain.textui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import remembrain.model.CellState;
import remembrain.model.Field;
import remembrain.model.FieldImpl;
import remembrain.model.GameState;
import inout.In;
import inout.Out;

public class Main {
	
	final static int WIDTH = 5;
	final static int HEIGHT = 5;
	final static String FILE_NAME = "testData2.txt";
		
	public static void main(String[] args) throws IOException {
		
		CellState fieldToRemember[][] = new CellState[HEIGHT][WIDTH];	
		CellState fieldRemembered[][] = new CellState[HEIGHT][WIDTH];	
		
		/*new field is created with contents of file*/
		Field field = createField(fieldToRemember, fieldRemembered);
		
		/*field to remember is shown for a certain time frame according to field size*/
		printField(field, field.getGameState());
		//field.timeToRemember(field.getWidth()*field.getHeight()*120);
		field.timeToRemember(1000);

		/*'clear' console*/
		for(int i = 0; i<40000; i++){
			System.out.println("\n");
		}
		
		/*remember the fields*/
		while (field.getGameState() == GameState.SELECTION){
			printField(field, field.getGameState());
			doMove(field);
		}
		
		/*print evaluated field*/
		printField(field, field.getGameState());
		/*print result*/
		if(field.getGameResult()){
			System.out.println("Well Done - You Won!");
		}
		else{
			System.out.println("Sorry - Try Again!");
		}
	}
	
	private static Field createField(CellState fieldToRemember[][],	CellState fieldRemembered[][]) {
		readFile(FILE_NAME, fieldToRemember, fieldRemembered);
		return new FieldImpl(fieldToRemember,fieldRemembered);
	}
	
	private static void readFile(String fileName, CellState fieldToRemember[][], CellState fieldRemembered[][]){
		try {			
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			
			for(int row = 0; row < fieldToRemember.length; row++){
				for(int col = 0; col < fieldToRemember[0].length; col++){
					int ch = reader.read();
					fieldToRemember[row][col]=CellState.EMPTY;
					fieldRemembered[row][col]=CellState.EMPTY;
					
					if(ch != '0'){
						switch(ch){
						case 'x': //bomb
							fieldToRemember[row][col]=CellState.BOMB;
							break;
						case 'p':
							fieldRemembered[row][col]=CellState.POINT;
							break;
						}
					}					
				}
			}
 		    reader.close();
		}
		catch(Exception e){
			System.out.println(e.toString());
		} 
	}
	

	private static void doMove(Field field) {
		int row, col;
		
		do{
			Out.println("Please select a field to build the path in consecutive order");
			do {
				Out.print("Row: ");
				row = In.readInt();
				In.readLine();
			} while (row < 1 || row > field.getHeight());
			
			do {
				Out.print("Column: ");
				col = In.readInt();
				In.readLine();
			} while (col < 1 || col > field.getWidth());
			
		}while (!field.isValidMove(row-1, col-1));		
		field.select(row - 1, col - 1);
	}
	
	private static void printField(Field field, GameState gameState) {
		Out.println();
		Out.print("  ");
		for (int col = 0; col < field.getWidth(); col++) {
			Out.print("  ");
			Out.print((col + 1) / 10);
		}
		Out.println();

		Out.print("  ");
		for (int col = 0; col < field.getWidth(); col++) {
			Out.print("  ");
			Out.print((col + 1) % 10);
		}
		Out.println();

		for (int row = 0; row < field.getHeight(); row++) {
			Out.print((row + 1) / 10);
			Out.print((row + 1) % 10);

			for (int col = 0; col < field.getWidth(); col++) {
				Out.print("  ");
				if(gameState == GameState.INITIAL){//print field to remember
					Out.print(field.getCellStateToRemember(row, col).toString()); 
				}
				else{//print remembered field
					Out.print(field.getRememberedCellState(row, col).toString()); 
				}
			}
			Out.println();
		}
	}
}
