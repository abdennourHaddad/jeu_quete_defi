package test;

import static org.junit.Assert.*;

import org.junit.Test;

import src.mazegame.entity.player.Player;
import src.mazegame.item.*;

public class ItemTest {

    @Test
    public void testParcheminName() {
        Parchemin parchemin = new Parchemin();
        assertEquals(parchemin.getTypeName(), "Parchemin");
    }

    @Test
    public void testJewelName() {
        Jewel jewel = new Jewel();
        assertEquals(jewel.getTypeName(), "Joyau");
    }

    @Test
    public void testUseJewel() {
        Jewel jewel = new Jewel();
        Player player = new Player(null);
        assertEquals(0, player.getNbGold()); // initialement le joueur n'a pas de pièces
        jewel.use(player);
        assertEquals(10, player.getNbGold()); // il a maintenant 10 pièces
        assertFalse(player.getInventory().itemIsPresent(jewel)); // le joyau disparait de l'inventaire
    }

    // ---To allow test's execution ----------------------
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(ItemTest.class);
    }

}
