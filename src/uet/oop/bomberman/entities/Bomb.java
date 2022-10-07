package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private int timeCounter = 0;
    int radius = 1;
    public Bomb(int x, int y, Image img ) {
        super( x, y, img);
        alive = true;
    }
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    @Override
    public void update() {
        timeCounter++;
        if (timeCounter == 100) {
            explode();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, xx++, 20).getFxImage();
    }

    public void explode() {
        Flame e = new Flame(x, y);
        e.setRadius(radius);
        e.render_explosion();
        //add bomb sound
        alive = false;
    }
}
