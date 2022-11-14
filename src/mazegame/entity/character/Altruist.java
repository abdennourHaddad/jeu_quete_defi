package src.mazegame.entity.character;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import src.mazegame.cell.Cell;
import src.mazegame.entity.Character;
import src.mazegame.entity.UnknownCharacterException;
import src.mazegame.entity.player.Player;
import src.mazegame.entity.wrongValueException;
import src.mazegame.item.Parchemin;

/**
 * @generated
 */
public class Altruist implements Character {
    private final static String workName = "Altruiste";
    /**
     * The name of the character
     */
    private String name;

    /**
     * The free parchment to give to the player
     */
    private Parchemin gift;

    private Cell cell;

    /**
     * Constructor of Altruist character
     *
     * @param name The name of the character
     */
    public Altruist(String name) {
        this.name = name;
        this.gift = new Parchemin();
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

    /**
     * Once called, the player would interact directly at this function
     * to make the interaction possible.
     *
     * @param player A player
     */
    @Override
    public void interaction(Player player) {
        System.out.println("Voulez-vous un indice gratuit ?");
        Scanner scanner = new Scanner(System.in);
        String reponse = scanner.nextLine();
        reponse = reponse.toLowerCase();

        if (reponse.equals("oui")) {
            this.gift.use(player); // on donne l'indice au joueur au joueur
        } else if (reponse.equals("non")) {
            System.out.println("A bientôt !");
        } else {
            System.out.println("La réponse n'est pas valide.");
        }
    }

    /**
     * Return the name of the work for this character
     */
    public String getWorkName() {
        return workName;
    }

    @Override
    public String getName() {
        return name;
    }

    public enum Direction {
        E,
        W,
        N,
        S
    }

    private static Random numberGenerator = new Random();

    public <T> T randomDirection(T[] elements) {
        return elements[numberGenerator.nextInt(elements.length)];
    }

}
