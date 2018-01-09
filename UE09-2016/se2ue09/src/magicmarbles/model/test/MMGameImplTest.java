package magicmarbles.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import magicmarbles.model.MMException;
import magicmarbles.model.MMFieldState;
import magicmarbles.model.MMGame;
import magicmarbles.model.MMGameImpl;
import magicmarbles.model.MMState;

public class MMGameImplTest {

	private MMFieldState gameField[][];

	@Before
	public void setUp() throws Exception {

		gameField = new MMFieldState[5][4];
		gameField[0][0] = MMFieldState.BLUE;
		gameField[0][1] = MMFieldState.GREEN;
		gameField[0][2] = MMFieldState.GREEN;
		gameField[0][3] = MMFieldState.RED;

		gameField[1][0] = MMFieldState.BLUE;
		gameField[1][1] = MMFieldState.GREEN;
		gameField[1][2] = MMFieldState.RED;
		gameField[1][3] = MMFieldState.BLUE;

		gameField[2][0] = MMFieldState.GREEN;
		gameField[2][1] = MMFieldState.GREEN;
		gameField[2][2] = MMFieldState.GREEN;
		gameField[2][3] = MMFieldState.GREEN;

		gameField[3][0] = MMFieldState.RED;
		gameField[3][1] = MMFieldState.RED;
		gameField[3][2] = MMFieldState.RED;
		gameField[3][3] = MMFieldState.RED;

		gameField[4][0] = MMFieldState.GREEN;
		gameField[4][1] = MMFieldState.RED;
		gameField[4][2] = MMFieldState.RED;
		gameField[4][3] = MMFieldState.RED;

	}

	@Test
	public void testValidGame() {

		MMGameImpl game = new MMGameImpl(gameField);

		try {
			game.select(4, 3);
		} catch (MMException e) {
			fail();
		}

		int i = 0;
		int j = 0;
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));

		i++;
		j = 0;
		assertEquals(MMFieldState.BLUE, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));

		i++;
		j = 0;
		assertEquals(MMFieldState.BLUE, game.getFieldState(i, j++));
		assertEquals(MMFieldState.GREEN, game.getFieldState(i, j++));
		assertEquals(MMFieldState.GREEN, game.getFieldState(i, j++));
		assertEquals(MMFieldState.RED, game.getFieldState(i, j++));

		i++;
		j = 0;
		assertEquals(MMFieldState.GREEN, game.getFieldState(i, j++));
		assertEquals(MMFieldState.GREEN, game.getFieldState(i, j++));
		assertEquals(MMFieldState.RED, game.getFieldState(i, j++));
		assertEquals(MMFieldState.BLUE, game.getFieldState(i, j++));

		i++;
		j = 0;
		assertEquals(MMFieldState.GREEN, game.getFieldState(i, j++));
		assertEquals(MMFieldState.GREEN, game.getFieldState(i, j++));
		assertEquals(MMFieldState.GREEN, game.getFieldState(i, j++));
		assertEquals(MMFieldState.GREEN, game.getFieldState(i, j++));

		assertEquals(49, game.getGamePoints());

		assertEquals(MMState.RUNNING, game.getGameState());

		try {
			game.select(4, 2);
		} catch (MMException e) {
			fail();
		}

		i = 0;
		j = 0;
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));

		i++;
		j = 0;
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));

		i++;
		j = 0;
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));

		i++;
		j = 0;
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.BLUE, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.RED, game.getFieldState(i, j++));

		i++;
		j = 0;
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.BLUE, game.getFieldState(i, j++));
		assertEquals(MMFieldState.RED, game.getFieldState(i, j++));
		assertEquals(MMFieldState.BLUE, game.getFieldState(i, j++));

		assertEquals(113, game.getGamePoints());

		assertEquals(MMState.RUNNING, game.getGameState());

		try {
			game.select(4, 1);
		} catch (MMException e) {
			fail();
		}

		i = 0;
		j = 0;
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));

		i++;
		j = 0;
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));

		i++;
		j = 0;
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));

		i++;
		j = 0;
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.RED, game.getFieldState(i, j++));

		i++;
		j = 0;
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.EMPTY, game.getFieldState(i, j++));
		assertEquals(MMFieldState.RED, game.getFieldState(i, j++));
		assertEquals(MMFieldState.BLUE, game.getFieldState(i, j++));

		assertEquals(87, game.getGamePoints());

		assertEquals(MMState.END, game.getGameState());

	}

	@Test
	public void testInvalidGame() {

		MMGameImpl game = new MMGameImpl(gameField);

		try {
			game.select(10, 3);
			fail();
		} catch (MMException e) {

		}

		try {
			game.select(4, 3);
		} catch (MMException e) {
			fail();
		}

		try {
			game.select(0, 0);
			fail();
		} catch (MMException e) {
		}
	}

}
