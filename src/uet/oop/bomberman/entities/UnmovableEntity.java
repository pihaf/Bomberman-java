package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class UnmovableEntity extends Entity {
    public UnmovableEntity(int x, int y, Image img) {
        super(x, y, img);
    }

    public UnmovableEntity(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    @Override
    public void update() {

    }

    @Override
    public Rectangle getHitBox() {
        return new Rectangle(x, y, Sprite.SCALED_SIZE-15, Sprite.SCALED_SIZE);
    }

}
