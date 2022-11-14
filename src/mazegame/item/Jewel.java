package src.mazegame.item;

import src.mazegame.entity.player.*;

public class Jewel extends Item {

	private final static String typeName = "Joyau";
	private static final int GOLD = 10;

	public Jewel() {
		super();
	}

	
	public void use(Player player) {
		player.addNbGold(GOLD);// un joyau vaut 10 pièces d'or
		System.out.println("Vous avez " + GOLD + " pièces d'or en plus dans votre bourse. Vous avez en tout "+ player.getNbGold() + " pièces.");
		player.getInventory().removeItem(this); // les joyaux qui ont été convertis "disparaissent" de l'inventaire et deviennent de l'or
	}

	/**
	 *
	 * @return The type name of the item
	 */
	public String getTypeName() {
		return typeName;
	}
}
