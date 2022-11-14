package src.mazegame.cell;

import src.mazegame.entity.*;
import src.mazegame.entity.Character;
import src.mazegame.entity.player.Player;
import src.mazegame.item.Item;
import src.mazegame.maze.Maze;

import java.util.ArrayList;
import java.util.List;

public class BasicCell implements Cell {

    private final Maze maze;
    
	private final int x ;
	
	private final int y ;
	
    private boolean n_wall;
    
    private boolean s_wall;
    
    private boolean e_wall;
    
    private boolean w_wall;
    
    private List<Character> theCharacters;

    private List<Player> thePlayers;
    
    private List<Item> theItems;
    
    private final boolean finalCell;

    public BasicCell(int x, int y, Maze maze, boolean exit) {
    	this.x = x;
    	this.y = y;
    	this.n_wall = true;
    	this.s_wall = true;
    	this.e_wall = true;
    	this.w_wall = true;
    	this.finalCell = exit;
    	this.theCharacters = new ArrayList<>();
        this.thePlayers = new ArrayList<>();
        this.theItems = new ArrayList<>();
        this.maze = maze;
    }

    /**
     * Return the actual maze of the cell
     * @return the maze
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * return the coordinate x
     * @return x
     */
    public int getX() {
    	return this.x ;
    }
    
    /**
     * return the coordinate y
     * @return y
     */
    public int getY() {
    	return this.y ;
    }
    
    /**
     * return true if there is a north wall
     * @return n_wall
     */
    public boolean getN_Wall() {
        return this.n_wall;
    }
    
    /**
     * return true if there is a south wall
     * @return s_wall
     */
    public boolean getS_Wall() {
        return this.s_wall;
    }
    
    /**
     * return true if there is an east wall
     * @return e_wall
     */
    public boolean getE_Wall() {
        return this.e_wall;
    }
    
    /**
     * return true if there is a west wall
     * @return w_wall
     */
    public boolean getW_Wall() {
        return this.w_wall;
    }
    
    /**
     * sets the state of the north wall
     * @param n_wall boolean how represent the state of the wall
     */
    public void setN_Wall(boolean n_wall) {
    	this.n_wall = n_wall;
    }

    /**
     * sets the state of the south wall
     * @param s_wall boolean how represent the state of the wall
     */
    public void setS_Wall(boolean s_wall) {
    	this.s_wall = s_wall;
    }

    /**
     * sets the state of the east wall
     * @param e_wall boolean how represent the state of the wall
     */
    public void setE_Wall(boolean e_wall) {
    	this.e_wall = e_wall;
    }

    /**
     * sets the state of the west wall
     * @param w_wall boolean how represent the state of the wall
     */
    public void setW_Wall(boolean w_wall) {
    	this.w_wall = w_wall;
    }
    
    /**
     * for each cell, we decide randomly if we have to destroy the east wall or not.
	 * if yes, then we destroy the east wall.
	 * Otherwise, we consider the horizontal passage that has just been completed, formed by the current cell and all the cells to the left that have dug passages leading to it.
	 * We then randomly choose a cell along this passage and destroy its south wall.
     * @param cell a cell
     * @param direction a direction for destroy a wall
     */
    public void destroy(Cell cell, String direction) {
    	if (direction.equals("N")) {
    		this.setN_Wall(false) ;
    		cell.setS_Wall(false) ;
    	}
    	else if (direction.equals("S")) {
    		this.setS_Wall(false) ;
    		cell.setN_Wall(false) ;
    	}
    	else if (direction.equals("E")) {
    		this.setE_Wall(false) ;
    		cell.setW_Wall(false) ;
    	}
        else {
    		this.setW_Wall(false) ;
    		cell.setE_Wall(false) ;
    	}
    }
    
    /**
     * return the list of characters in the cell
     * @return the list of characters
     */
    public List<Character> getCharacters() {
        return this.theCharacters ;
    }


    /**
     * add a character in the cell
     * @param character to add
     */
    public void addCharacter(Character character) {
        this.theCharacters.add(character);
//        character.setCell(this);
    }
    
    /**
     * remove a character in the cell
     * @param c the character to remove
     * @throws UnknownCharacterException if the character is not present in the cell
     */
    public void removeCharacter(Character c) throws UnknownCharacterException {
        if (!this.theCharacters.contains(c))
            throw new UnknownCharacterException("Personnage inconnu");
        // else
        this.theCharacters.remove(c);
     }

    /**
     * Add a player in the cell
     * @param player to add
     */
    public void addPlayer(Player player) {
        this.thePlayers.add(player);
        player.setCell(this);
    }

    /**
     * Remove a player in the cell
     *
     * @param player the character to remove
     * @throws UnknownPlayerException if the character is not present in the cell
     */
    public void removePlayer(Player player) throws UnknownPlayerException {
        if (!this.thePlayers.contains(player)) {
            throw new UnknownPlayerException("Personnage inconnu");
        }
        else {
        	this.thePlayers.remove(player);
        }
    }
    
    /**
     * @param player A player
     * @return true if the player is in the cell, false otherwise
     */
    public boolean containsPlayer(Player player) {
    	return this.thePlayers.contains(player);
    }
    
    /**
     * @param c character
     * @return true if the character is in the cell, false otherwise
     */
    public boolean containsCharacter(Character c) {
    	return this.theCharacters.contains(c);
    }
    
    /**
     * Return a list of all players in the cell
     * @return List of player in the cell
     */
    public List<Player> getPlayers(){
        return this.thePlayers;
    }
    
    /**
     * return the number of characters in the cell
     * @return the number of characters in the cell
     */
    public int numberOfCharacters() {
    	return this.theCharacters.size() ;
    }
    
    /**
     * return the number of characters in the cell
     * @return the number of characters in the cell
     */
    public int numberOfPlayers() {
    	return this.thePlayers.size() ;
    }

    public List<Item> getItems() {
        return this.theItems;
    }
    
    /**
     * add an item in the cell
     * @param item to add
     */
    public void addItem(Item item) {
        this.theItems.add(item);
    }
    
    /**
     * Remove an item from the cell
     * @param item Character 
     */
    public void removeItem(Item item) {
    	this.theItems.remove(item);
    }
    
    /**
     * return the number of items in the cell
     * @return the number of items in the cell
     */
    public int numberOfItems() {
    	return this.theItems.size() ;
    }
    
    /**
     * 
     * @return boolean true if the current cell is the final case, false otherwise 
     */
    public boolean isFinalCell() {
    		return this.finalCell;
    }
    
    /**
     * description
     */
    public String toString() {
        return "Vous êtes dans la case ("+ this.getX()+ "," + this.getY() + ").";
     }
}
