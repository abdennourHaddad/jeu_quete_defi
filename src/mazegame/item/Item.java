package src.mazegame.item;


import src.mazegame.entity.player.*;

/**
* Interface who implements all types of item in the game
*/
public abstract class Item {

	protected String typeName;
	public Item() {
	}

	/**
	 * return the name of the item
	 * @return name
	 */
	public String getTypeName() {
		return this.typeName;
	}
	
	/**
	 * use an item
	 */
	public abstract void use(Player player);

}
