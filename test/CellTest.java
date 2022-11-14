package test;

import org.junit.*;
import static org.junit.Assert.*;

import src.mazegame.cell.*;
import src.mazegame.maze.*;
import src.mazegame.entity.*;
import src.mazegame.entity.character.Bishop;
import src.mazegame.entity.player.*;
import src.mazegame.item.*;

public class CellTest {	

    @Test
    public void testCellIsCorrectAtCreation() {
    	MazeType1 maze = new MazeType1(20, 20);
    	BasicCell cell = new BasicCell(2, 4, maze, false);
    	assertSame(maze, cell.getMaze());
        assertEquals(2, cell.getX());
        assertEquals(4, cell.getY());
        assertFalse(cell.isFinalCell());
    }

	@Test
	public void testDestroy() {
		MazeType1 maze = new MazeType1(20, 20);
    	BasicCell cell = new BasicCell(2, 4, maze, false);
    	assertTrue(cell.getN_Wall());
    	assertTrue(cell.getS_Wall());
		cell.destroy(cell, "N");
		assertFalse(cell.getN_Wall());
		assertFalse(cell.getS_Wall());
	}

	@Test
	public void testAddPlayer() {
		MazeType1 maze = new MazeType1(20, 20);
    	BasicCell cell = new BasicCell(2, 4, maze, false);
    	Player player = new Player("Patricia");
    	assertEquals(0, cell.numberOfPlayers());
    	cell.addPlayer(player);
    	assertEquals(1, cell.numberOfPlayers());
	}
	
	@Test
	public void testRemovePlayer() throws UnknownPlayerException {
		MazeType1 maze = new MazeType1(20, 20);
    	BasicCell cell = new BasicCell(2, 4, maze, false);
    	Player player = new Player("Patricia");
    	cell.addPlayer(player);
    	assertTrue(cell.containsPlayer(player));
    	cell.removePlayer(player);
    	assertFalse(cell.containsPlayer(player)); 	
	}
	
	@Test
	public void testNumberOfPlayers() {
		MazeType1 maze = new MazeType1(20, 20);
    	BasicCell cell = new BasicCell(2, 4, maze, false);
    	Player player1 = new Player("Patricia");
    	cell.addPlayer(player1);
    	assertEquals(1, cell.numberOfPlayers());
    	Player player2 = new Player("Samuel");
    	cell.addPlayer(player2);
    	assertEquals(2, cell.numberOfPlayers());
	}
	
	@Test
	public void testAddItem() {
		MazeType1 maze = new MazeType1(20, 20);
    	BasicCell cell = new BasicCell(2, 4, maze, false);
    	Parchemin item = new Parchemin();
    	assertEquals(0, cell.numberOfItems());
    	cell.addItem(item);
    	assertEquals(1, cell.numberOfItems());
	}
	
	@Test
	public void testNumberOfItems() {
		MazeType1 maze = new MazeType1(20, 20);
    	BasicCell cell = new BasicCell(2, 4, maze, false);
    	Parchemin p = new Parchemin();
    	cell.addItem(p);
    	assertEquals(1, cell.numberOfItems());
    	Jewel j = new Jewel();
    	cell.addItem(j);
    	assertEquals(2, cell.numberOfItems());
	}
	
	@Test
	public void testAddCharacter() {
		MazeType1 maze = new MazeType1(20, 20);
    	BasicCell cell = new BasicCell(2, 4, maze, false);
    	Bishop b = new Bishop(null);
    	assertEquals(0, cell.numberOfCharacters());
    	cell.addCharacter(b);
    	assertEquals(1, cell.numberOfCharacters());
	}
	
	@Test
	public void testRemoveCharacter() throws UnknownCharacterException {
		MazeType1 maze = new MazeType1(20, 20);
    	BasicCell cell = new BasicCell(2, 4, maze, false);
    	Bishop b = new Bishop(null);
    	cell.addCharacter(b);
    	assertTrue(cell.containsCharacter(b));
    	cell.removeCharacter(b);
    	assertFalse(cell.containsCharacter(b)); 	
	}
	
    // ---To allow test's execution ----------------------
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(CellTest.class);
    }

}