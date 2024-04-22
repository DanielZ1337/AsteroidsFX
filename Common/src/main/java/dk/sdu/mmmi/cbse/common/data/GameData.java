package dk.sdu.mmmi.cbse.common.data;

public class GameData {

    private final GameKeys keys = new GameKeys();
    private int displayWidth = 1080;
    private int displayHeight = 1080;

    public GameKeys getKeys() {
        return keys;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }


}
