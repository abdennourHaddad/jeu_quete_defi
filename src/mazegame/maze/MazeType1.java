package src.mazegame.maze;

/**
 * Maze using the Binary Tree algorithm
 */
public class MazeType1 extends Maze {

    /**
     * Create a maze with the type Binary Tree
     * @param width Width of the Maze
     * @param height Height of the Maze
     */
    public MazeType1(int width, int height) {
        super(width, height);
    }

    public void initMaze() {
        for (int h = 0; h < this.height; h++) { //Lignes
            for (int w = 0; w < this.width; w++) { //Colonnes
                if (w==this.width-1 && h==this.height-1){ //Dernière case du tableau
                    this.board[w][h].destroy(this.board[w][h-1],"N");
                    this.board[w][h].destroy(this.board[w-1][h], "W");
                }
                else if (w==this.width-1){ //Si on arrive à la fin de la ligne
                    this.board[w][h].destroy(this.board[w][h+1],"S");
                }
                else if (h==this.height-1){ //Si on arrive à la dernière colonne
                    this.board[w][h].destroy(this.board[w + 1][h], "E");
                }
                else { //Pour toutes les autres cases
                    if (Math.round(Math.random()) == 0) {
                        this.board[w][h].destroy(this.board[w+1][h], "E");
                    }
                    else {
                        this.board[w][h].destroy(this.board[w][h + 1], "S");
                    }
                }
            }
        }
    }
}
