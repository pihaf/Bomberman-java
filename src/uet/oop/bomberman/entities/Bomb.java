package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {

    public Bomb(int x, int y, Image img ) {
        super( x, y, img);
    }
    @Override
    public void update() {
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, xx++, 20).getFxImage();
    }

}
