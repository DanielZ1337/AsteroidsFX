package dk.sdu.mmmi.cbse.common.data;

public class GameData {

    private int displayWidth  = 1080;
    private int displayHeight = 1080;
    private final GameKeys keys = new GameKeys();
    private int gameScore = 0;
    private int lives = 3;

    public void increaseLives() {
        this.lives++;
    }

    public void decreaseLives() {
        this.lives--;
    }

    public void increaseGameScore(){
        this.gameScore++;
    }

    public void decreaseGameScore(){
        this.gameScore--;
    }

    public void setGameScore(int score) {
        this.gameScore = score;
    }

    public int getGameScore() {
        return gameScore;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }


}
