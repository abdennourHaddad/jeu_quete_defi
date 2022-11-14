package src.mazegame.entity.character;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import src.mazegame.cell.Cell;
import src.mazegame.entity.Character;
import src.mazegame.item.Parchemin;
import src.mazegame.entity.UnknownCharacterException;
import src.mazegame.entity.player.Player;
import src.mazegame.entity.wrongValueException;

/**
 * A merchant was in a cell of a maze
 */
public class Merchant implements Character {
    /**
     * The name of the work
     */
    private final String workName = "Marchand";
    /**
     * The firstname of the character
     */
    private String name;
    /**
     * The cell of the character
     */
    private Cell cell;
    private Parchemin parchemin;

    /**
     * Creates a Merchant
     * @param name name of the Merchant
     */
    public Merchant(String name) {
        this.name = name;
    }

    /**
     * @return the name of the Merchant
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set a cell to the Merchant
     * @param cell a cell
     */
    public void setCell(Cell cell) {
        this.cell = cell;
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
     * Le marchand vend un indice contre 5 pièces d'or
     */
    public void interaction(Player player) {
        System.out.println("Voulez-vous acheter un indice ?");
        Scanner scanner = new Scanner(System.in);
        String reponse = scanner.nextLine();
        reponse = reponse.toLowerCase();

        if (reponse.equals("oui")) {
            System.out.println("Un indice coûte 10 pièces d'or");

            if (player.getNbGold() >= 10) { // si le joueur a assez de pieces
                player.removeNbGold(10); // il paye 10 pieces. Son nombre d'or diminue
                player.getInventory().addItem(this.parchemin); // on donne le parchemin acheté au joueur, cela entre dans son inventaire
            } else {
                System.out.println("Vous n'avez pas assez de pièces.");
            }
        } else if (reponse.equals("non")) { // si le joueur ne veut rien acheter
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
}
