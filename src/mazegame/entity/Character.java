package src.mazegame.entity;

import src.mazegame.entity.player.Player;

public interface Character {

	/**
	 * The character interact with the player
	 * @param player The player
	 */
	public void interaction(Player player);

	/**
	 * @return The name of the work for this character
	 */
	public String getWorkName();

	/**
	 *
	 * @return The firstname of the player
	 */
	public String getName();
	
}
