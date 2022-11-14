package src.mazegame.cell;

import java.util.List;

import src.mazegame.entity.player.Player;
import src.mazegame.item.Item;
import src.mazegame.maze.Maze;
import src.mazegame.entity.*;
import src.mazegame.entity.Character;

/**
* Interface who implements all types of cell of the game
*/
public interface Cell {

    /**
     * Get the state of the North wall of this cell
     * @return true if cell have a wall at the north face
     */
    public boolean getN_Wall();

    /**
     * Get the state of the East wall of this cell
     * @return true if cell have a wall at the east face
     */
    public boolean getS_Wall();

    /**
     * Get the state of the South wall of this cell
     * @return true if cell have a wall at the south face
     */
    public boolean getE_Wall();

    /**
     * Get the state of the West wall of this cell
     * @return true if cell have a wall at the west face
     */
    public boolean getW_Wall();

    /**
     * Destroys cell walls
     * @param cell a cell
     * @param direction a direction for destroy the wall
     */
    public void destroy(Cell cell, String direction);
    
    /**
     * Returns the coordinates of the abscesses of this cell
     * @return abscissa coordinate
     */
    public int getX();

    /**
     * returns the ordinate coordinate of this cell
     * @return ordinate coordinate
     */
    public int getY();

    /**
     * Return the actual maze of the cell
     * @return The maze
     */
    public Maze getMaze();

    /**
     * Add a player in the cell
     * @param player to add
     */
    public void addPlayer(Player player);

    /**
     * Remove a player in the cell
     * @param player the character to remove
     * @throws UnknownPlayerException if the character is not present in the cell
     */
    public void removePlayer(Player player) throws UnknownPlayerException;
    
    /**
     * @param player A player
     * @return true if the player is in the cell, false otherwise
     */
    public boolean containsPlayer(Player player);
    
    /**
     * @param c character
     * @return true if the character is in the cell, false otherwise
     */
    public boolean containsCharacter(Character c);
    
    /**
     * Return a list of all players in the cell
     * @return List of player in the cell
     */
    public List<Player> getPlayers();
    
    /**
     * return the number of characters in the cell
     * @return the number of characters in the cell
     */
    public int numberOfPlayers();
    
    /**
     * 
     * @param e_wall boolean
     */
    public void setE_Wall(boolean e_wall);

    /**
     * 
     * @param w_wall boolean
     */
    public void setW_Wall(boolean w_wall);

    /**
     * 
     * @param s_wall boolean
     */
    public void setS_Wall(boolean s_wall);

    /**
     * 
     * @param n_wall boolean
     */
    public void setN_Wall(boolean n_wall);

    /**
     * Remove a character from the cell
     * @param c Character 
     * @throws UnknownCharacterException if the character is not present in the cell
     */
    public void removeCharacter(Character c) throws UnknownCharacterException;


    /**
     * Add a character to the cell
     * @param character the character
     */
    public void addCharacter(Character character);

    /**
     * Get all characters of this cell actually
     * @return A list of all characters actually in the cell
     */
    public List<Character> getCharacters();

    /**
     * Cell can contain Items when the game was started.
     * Use this method for access to the items.
     * @return The list of items in the cell
     */
    public List<Item> getItems();
    
    /**
     * Add a item in the cell
     * @param item to add
     */
    public void addItem(Item item);
    
    /**
     * Remove an item from the cell
     * @param item Character 
     */
    public void removeItem(Item item);
    
    /**
     * return the number of items in the cell
     * @return the number of items in the cell
     */
    public int numberOfItems(); 
    
     /**
     * @return boolean true if the current cell is the final case, false otherwise
     */
    public boolean isFinalCell();
}
