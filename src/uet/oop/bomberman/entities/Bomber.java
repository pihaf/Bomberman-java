package uet.oop.bomberman.entities;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Bomber extends AnimatedEntity {
    int bombNum ;
    private boolean isBomb = false;
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private int radius;
    private KeyCode direction = null;
    private int timeAfterDie = 0;
    public Bomber(int x, int y, Image img ) {
        super( x, y, img);
        setSpeed(2);
        bombNum = 1;
        setRadius(1);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    // xu ly animation cua bomberman
    public void update() {

        if(direction==KeyCode.RIGHT) {
            goRight();
        }
        if (direction == KeyCode.LEFT) {
            goLeft();
        }
        if (direction == KeyCode.DOWN) {
            goDown();
        }
        if (direction == KeyCode.UP) {
            goUp();
        }
        if(isBomb) {
            placeBomb();
        }
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
                bombNum++;
            }
        }
        //
        if(!isAlive()) {
            timeAfterDie ++;
            die();
        }
    }
    // sử lý di chuyển cho bomb
    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN ||keyCode==KeyCode.SPACE) {
            this.direction = keyCode;
        }
        if (keyCode == KeyCode.SPACE) {
            isBomb = true;
        }
    }
    // sử lý di chuyển cho bomb
    public void handleKeyReleasedEvent(KeyCode keyCode) {
        if (direction == keyCode) {
            if (direction == KeyCode.LEFT) {
                img = Sprite.player_left.getFxImage();
            }
            if (direction == KeyCode.RIGHT) {
                img = Sprite.player_right.getFxImage();
            }
            if (direction == KeyCode.UP) {
                img = Sprite.player_up.getFxImage();
            }
            if (direction == KeyCode.DOWN) {
                img = Sprite.player_down.getFxImage();
            }
            if (direction == KeyCode.SPACE) {
                isBomb = false;
            }
            direction = null;
        }
    }
    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, xx++, 20).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2,xx++ , 20).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, xx++, 20).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2,xx++ , 20).getFxImage();
    }
    public void placeBomb() {
        if (bombNum > 0) {
            int xB = (int) Math.round((x + 4) / (double) Sprite.SCALED_SIZE);
            int yB = (int) Math.round((y + 4) / (double) Sprite.SCALED_SIZE);
            for (Bomb bomb : bombs) {
                if (xB * Sprite.SCALED_SIZE == bomb.x && yB * Sprite.SCALED_SIZE == bomb.y) return;
            }
            bombs.add(new Bomb(xB, yB, Sprite.bomb.getFxImage()));
            bombNum--;
        }
    }
    public ArrayList<Bomb> getBombs() {
        return bombs;
    }
@Override
public Rectangle getHitBox() {
    return new Rectangle(desX, desY, Sprite.SCALED_SIZE-15 , Sprite.SCALED_SIZE);
}
    public void die() {
        direction = null;
        stay();
        if(timeAfterDie <= 30) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, timeAfterDie, 20).getFxImage();
        }

    }
}


