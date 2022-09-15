package Characters;

import field.Field;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Beer {

    private Picture pic;

    public Beer(Field field) {
        pic = new Picture(((int) Math.floor(Math.random() * (field.getWidth() - 80 + 1) + 70)),
                ((int) (Math.random() * -2500) - 30), "beer.png");
        pic.draw();

        System.out.println(pic.getX() + " | " + pic.getY());
        System.out.println();
    }

    public Picture getPic() {

        return this.pic;
    }
}