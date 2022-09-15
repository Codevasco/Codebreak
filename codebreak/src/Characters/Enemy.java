package Characters;

import field.Field;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Enemy {
    private Field field;
    private Picture enemy;
    private String[] enemyPic = {"wine.png"};
    private int randPic = (int) Math.floor(Math.random() * enemyPic.length);


    public Enemy(Field field) {
        this.field = field;
        enemy = new Picture(((int) (Math.random() * (field.getWidth() - 80 + 1) + 70)),
                ((int) (Math.random() * -2500) - 30), enemyPic[randPic]);
        enemy.draw();
    }

    public Picture getPic() {
        return enemy;
    }
}
