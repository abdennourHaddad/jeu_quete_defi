package test;

import org.junit.Test;
import src.mazegame.cell.BasicCell;
import src.mazegame.entity.UnknownCharacterException;
import src.mazegame.entity.UnknownPlayerException;
import src.mazegame.entity.character.Bishop;
import src.mazegame.entity.player.Player;
import src.mazegame.item.Jewel;
import src.mazegame.item.Parchemin;
import src.mazegame.maze.MazeType1;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testItemsAddedToInventory() {
		MazeType1 maze = new MazeType1(20, 20);
		BasicCell cell = new BasicCell(2, 4, maze, false);
		Player player = new Player("Patricia");
		Jewel j = new Jewel();
		Jewel k = new Jewel();
		player.getInventory().addItem(j);
		player.getInventory().addItem(k);
		assertTrue(player.getInventory().itemIsPresent(k) && player.getInventory().itemIsPresent(j));
    }
	
    // ---To allow test's execution ----------------------
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(PlayerTest.class);
    }

}