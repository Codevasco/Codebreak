import Characters.Beer;
import Characters.Character;
import Characters.Enemy;
import Characters.Score;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class CollisionDetector {

    ArrayList<Beer> beers;
    ArrayList<Enemy> enemies;
    Character player;
    SoundHandler yesBaby = new SoundHandler("sound/yesbaby.wav", false);
    SoundHandler fresh = new SoundHandler("sound/fresh.wav", false);
    SoundHandler bitdrunk = new SoundHandler("sound/bitdrunk.wav", false);
    SoundHandler beerbeer = new SoundHandler("sound/beerbeer.wav", false);
    SoundHandler bastard = new SoundHandler("sound/bloodybastard.wav", false);


    public CollisionDetector(ArrayList<Beer> beers, Character player, ArrayList<Enemy> enemies) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.beers = beers;
        this.enemies = enemies;
        this.player = player;
    }

    public void loopCollision() {

        for (int i = 0; i < beers.size(); i++) {
            if (checkCollision(beers.get(i))) {
                Score.upScore();
                System.out.println(Score.getScore());

                double rand = Math.random();

                if (rand < 0.6) {
                    yesBaby.play();
                } else if (rand < 0.8) {
                    fresh.play();
                } else if (rand < 0.9) {
                    bitdrunk.play();
                } else {
                    beerbeer.play();
                }

                beers.get(i).getPic().delete();
                beers.remove(i);
            }
        }


        for (int i = 0; i < enemies.size(); i++) {
            if (checkEnemyCollision(enemies.get(i))) {
                bastard.play();
                enemies.get(i).getPic().delete();
                enemies.remove(i);
            }
        }
    }

    public boolean checkCollision(Beer beer) {
        if (beer.getPic().getY() > player.getPicture().getY() && beer.getPic().getY() < player.getPicture().getMaxY() && beer.getPic().getX() > player.getPicture().getX() && beer.getPic().getX() < player.getPicture().getMaxX()) {
            System.out.println("COLLISION");
            return true;
        }

        return false;
    }


    public boolean checkEnemyCollision(Enemy enemy) {
        if (enemy.getPic().getY() > player.getPicture().getY() && enemy.getPic().getY() < player.getPicture().getMaxY() && enemy.getPic().getX() > player.getPicture().getX() && enemy.getPic().getX() < player.getPicture().getMaxX()) {
            System.out.println("COLLISION WITH ENEMY");
            player.looseLife();
            return true;
        }
        return false;
    }

}