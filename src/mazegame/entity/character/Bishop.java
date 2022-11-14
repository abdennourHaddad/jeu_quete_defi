package src.mazegame.entity.character;

import java.util.Random;

import src.mazegame.cell.Cell;
import src.mazegame.entity.Character;
import src.mazegame.entity.UnknownCharacterException;
import src.mazegame.entity.player.Player;
import src.mazegame.entity.wrongValueException;

/**
 * A bishop was in a cell of a maze
 */
public class Bishop implements Character {

    private final String workName = "Fou";
    /**
     * The name of the character
     */
    private String name;
    private Cell cell;

    /**
     * Creates a bishop
     *
     * @param name name of the bishop
     */
    public Bishop(String name) {
        this.name = name;
    }

    /**
     * @return the name of the Bishop
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set a cell to the Bishop
     *
     * @param cell a cell
     */
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    /**
     * @return the cell of the Bishop
     */
    public Cell getCell() {
        return this.cell;
    }

    public void move() throws wrongValueException, UnknownCharacterException {
        try {
            Direction[] allDirections = Direction.values();
            Direction randomDirection = randomDirection(allDirections);
            if (randomDirection.toString().equals(Direction.N.name())) {
                if (!cell.getN_Wall()) {
                    cell.removeCharacter(this);
                    cell.getMaze().getCell(cell.getX(), cell.getY() - 1).addCharacter(this);
                    this.cell = cell.getMaze().getCell(cell.getX(), cell.getY() - 1);
                }

            } else if (randomDirection.toString().equals(Direction.E.name())) {
                if (!cell.getE_Wall()) {
                    cell.removeCharacter(this);
                    cell.getMaze().getCell(cell.getX() + 1, cell.getY()).addCharacter(this);
                    this.cell = cell.getMaze().getCell(cell.getX() + 1, cell.getY());
                }

            } else if (randomDirection.toString().equals(Direction.S.name())) {
                if (!cell.getS_Wall()) {
                    cell.removeCharacter(this);
                    cell.getMaze().getCell(cell.getX(), cell.getY() + 1).addCharacter(this);
                    this.cell = cell.getMaze().getCell(cell.getX(), cell.getY() + 1);
                }

            } else {
                if (!cell.getW_Wall()) {
                    cell.removeCharacter(this);
                    cell.getMaze().getCell(cell.getX() - 1, cell.getY()).addCharacter(this);
                    this.cell = cell.getMaze().getCell(cell.getX() - 1, cell.getY());
                }
            }
        } catch (UnknownCharacterException e) {
            e.printStackTrace();
        }
    }

    public enum Direction {
        E,
        W,
        N,
        S
    }

    private Direction randomDirection(Direction[] elements) {
        Random numberGenerator = new Random();
        return elements[numberGenerator.nextInt(elements.length)];
    }

    /**
     * Le fou va donner de faux indices
     */
    public void interaction(Player player) {
    	/* ceci lance une exception car la this.cell n'est pas "initialis�e. Ca fait nullPointerExeption. A corriger
        int x = (int) Math.floor(Math.random() * this.cell.getMaze().getHeight());
        int y = (int) Math.floor(Math.random() * this.cell.getMaze().getHeight());
        */
        // un exemple qui fonctionne
        int x = (int) Math.floor(Math.random() * 2);
        int y = (int) Math.floor(Math.random() * 2);
        System.out.println("Le but se trouve à " + x + " cases à l'Est et " + y + " cases au Sud de la cellule actuelle. Hip! 🍷");
    }

    /**
     * Return the name of the work for this character
     */
    public String getWorkName() {
        return workName;
    }


}
