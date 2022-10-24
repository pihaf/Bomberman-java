package uet.oop.bomberman.entities.movable.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.audio.Music;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.audio.Music.ENEMY_DEAD;

public class Minvo extends Enemy{

    private int direction;

    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setSpeed(2);
        generateDirection();
        alive = true;
    }

    @Override
    public void update() {
        if(isAlive()) {
            if (direction == 0) goUp();
            if (direction == 1) goDown();
        } else {
            timeCounter++;
            if(timeCounter == 1) {
                if (!BombermanGame.muted.isMutedSound())new Music(ENEMY_DEAD).play();
                BombermanGame.score += 100;
            }
            if(animated < 30){
                animated ++;
                img = Sprite.minvo_dead.getFxImage();
            }else {
                BombermanGame.enemies.remove(this);
            }
        }
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, left++, 18).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, right++, 18).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        generateDirection();
    }

    @Override
    public void generateDirection() {
        if (direction == 0) direction = 1;
        else if (direction == 1) direction = 0;
    }

    @Override
    public void restartEnemy() {

    }
}
