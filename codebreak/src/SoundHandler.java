import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundHandler {

    Clip clip;
    String status;
    AudioInputStream audioInputStream;
    static String filePath;
    boolean loop;

    public SoundHandler(String filePath, boolean loop) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {

        this.loop = loop;

        URL path = getClass().getResource(filePath);
        audioInputStream = AudioSystem.getAudioInputStream(path);

        clip = AudioSystem.getClip();

        clip.open(audioInputStream);

    }


    public void play() {

        clip.stop();
        clip.setMicrosecondPosition(0);
        clip.start();
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        status = "play";
    }

    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        clip.close();
    }


}
