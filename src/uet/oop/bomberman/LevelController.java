package uet.oop.bomberman;

import javafx.scene.Scene;

public class LevelController {
    public Scene lvScene;
    public int level = 1;
    public boolean won = false;

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Scene getLvScene() {
        return lvScene;
    }

    public void setLvScene(Scene lvScene) {
        this.lvScene = lvScene;
    }
}
