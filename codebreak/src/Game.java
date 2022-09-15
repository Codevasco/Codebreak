import Characters.*;
import Characters.Character;
import field.Field;
import field.Scenes;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private Field field;
    private Character player;
    private Controls controls;
    private Lives lives;
    private Score score;
    SoundHandler damnit = new SoundHandler("sound/damnit.wav", false);
    SoundHandler dontspill = new SoundHandler("sound/dontspill.wav", false);
    SoundHandler fckinhell = new SoundHandler("sound/fckinhell.wav", false);
    SoundHandler bgMusic = new SoundHandler("sound/bgMusic_org.wav", true);
    SoundHandler wasted = new SoundHandler("sound/wasted.wav", false);

    Picture border = new Picture(10, 0, "border.png");

    private CollisionDetector collisionDetector;
    private ArrayList<Beer> beers;
    private ArrayList<Enemy> enemies;
    static boolean menu = true;
    static Scenes gameState = Scenes.MENU;

    public Game(int cols, int rows) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        field = new Field(cols, rows);
        lives = new Lives(field);
        player = new Character(lives, field);
        beers = new ArrayList<>();
        enemies = new ArrayList<>();
        collisionDetector = new CollisionDetector(beers, player, enemies);
        score = new Score(field);

        System.out.println(lives.getNumLives());

    }

    public void init() throws InterruptedException {
        controls = new Controls(player, this);
        bgMusic.play();

        field.show(Scenes.MENU);
        start();

    }

    public void start() throws InterruptedException {
        while (gameState == Scenes.MENU) {
            System.out.println("MENU");
        }

        field.show(Scenes.GAME);
        player.show();
        lives.showLivesRemaining();
        score.showScore();
        border.draw();

        while (gameState == Scenes.GAME) {
            beerFactory();
            enemyFactory();
            Thread.sleep(10);
            beerMovement();
            enemyMovement();
            collisionDetector.loopCollision();
            if (!lives.stillHaveLives()) {
                score.showScoreGameOver();
                break;
            }
        }
        gameOver();
        field.show(Scenes.GAMEOVER);

    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Character getPlayer() {
        return player;
    }

    public Field getField() {
        return field;
    }

    public void gameOver() {

        gameState = Scenes.GAMEOVER;
        wasted.play();

    }


    public void beerFactory() {
        while (beers.size() < 20) {
            beers.add(new Beer(field));
            border.delete();
            border.draw();
        }
    }

    public void beerMovement() {

        for (int i = 0; i < beers.size(); i++) {
            if (beers.get(i).getPic().getMaxY() < field.getMaxY() - 100) {
                beers.get(i).getPic().translate(0, 1.5);
            } else {
                player.looseLife();
                double rand = Math.random();

                if (rand < 0.6) {
                    damnit.play();
                } else if (rand < 0.9) {
                    dontspill.play();
                } else {
                    fckinhell.play();
                }

                beers.get(i).getPic().delete();
                beers.remove(beers.get(i));
            }
        }

    }


    public void enemyFactory() {
        while (enemies.size() < 3) {
            enemies.add(new Enemy(field));
        }
    }


    public void enemyMovement() throws InterruptedException {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getPic().getMaxY() < field.getMaxY() - 100) {
                enemies.get(i).getPic().translate(0, 3);
            } else {
                enemies.get(i).getPic().delete();
                enemies.remove(enemies.get(i));
            }
        }

    }


}