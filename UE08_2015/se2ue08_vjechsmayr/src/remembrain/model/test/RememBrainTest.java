package remembrain.model.test;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import org.junit.Test;
import remembrain.model.CellState;
import remembrain.model.Field;
import remembrain.model.FieldImpl;
import remembrain.model.GameState;

public class RememBrainTest {
	
	@Test
	public void testFieldImpl()
	{
		try {
			CellState fieldToRemember[][] = new CellState[0][0];	
			CellState fieldRemembered[][] = new CellState[0][0];	
			
			createField(fieldToRemember, fieldRemembered);
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
	
	@Test
	public void testgetWidth()
	{
		CellState fieldToRemember[][] = new CellState[5][4];	
		CellState fieldRemembered[][] = new CellState[5][4];	
		
		Field field = createField(fieldToRemember, fieldRemembered);
		
		assertEquals(4, field.getWidth()); //Falsch bei zB 5
	}
	
	@Test
	public void testgetHeight()
	{
		CellState fieldToRemember[][] = new CellState[5][4];	
		CellState fieldRemembered[][] = new CellState[5][4];	
		
		Field field = createField(fieldToRemember, fieldRemembered);
		
		assertEquals(5, field.getHeight());
	}
	
	@Test
	public void testgetGameState()
	{
		CellState fieldToRemember[][] = new CellState[5][5];	
		CellState fieldRemembered[][] = new CellState[5][5];	
		
		Field field = createField(fieldToRemember, fieldRemembered);
		
		assertEquals(GameState.INITIAL, field.getGameState());
		field.timeToRemember(0);
		assertEquals(GameState.SELECTION, field.getGameState());
		field.select(3, 2);
		assertEquals(GameState.SELECTION, field.getGameState());
		field.select(4, 2);
		assertEquals(GameState.SELECTION, field.getGameState());
		field.select(4, 3);
		assertEquals(GameState.END, field.getGameState());
	}
	
	@Test
	public void testgetGameResultTrue()
	{
		CellState fieldToRemember[][] = new CellState[5][5];	
		CellState fieldRemembered[][] = new CellState[5][5];	
		
		Field field = createField(fieldToRemember, fieldRemembered);
		
		field.timeToRemember(0);
		field.select(3, 2);
		field.select(4, 2);
		field.select(4, 3);
		assertTrue(field.getGameResult());
	}
	
	@Test
	public void testgetGameResultFalse()
	{
		CellState fieldToRemember[][] = new CellState[5][5];	
		CellState fieldRemembered[][] = new CellState[5][5];	
		
		Field field = createField(fieldToRemember, fieldRemembered);
		
		field.timeToRemember(0);
		field.select(2, 3);
		field.select(2, 4);
		field.select(3, 4);
		assertFalse(field.getGameResult());
	}
	
	@Test
	public void testgetCellStateToRemember()
	{
		CellState fieldToRemember[][] = new CellState[5][5];	
		CellState fieldRemembered[][] = new CellState[5][5];	
		
		Field field = createField(fieldToRemember, fieldRemembered);
		
		assertEquals(CellState.EMPTY, field.getCellStateToRemember(1, 2));
		assertEquals(CellState.EMPTY, field.getCellStateToRemember(4, 4));
		
		assertEquals(CellState.BOMB, field.getCellStateToRemember(0, 0));
		assertEquals(CellState.BOMB, field.getCellStateToRemember(3, 1));
		
		field.timeToRemember(0);
		
		try
		{
			assertEquals(CellState.EMPTY, field.getCellStateToRemember(1, 2));
			fail();			
		}catch(Exception e)
		{
			assertTrue(e instanceof IllegalStateException);
		}
	}
	
	@Test
	public void testgetRememberedCellState()
	{
		CellState fieldToRemember[][] = new CellState[5][5];	
		CellState fieldRemembered[][] = new CellState[5][5];	
		
		Field field = createField(fieldToRemember, fieldRemembered);
		
		try
		{
			assertEquals(CellState.EMPTY, field.getRememberedCellState(1, 2));
			fail();			
		}catch(Exception e)
		{
			assertTrue(e instanceof IllegalStateException);
		}
		
		field.timeToRemember(0);
		
		field.select(2, 3);
		field.select(3, 3);

		assertEquals(CellState.PATH, field.getRememberedCellState(2, 3));
		assertEquals(CellState.PATH, field.getRememberedCellState(3, 3));
		
		assertEquals(CellState.POINT, field.getRememberedCellState(2, 2));
		assertEquals(CellState.POINT, field.getRememberedCellState(4, 4));
	}
	
	@Test
	public void testSelect()
	{
		CellState fieldToRemember[][] = new CellState[5][5];	
		CellState fieldRemembered[][] = new CellState[5][5];	
		
		Field field = createField(fieldToRemember, fieldRemembered);
		
		try
		{
			field.select(1, 2);
			fail();			
		}catch(Exception e)
		{
			assertTrue(e instanceof IllegalStateException);
		}
		
		field.timeToRemember(0);
		
		field.select(1, 2);
		assertEquals(CellState.PATH, fieldRemembered[1][2]);
	}
	
	@Test
	public void testTimeToRemember()
	{
		CellState fieldToRemember[][] = new CellState[5][5];	
		CellState fieldRemembered[][] = new CellState[5][5];	
		
		Field field = createField(fieldToRemember, fieldRemembered);
		
		assertEquals(GameState.INITIAL, field.getGameState());
		field.timeToRemember(3000);
		assertEquals(GameState.SELECTION, field.getGameState());
	}
	
	@Test
	public void testisValidMove()
	{
		CellState fieldToRemember[][] = new CellState[5][5];	
		CellState fieldRemembered[][] = new CellState[5][5];	
		
		Field field = createField(fieldToRemember, fieldRemembered);
		
		try
		{
			field.isValidMove(1,2);
			fail();			
		}catch(Exception e)
		{
			assertTrue(e instanceof IllegalStateException);
		}
		
		field.timeToRemember(0);
		
		assertTrue(field.isValidMove(1, 2));
		assertFalse(field.isValidMove(0, 0));
		
		field.select(1, 2);
		assertTrue(field.isValidMove(0, 2));
		assertFalse(field.isValidMove(1, 2));
		assertFalse(field.isValidMove(2, 3));
		field.select(0,2);
		assertTrue(field.isValidMove(0,3));
		field.select(0, 3);
		assertTrue(field.isValidMove(0,4));
		field.select(0, 4);
		assertTrue(field.isValidMove(1, 4));
		field.select(1,4);
		assertTrue(field.isValidMove(2,4));
		field.select(2, 4);
		assertTrue(field.isValidMove(3,4));
		field.select(3,4);
		
		try
		{
			field.isValidMove(4,4);
			fail();			
		}catch(Exception e)
		{
			assertTrue(e instanceof IllegalStateException);
		}
	}
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	private static Field createField(CellState fieldToRemember[][],	CellState fieldRemembered[][]) {
		readFile("testData2.txt", fieldToRemember, fieldRemembered);
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
}
