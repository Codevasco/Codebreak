package Characters;


import field.Field;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Character {

    private final Lives lives;

    private int speed = 35;
    private Picture pica;
    private boolean picRight = false;
    private Field field;


    public Character(Lives lives, Field field) {
        pica = new Picture(350, 380, "player/player1.png");
        this.lives = lives;
        this.field = field;
    }

    public void show() {
        pica.draw();
    }

    public Picture getPicture() {
        return pica;
    }

    public void looseLife() {
        lives.looseLife();
    }

    public void resetPosition() {
        pica.translate(350 - pica.getX(), 0);
    }

    public int getLives() {
        return lives.getNumLives();
    }

    private int picIndex = 0;

    public void moveRight() {

        if (pica.getMaxX() + speed <= field.getMaxX() + 5) {
            if (picIndex > 1) {
                picIndex = 0;
            }
            if (picIndex == 0) {
                pica.load("player/right/player77.png");
            } else {
                pica.load("player/right/player76.png");
            }
            picIndex++;

            pica.translate(speed, 0);
        }
    }


    public void moveLeft() {
        System.out.println("LEFT");
        if (pica.getX() >= field.getX() + Field.PADDING) {
            if (picIndex > 1) {
                picIndex = 0;
            }
            if (picIndex == 0) {
                pica.load("player/left/player77.png");
            } else {
                pica.load("player/left/player76.png");
            }
            picIndex++;
            pica.translate(-speed, 0);
        }
    }

    public void idle1() {
        pica.load("player/player1.png");
    }

    public void idle2() {
        pica.load("player/player2.png");
    }


}