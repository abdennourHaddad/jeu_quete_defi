package src.mazegame;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

import src.mazegame.entity.*;
import src.mazegame.entity.player.Player;
import src.mazegame.maze.*;

/**
 * Start a MazeGame
 */
public class MazeMain {

    /**
     * Parameter of the game the density of characters and items present in the game
     * should be between 0 and 1 but can be higher
     * Example :
     * 0 : without characters/items
     * >1 : A Lot of characters/items
     */
    private static final double DENSITY_CHARACTERS = 0.5;
    /**
     * Parameter of the game. The density of items in the game
     */
    private static final double DENSITY_ITEMS = 0.2;

    /**
     * Start the script for generating the maze for playing a game
     * @throws UnknownCharacterException
     */
    public static void main(String[] args) throws Exception, UnknownCharacterException {
        String algoVerified = "";
        boolean whVerified = false;
        boolean finished = false;
        Maze mazeBoard = null;
        try (Scanner scanner = new Scanner(System.in)) {
            String[] widthHeightTab = new String[1];
            while (!whVerified) {
                System.out.println("Choisissez la taille du labyrinthe. Ex : \"7x5\" Pour une largeur de 7 cases et une hauteur de 5 cases");
                String widthHeight = scanner.nextLine();
                String regex = "[0-9]+x[0-9]+";
                if (widthHeight.matches(regex)){
                    widthHeightTab = widthHeight.split("x"); //Spit in two values
                    whVerified = true;
                }
                else {
                    System.out.println("Votre proposition ne respecte pas la forme NOMBRExNOMBRE");
                }
            }
            while (algoVerified.equals("")) {
                System.out.println("Veuillez taper un algorithme pour la génération du labyrinthe ");
                System.out.println("Choix :  0  :  \"Quitter\"");
                System.out.println("         1  :  \"BinaryTree\"");
                System.out.println("         2  :  \"Prime#ENCONSTRUCTION#\"");
                System.out.println("         3  :  \"Sidewinder\"");
                String choix = scanner.nextLine();
                try {
                    switch (parseInt(choix)) {
                        case 0:
                            System.out.println("Quitté");
                            System.exit(-1);
                        case 1:
                            mazeBoard = new MazeType1(parseInt(widthHeightTab[0]), parseInt(widthHeightTab[1]));
                            algoVerified = choix;
                            break;
                        case 2:
                            mazeBoard = new MazeType2(parseInt(widthHeightTab[0]), parseInt(widthHeightTab[1]));
                            algoVerified = choix;
                            break;
                        case 3:
                            mazeBoard = new MazeType3(parseInt(widthHeightTab[0]), parseInt(widthHeightTab[1]));
                            algoVerified = choix;
                            break;
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Veuillez recommencer en entrant un choix valable");
                }
            }

            Player player = new Player("Player1");
            
            mazeBoard.getCell(0,0).addPlayer(player); // Ajoute un joueur à la case (0,0)

            //----- ----- -----

            // Add different characters using density parameter
            mazeBoard.generateCharacters(DENSITY_CHARACTERS);
            
            // Add different items using density parameter
            mazeBoard.generateItems(DENSITY_ITEMS);
            
            // Rounds start after this ↘
            while (!finished){
                // Beginning of the round ---
                mazeBoard.display();

                // Asking for action
                System.out.println("Veuillez taper un choix d'action.");
                System.out.println("Choix :  0  :  \"Regarder autour\"");
                System.out.println("         1  :  \"Se déplacer\"");
                System.out.println("         2  :  \"Accéder à l'inventaire\"");
                System.out.println("         3  :  \"Quitter\"");
                String choix = scanner.nextLine();
                try {
                    switch (parseInt(choix)) {// Enhanced switch
                        case 0 ->
                            //Look Around
                            player.lookAround();

                        case 1 -> {
                            //Move

                            System.out.println("Choisissez la direction ou vous voulez aller (N,S,E,W)");
                            String regex = "[NSEW]";
                            String direction = scanner.nextLine();
                            if (!direction.matches(regex)) {
                                System.out.println("Choisissez une direction parmi N S E W");
                            } else {
                                player.move(direction);
                                // TODO Faire bouger tout les bishop et merchant du jeu
//                                bishop.move();
//                                merchant.move();
                            }
                            if (player.getCell().isFinalCell()) {//S'il a atteint la cellule exit c'est la fin
                                finished = true;
                            }
                        }
                        case 2 -> {
                            // Access to the inventory
                            player.lookInventory();
                        }
                        case 3 -> {
                            System.out.println("Quitté");
                            System.exit(-1);
                        }
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Veuillez recommencer en entrant un choix valable");
                }
                // End of the round ---
            }
            System.out.println("Félicitation vous avez atteint la sortie du labyrinthe. 🎉");
        }
    }
}