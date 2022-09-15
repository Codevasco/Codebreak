package Characters;

import field.Field;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Score {

    private static final int SCORE = 10;
    private static Picture[][] scoreDisplay;
    private static Picture[] display;
    private static int score;
    private Field field;

    public Score(Field field) {
        this.field = field;
        score = 0;
        scoreDisplay = new Picture[6][10];
        display = new Picture[6];
        populateScoreDisplayDigits(field.getX() + 50);
        resetScoreDisplay();
    }


    public static void upScore() {
        score += SCORE;
        updateScore();
    }

    public void resetScore() {
        score = 0;
        resetScoreDisplay();
    }

    public void populateScoreDisplayDigits(int xCoordinate) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                scoreDisplay[i][j] = new Picture(xCoordinate, 565, j + ".png");
            }
            xCoordinate = scoreDisplay[i][0].getMaxX();
        }
    }


    public void resetScoreDisplay() {
        for (int i = 0; i < display.length; i++) {
            display[i] = scoreDisplay[i][0];
        }
    }

    public void showScore() {
        for (Picture picture : display) {
            picture.draw();
        }
    }

    public static void updateScore() {
        String scoreToString = score + "";

        for (int i = scoreToString.length() - 1, j = display.length - 1; i >= 0; i--, j--) {
            display[j].delete();
            int a = java.lang.Character.getNumericValue(scoreToString.charAt(i));
            display[j] = scoreDisplay[j][a];
            display[j].draw();
        }
    }

    public void showScoreGameOver() {
        for (Picture pic : display) {
            pic.delete();
            pic.translate(85, -180);
            pic.draw();
        }
    }

    public void hideScoreGameOver() {
        for (Picture pic : display) {
            pic.delete();
            pic.translate(-85, 180);
        }
    }

    public static int getScore() {
        return score;
    }
}