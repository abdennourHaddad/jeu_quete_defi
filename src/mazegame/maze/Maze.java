package src.mazegame.maze;

import src.mazegame.cell.*;
import src.mazegame.entity.character.*;
import src.mazegame.entity.Character;
import src.mazegame.item.Item;
import src.mazegame.item.*;

import java.io.File;
import java.util.*;

/**
* For a board of MazeGame
*/
public abstract class Maze {
    /**
     * The width of this maze
     */
    protected int width;
    /**
     * The height of this maze
     */
    protected int height;
    /**
     * List on 2 dimensions for store the Cells of the maze
     */
    protected Cell[][] board;
    /**
     * The final cell
     */
    public Cell finalCell;

    /**
     * Generate a maze, by default, without algorithm all cells have all their walls built
     * @param width The number of Cells for the width of the maze
     * @param height The number of Cells for the height of the maze
     */
    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Cell[width][height];
        int aleaHeight = (int)(Math.random() * height);
        int aleaWidth = (int)(Math.random() * width);
//        System.out.println("Sortie : " + aleaHeight + " " + aleaWidth);
        for (int h=0; h < height; h++){
            for (int w=0; w < width; w++){
                if (h == aleaHeight && w == aleaWidth){//Verifying if it's the exit cell
                	this.finalCell = new BasicCell(w,h,this,true);
                    this.board[w][h] = this.finalCell;
//                    System.out.println("x = " + this.board[w][h].getX() + " | y = " + this.board[w][h].getY() + " : FINAL  CELL " + this.finalCell.getX() + " " + this.finalCell.getY());


                }
                else{
                    this.board[w][h] = new BasicCell(w,h,this,false);
//                    System.out.println("x = " + this.board[w][h].getX() + " | y = " + this.board[w][h].getY() + " : NORMAL CELL");
                }
            }
        }
        initMaze();
    }

    /**
     * Return the width of this Maze (Number of Cell inside row)
     * @return The number of Cells in one row
     */
    public int getWidth(){
        return this.width;
    }

    /**
     * Return the height of this Maze (Number of Cell inside column)
     * @return The number of Cells in one column
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * Returns the cell with the coordinates (coordX, coordY)
     * @param coordX
     * @param coordY
     * @return cell(coordX, coordY)
     */
    public Cell getCell(int coordX, int coordY) {
        return this.board[coordX][coordY];
    }


    /**
     * Generating and placing character inside this maze according to density
     * @param density number should be between 0 and 1 (>0) who describe the density of population
     */
    public void generateCharacters(double density) throws Exception {
        if ((density < 0)||(density>1)){
            throw new Exception("Density is up to 0");
        }

        Random inBoundsGenerator = new Random();

        ArrayList<String> nameList = new ArrayList<>();// Stock the names of a file
        Scanner sc = new Scanner(new File("data/Prenoms.csv"));
        while (sc.hasNext()) {
            nameList.add(sc.nextLine());
        }
        sc.close();// Referme le scanner
        int k = 0;
        Character character = null;
        for (int i = 0; i < density*(this.width*this.height); i++){
            int indexNameList = (int)(Math.random() * nameList.size());// Chose a random name
            if (k > 3){
                k = 0;
            }
            if (k == 0){
                character = new Altruist(nameList.get(indexNameList));
            }
            else if(k == 1){
                character = new Bishop(nameList.get(indexNameList));
            }
            else if (k == 2){
                character = new Merchant(nameList.get(indexNameList));
            }
            else if (k == 3){
                character = new Sphinx(nameList.get(indexNameList), "Quelle est la capitale de la France ?", "Paris");
            }
            k++;

            int a = inBoundsGenerator.nextInt(this.width);
            int b = inBoundsGenerator.nextInt(this.height);
            this.getCell(a,b).addCharacter(character);
        }
    }
    
    /**
     * Generating and placing item inside this maze according to density
     * @param density number should be between 0 and 1 (>0) who describe the density of items
     */
    public void generateItems(double density) throws Exception {
        if ((density < 0)||(density>1)){
            throw new Exception("Density is up to 0");
        }

        Random inBoundsGenerator = new Random();

        int k = 0;
        Item item = null;
        for (int i = 0; i < density*(this.width*this.height); i++){
            if (k > 1){
                k = 0;
            }
            if (k == 0){
                item = new Jewel();
            }
            else if(k == 1){
                item = new Parchemin();
            }
            k++;

            int a = inBoundsGenerator.nextInt(this.width);
            int b = inBoundsGenerator.nextInt(this.height);
            this.getCell(a,b).addItem(item);
        }
    }


    /**
     * Show this maze in the terminal
     */
    public void display() {
        String southWalls = null;
        for (int h = 0; h < this.height; h++) {
            String northWalls = ""; // Represent the Area of potentials walls of one row
            southWalls = ""; // Represent the Area of potentials walls for the south border of the maze
            String pathArea = ""; // Represent the Area of potentials Path of one row
            for (int w = 0; w < this.width; w++) {
                //Verifying the existence of north wall
                if (board[w][h].getN_Wall()) {
                    northWalls += "██████";
                } else {
                    northWalls += "███───";
                }
                //Verifying the existence of west wall
                if(board[w][h].getPlayers().isEmpty()){//Verifying the existence of a character in the cell
                    if (board[w][h].getW_Wall()) {
                        pathArea += "███   ";
                    } else {
                        pathArea += " |    ";
                    }
                } else {
                    if (board[w][h].getW_Wall()) {
                        pathArea += "███ X ";
                    } else {
                        pathArea += " |  X ";
                    }
                }
                //Last row
                if (w == this.width - 1) {
                    northWalls += "███";
                    if (board[w][h].getE_Wall()) {
                        pathArea += "███";
                    } else {
                        pathArea += " | ";
                    }
                }
                //Last column (southWalls)
                if (h == this.height - 1) {
                    if (board[w][h].getS_Wall()) {
                        southWalls += "██████";
                    } else {
                        southWalls += "███───";
                    }
                    if (w == this.width - 1) {
                        southWalls += "███";
                    }
                }
            }
            System.out.println(northWalls);
            System.out.println(pathArea);
            if (southWalls != "") {// ⛔ Do not change to equals
                System.out.println(southWalls);
            }
        }
    }

    /**
     * Initialization of the destructions of all walls depends on algorithm selected to build the maze
     */
    public abstract void initMaze();
}
