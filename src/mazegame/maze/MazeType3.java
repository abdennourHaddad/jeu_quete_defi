package src.mazegame.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.mazegame.cell.Cell;


public class MazeType3 extends Maze {


    /**
     * Create a maze with sidewinder type
     * @param width Width of the Maze
     * @param height Height of the Maze
     */
    public MazeType3(int width, int height) {
        super(width, height);
    }

    public void initMaze() {
        // First row can only be a single passage(cannot crave north), crave it.
        for(int x = 0; x < super.width-1; x++) {
            this.board[x][0].destroy(super.board[x+1][0], "E");    
        }
        
        // Scan grid row by row (starting from the second one)
        for (int y = 1; y < super.height; y++) {
            // Initialize an empty list to keep track of the current run path
            List<Cell> currentPath = new ArrayList<>();
            Random random = new Random();
            for(int x = 0; x < super.width; x++) {
                try {
                    // Add current cell to the path
                    currentPath.add(this.board[x][y]);
                    int casse = random.nextInt(2);
                    if ((x < super.width) && (casse == 1)) {
                        this.board[x][y].destroy(this.board[x+1][y], "E");
                    }
                    else {
                        Cell cell = currentPath.get(random.nextInt(currentPath.size()));
                        int coordX = cell.getX();
                        int coordY = cell.getY();
                        Cell tempCell = this.getCell(coordX, (coordY-1));
                        cell.destroy(tempCell, "N");
                        // Empty the run set and continue row scan
                        currentPath.clear();
                    }
                }
                catch (IndexOutOfBoundsException e) {    
                    this.board[x][y].destroy(this.board[x][y-1], "N");
                }
            }
    
        }
    }


}
