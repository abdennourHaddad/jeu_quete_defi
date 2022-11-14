package src.mazegame.item;

import src.mazegame.cell.Cell;
import src.mazegame.entity.player.*;

public class Parchemin extends Item {

	private final static String typeName = "Parchemin";

	public Parchemin() {
		super();
	}

	/**
	 * give the index to the player
	 */
	public void use(Player player) {
		int xInit = player.getCell().getX();
		int yInit = player.getCell().getY();
		int xFinal = player.getCell().getMaze().finalCell.getX();
		int yFinal = player.getCell().getMaze().finalCell.getY();
		int distance = Math.max(Math.abs(xFinal-xInit),Math.abs(yFinal-xFinal))+1; //+1 Car en informatique, on commence à calculer de 0
		System.out.println("La sortie se trouve dans un rayon de " + distance + " à partir du point actuel.");
	}

	/**
	 *
	 * @return The type name of the item
	 */
	public String getTypeName() {
		return typeName;
	}
}
