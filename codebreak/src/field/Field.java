package field;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Field {

    private static int CELL_SIZE = 5;
    public static final int PADDING = 40;
    private int rows;
    private int cols;
    private float width;
    private float height;

    private Picture background = new Picture(Field.PADDING + 25, Field.PADDING - 6, "bg.jpg");
    private Picture menuPic = new Picture(10, 10, "main.png");
    private Picture gameOverPic = new Picture(10, 10, "gameover.png");


    public Field(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.width = cols * CELL_SIZE;
        this.height = rows * CELL_SIZE;
        init();

    }


    public void init() {
    }


    public int getCols() {
        return cols;
    }


    public int getRows() {
        return rows;
    }


    public void setPos(int col, int row) {
        cols = col;
        rows = row;


    }


    public void show(Scenes state) {
        background.delete();
        menuPic.delete();
        gameOverPic.delete();
        if (Scenes.MENU == state) {
            menuPic.draw();
        } else if (Scenes.GAME == state) {
            background.draw();
        } else {
            gameOverPic.draw();
        }
    }


    public void hide() {

    }

    public float getWidth() {
        return background.getWidth();
    }


    public int getCellSize() {
        return CELL_SIZE;
    }

    public int getPadding() {
        return PADDING;
    }

    public int getMaxX() {
        return background.getMaxX();
    }

    public int getMaxY() {

        return background.getMaxY();
    }

    public int getX() {
        return background.getX();
    }

    public int getY() {
        return background.getY();
    }

    public int colsToX(int cols) {
        return PADDING + (cols * CELL_SIZE);
    }

    public int rowsToY(int rows) {
        return PADDING + (rows * CELL_SIZE);
    }
}