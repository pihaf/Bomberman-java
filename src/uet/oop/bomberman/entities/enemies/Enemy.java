package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovableEntity;

public abstract class Enemy extends MovableEntity {
    protected int timeCounter = 0;
    protected int startX;
    protected int startY;
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        startX = xUnit;
        startY = yUnit;
    }


    public abstract void generateDirection();
    public abstract void restartEnemy();
}
