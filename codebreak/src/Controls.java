import Characters.Character;
import field.Scenes;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class Controls implements KeyboardHandler, MouseHandler {
    private Character player;
    private Keyboard keyboard;
    private Mouse mouse;
    private Game game;

    public Controls(Character player, Game game) {

        keyboard = new Keyboard(this);
        mouse = new Mouse(this);
        this.player = player;
        this.game = game;

        init();
    }

    public void init() {

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent e = new KeyboardEvent();
        e.setKey(KeyboardEvent.KEY_E);
        e.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent close = new KeyboardEvent();
        close.setKey(KeyboardEvent.KEY_Q);
        close.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent rightIdle = new KeyboardEvent();
        rightIdle.setKey(KeyboardEvent.KEY_RIGHT);
        rightIdle.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent leftIdle = new KeyboardEvent();
        leftIdle.setKey(KeyboardEvent.KEY_LEFT);
        leftIdle.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);


        keyboard.addEventListener(right);
        keyboard.addEventListener(left);
        keyboard.addEventListener(e);
        keyboard.addEventListener(close);

        keyboard.addEventListener(rightIdle);
        keyboard.addEventListener(leftIdle);

        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                player.moveRight();
                break;
            case KeyboardEvent.KEY_LEFT:
                player.moveLeft();
                break;
            case KeyboardEvent.KEY_Q:
                System.exit(0);
                break;
            case KeyboardEvent.KEY_E:
                if (Game.gameState == Scenes.MENU) {
                    Game.gameState = Scenes.GAME;
                }
                break;

        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                player.idle2();
                break;
            case KeyboardEvent.KEY_LEFT:
                player.idle1();
                break;

        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (mouseEvent.getX() > player.getPicture().getMaxX()) {
            player.moveRight();
        } else if (mouseEvent.getX() < player.getPicture().getX()) {
            player.moveLeft();
        }

    }
}