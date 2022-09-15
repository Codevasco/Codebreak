import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Game game;
        try {
            game = new Game(300, 150);
            game.init();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}