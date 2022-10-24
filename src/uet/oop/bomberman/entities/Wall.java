package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Wall extends UnmovableEntity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        setLayer(5);
    }

    @Override
    public void update() {

    }
    @Override
    public Rectangle getHitBox() {
        return new Rectangle(x, y, Sprite.SCALED_SIZE-5, Sprite.SCALED_SIZE-10);
    }
}
