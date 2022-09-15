package Characters;

import field.Field;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Lives {
    private final Picture[] lives;
    private int numLives;
    private boolean hasLives;


    public Lives(Field field) {
        lives = new Picture[10];
        lives[0] = new Picture(field.getMaxX() - 100, field.getMaxY() - 55, "beer.png");
        for (int i = 1; i < lives.length; i++) {
            lives[i] = new Picture(lives[i - 1].getX() - lives[i - 1].getWidth() - 10, field.getMaxY() - 55, "beer" +
                    ".png");
        }
        numLives = 10;
        hasLives = true;
    }

    public boolean stillHaveLives() {
        return hasLives;
    }

    public void resetNumOfLives() {
        numLives = 10;
        hasLives = true;
    }

    public int getNumLives() {
        return numLives;
    }

    public void showLivesRemaining() {

        for (int i = 0; i < numLives; i++) {
            lives[i].draw();
        }

    }

    public void looseLife() {

        if (numLives == 1) {
            hasLives = false;
            return;
        }

        lives[--numLives].delete();

    }
}