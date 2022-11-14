package src.mazegame.entity.player;

import src.mazegame.cell.Cell;
import src.mazegame.entity.*;
import src.mazegame.entity.Character;
import src.mazegame.item.Item;

import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
* A player was in a cell of a maze
*/
public class Player{
    private final String name;
    private Cell cell;
    private final Inventory inventory;
    int nbGold;

    /**
     * Create a player
     * @param name name of the player
     */
    public Player(String name){
        this.name = name;
        this.inventory = new Inventory(this);
        this.nbGold = 0;
    }

    /**
     * Get inventory of player
     * @return an inventory
     */
    public Inventory getInventory(){
        return this.inventory;
    }

    /**
     * @return the name of the player
     */
    public String getName(){
        return this.name;
    }

    /**
     * Set a cell for the player
     * @param cell a cell
     */
    public void setCell(Cell cell){
        this.cell = cell;
    }

    /**
     * @return the cell of the player
     */
    public Cell getCell() {
        return this.cell;
    }
    
    /**
	 * returns  the number of gold coins
	 * @return Amount of gold in this wallet
	 */
	public int getNbGold() {
		return this.nbGold;
	}
	
	/**
	 * add the number of gold coins
	 */
	public void addNbGold(int n) {
		this.nbGold += n ;
	}
	
	/**
	 * remove the number of gold coins
	 */
	public void removeNbGold(int n) {
		this.nbGold -= n ;
	}
	
    /**
     * Move the player to a new cell if it's possible
     * @param direction The direction of the move (N S E W)
     * @throws wrongValueException If the direction value was not correct
     */
    public void move(String direction) throws wrongValueException {
        try {
            switch (direction) {
                case "N":
                    if (!cell.getN_Wall()) { // Verify if there is no wall
                        cell.removePlayer(this);
                        cell.getMaze().getCell(cell.getX(), cell.getY() - 1).addPlayer(this);
                    } else {
                        runThroughWall();
                    }
                    break;
                case "E":
                    if (!cell.getE_Wall()) {
                        cell.removePlayer(this);
                        cell.getMaze().getCell(cell.getX() + 1, cell.getY()).addPlayer(this);
                    } else {
                        runThroughWall();
                    }
                    break;
                case "S":
                    if (!cell.getS_Wall()) {
                        cell.removePlayer(this);
                        cell.getMaze().getCell(cell.getX(), cell.getY() + 1).addPlayer(this);
                    } else {
                        runThroughWall();
                    }
                    break;
                case "W":
                    if (!cell.getW_Wall()) {
                        cell.removePlayer(this);
                        cell.getMaze().getCell(cell.getX() - 1, cell.getY()).addPlayer(this);
                    } else {
                        runThroughWall();
                    }
                    break;
                default:
                    throw new wrongValueException(direction + " n'est pas une valeur valide.");
            }
        } catch (UnknownPlayerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Message when player try to move through a wall
     */
    private void runThroughWall(){
        System.out.println("Vous courrez vers le mur... BOOM ! Vous gagnez une bosse.");
    }

    private void talkTo(Character character){
        character.interaction(this);
    }

    public void lookAround(){
        int indexItem = 0;
        // Items
        System.out.println("A cet emplacement :\n- Item :");
        List<Item> theItems;
        theItems = getCell().getItems();
        for(Item item : theItems){
            System.out.println("  " + indexItem + ". " + item.getTypeName());
            indexItem++;
        }

        int indexCharacter = indexItem;
        // Characters
        System.out.println("\n- Personnages :");
        List<Character> theCharacters;
        theCharacters = getCell().getCharacters();
        for(Character character : theCharacters){
            System.out.println("  " + indexCharacter + ". " + character.getName());
            indexCharacter++;
        }
        System.out.println();//Line break for the text at the end

        // Wait for a choice
        Scanner scanner = new Scanner(System.in);
        int choose = -1;
        while (choose == -1) {
            System.out.println("\nRamasser/Parler : NOMBRE   |   Quitter le sous-menu : Q");
            String choice = scanner.nextLine();
            String regex = "[0-9]+|Q";
            if (choice.matches(regex)) {
                if (!choice.equals("Q")){
                    if ((parseInt(choice) >= 0)&&(parseInt(choice) <= indexCharacter)) {
                        choose = parseInt(choice);
                    }
                    else{
                        System.out.println("Veuillez choisir un nombre parmis les index disponibles");
                    }
                }
                else {
                    choose = -2;
                }
            }
            else{
                System.out.println("Votre proposition ne respecte pas la forme NOMBRE ou Q");
            }
        }
        if (indexItem != 0) {// Si il y a des items présents dans la case
            if ((choose >= 0) && (choose <= indexItem)) {// On depend on choice by the user, action is différent
                pickUp(theItems.get(choose));// Add to the inventory of the player
            } else if ((choose > indexItem ) && (choose <= indexCharacter)) {
                theCharacters.get((choose-indexItem+1)).interaction(this);
            }
        }
        else{// If there are no items in cell but character(s)
            if ((choose >= 0) && (choose <= indexCharacter)) {
                theCharacters.get(choose).interaction(this);
            }
        }
    }

    public void lookInventory(){
        int ChoixItemString = 0;// Affichage
        System.out.println("Liste des items :\n");
        List<Item> theItems = this.inventory.getItems();
        for (Item item : theItems) {// Text for the choice
            System.out.println("  " + ChoixItemString + ". " + item.getTypeName());
            ChoixItemString++;
        }

        int choose = -1; // Choice
        Scanner scanner = new Scanner(System.in);
        while (choose == -1) {
            System.out.println("\nUtiliser : NOMBRE   |   Quitter le sous-menu : Q");
            String choice = scanner.nextLine();
            String regex = "[0-9]+|Q";
            if (choice.matches(regex)) {
                if (!choice.equals("Q")) {
                    if ((parseInt(choice) >= 0) && (parseInt(choice) <= (theItems.size()-1))) {
                        choose = parseInt(choice);
                    } else {
                        System.out.println("Veuillez choisir un nombre parmis les index disponibles");
                    }
                } else {
                    choose = -2;// Quitter
                }
            } else {
                System.out.println("Votre proposition ne respecte pas la forme NOMBRE ou Q");
            }
        }
        if (choose != -2){
            theItems.get(choose).use(this);
        }
    }

    /**
     * PickUp item stocked in the cell and place it in the inventory of player
     * @param item item to be placed in the inventory of player
     */
    public void pickUp(Item item){
        System.out.println(this.name + " ramasse le " + item.getTypeName().toLowerCase() + " et le place dans son inventaire.");
        this.inventory.addItem(item);
        this.cell.removeItem(item); // lorsqu'il ramasse, l'objet est retiré de la cellule
    }
}
