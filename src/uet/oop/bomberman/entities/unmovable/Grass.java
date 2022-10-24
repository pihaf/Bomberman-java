package uet.oop.bomberman.entities.unmovable;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.unmovable.UnmovableEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Grass extends UnmovableEntity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
    public Rectangle getHitBox() {
        return new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }
}
