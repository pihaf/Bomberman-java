package uet.oop.bomberman.entities.unmovable;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.unmovable.UnmovableEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Portal extends UnmovableEntity {
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
    }

    @Override
    public void update() {

    }
    @Override
    public Rectangle getHitBox() {
        return new Rectangle(x+10, y+10, Sprite.SCALED_SIZE-30, Sprite.SCALED_SIZE-30);
    }
}
