package uet.oop.bomberman.entities.movable.enemies;

import javafx.scene.image.Image;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.audio.Music;
import uet.oop.bomberman.graphics.Sprite;


import static uet.oop.bomberman.audio.Music.ENEMY_DEAD;

public class Kondoria extends Enemy {
    private int direction;
    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setSpeed(1);
        generateDirection();
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, left++, 18).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, right++, 18).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, up++, 18).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, down++, 18).getFxImage();
    }

    @Override
    public void generateDirection() {
        if (BombermanGame.bomberman.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE < 0) direction = 0;
        if (BombermanGame.bomberman.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE > 0) direction = 1;
        if (BombermanGame.bomberman.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE < 0) direction = 2;
        if (BombermanGame.bomberman.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE > 0) direction = 3;
        }


    @Override
    public void restartEnemy() {
        super.stay();
        this.desX = startX * Sprite.SCALED_SIZE;
        this.desY = startY * Sprite.SCALED_SIZE;
    }

    @Override
    public void update() {
        generateDirection();
            if (direction == 0) goLeft();
            if (direction == 1) goRight();
            if (direction == 2) goUp();
            if (direction == 3) goDown();
        if(! BombermanGame.bomberman.isAlive()) {
            restartEnemy();
        }
            if (isAlive()) {
            } else {
                timeCounter++;
                if(timeCounter == 1) {
                    if (!BombermanGame.muted.isMutedSound())new Music(ENEMY_DEAD).play();
                    BombermanGame.score += 100;
                }
        if(animated< 30){
        super.stay();
        animated++;
        img=Sprite.kondoria_dead.getFxImage();
//            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
//                    Sprite.mob_dead3, animate, 10).getFxImage();
        }else
        BombermanGame.enemies.remove(this);
        }
        }
    }