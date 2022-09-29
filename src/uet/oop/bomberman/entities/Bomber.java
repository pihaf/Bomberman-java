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


public class Bomber extends Entity {
    private boolean isBomb = false;
    private ArrayList<Entity> bombs = new ArrayList<>();

    private KeyCode direction = null;
    public Bomber(int x, int y, Image img ) {
        super( x, y, img);
    }
    @Override
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
        if (direction == KeyCode.SPACE) {
        if(isBomb) {
            placeBomb();
        }
        }

    }
    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN ||keyCode==KeyCode.SPACE) {
            this.direction = keyCode;
        }
        if (keyCode == KeyCode.SPACE) {
            isBomb = true;
        }
    }
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
                isBomb= false;
            }
            direction = null;
        }
    }
    public void goLeft() {
        x-=2;
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, xx++, 20).getFxImage();
    }

    public void goRight() {
        x+=2;
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2,xx++ , 20).getFxImage();
    }

    public void goUp() {
        y-=2;
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, xx++, 20).getFxImage();
    }

    public void goDown() {
        y+=2;
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2,xx++ , 20).getFxImage();
    }
    public void placeBomb() {

        bombs.add( new Bomb(x, y, Sprite.bomb.getFxImage()));
        System.out.println("here");
    }
    public ArrayList<Entity> getBombs() {
        return bombs;
    }
    }


