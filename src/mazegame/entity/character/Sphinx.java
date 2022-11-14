package src.mazegame.entity.character;

import java.util.*;

import src.mazegame.entity.Character;
import src.mazegame.entity.player.Player;
import src.mazegame.item.Parchemin;

/**
 * @generated
 */
public class Sphinx implements Character {
    private String workName = "Sphinx";
    /**
     * The name of the character
     */
    private String name;

    private String question;

    private String answer;

    private Parchemin parchemin;

    /**
     * Constructor of a Sphinx
     * @param name The name of the character
     * @param question The question
     * @param answer The answer for the question
     */
    public Sphinx(String name, String question, String answer) {
        this.name = name;
        this.question = question;
        this.answer = answer;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return this.answer;
    }

    /**
     * le joueur doit résoudre une énigme pour avoir un indice
     *
     * @param player The player
     */
    public void interaction(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.getQuestion()); // le sphynx pose une question
        String reponse = scanner.nextLine();

        if (reponse.equals(this.getAnswer())) {
            System.out.println("Bravo ! Bonne réponse, vous gagnez un indice.");
            player.getInventory().addItem(this.parchemin); // on donne le parchemin au joueur
        } else {
            System.out.println("La réponse est fausse.");
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
}
