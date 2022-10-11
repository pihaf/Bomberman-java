package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.control.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.layout.FlowPane;
public class BombermanGame extends Application  {
    public static final int WIDTH = 20;

    public static final int HEIGHT = 15;
    private int xStart;
    private int yStart;
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();

    public static final List<Flame> flameList = new ArrayList<>();

    //start flame radius, neu co powerups thi tang len
    public int flameRadius = 1;
    public int startBomb = 1;
    public int startSpeed = 2;
    public int startFlame = 1;
    private Bomber bomberman = new Bomber(1 , 1, Sprite.player_right.getFxImage());
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {


        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        createMap();
        ArrayList<Bomb> bombs = bomberman.getBombs();
        entities.add(bomberman);
        AnimationTimer timer = new AnimationTimer() {
            public long prevTime = 0;
            @Override
            public void handle(long now) {
            long dt = now - prevTime;
                if (dt > 10000000) {
                    render();
                    update();
                    prevTime = now;
                }
            }


        };
        timer.start();
        // chep cua o kia tu nhien dc :))
        scene.setOnKeyPressed(event -> {
            bomberman.handleKeyPressedEvent(event.getCode());
        });
        scene.setOnKeyReleased(event -> bomberman.handleKeyReleasedEvent(event.getCode()));

    }

    //tao map
    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                Entity brick = null;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    if (i % 2 == 0 && j % 2 == 0) {
                        brick = new Brick(i, j, Sprite.brick.getFxImage());
                    }
                }
                stillObjects.add(object);
                if (brick != null) stillObjects.add(brick);
            }
        }
    }

    public void update() {
        bomberman.move();
        // animation cho cac entity
        entities.forEach(Entity::update);
        for (int i = 0; i < flameList.size(); i ++) {
            flameList.get(i).update();
        }
        bomberman.update();
        ArrayList<Bomb> bombs = bomberman.getBombs();
        // cac hoat dong cua bom
        for(Bomb bomb : bombs) {
            bomb.update();
        }
        for (int i = 0; i < stillObjects.size(); i ++) {
            stillObjects.get(i).update();
        }
        handleCollision();
        checkCollisionFlame();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        // cai nay cung chua xu ly duoc
        ArrayList<Bomb> bombs = bomberman.getBombs();
        for (Bomb bomb : bombs) {
            bomb.render(gc);
        }
        flameList.forEach(g -> g.render(gc));
    }
// handle collision, da xong phan cuc da
    public void handleCollision() {
        Rectangle bomber = bomberman.getHitBox();
        for (Entity stillObject : stillObjects) {
            Rectangle r = stillObject.getHitBox();
            if ((stillObject instanceof Wall || stillObject instanceof Brick) && bomber.intersects(r)) {
                bomberman.stay();
            }
        }
    }

    /**
     * flame collision.
     *
     */
    public void checkCollisionFlame() {
        for (Flame flame : flameList) {
            Rectangle r1 = flame.getHitBox();
            for (Entity stillObject : stillObjects) {
                Rectangle r2 = stillObject.getHitBox();
                //!stillObject instanceof Item
                if (r1.intersects(r2)) {
                    stillObject.setAlive(false);
                }
            }
            //chua co check va cham voi enemy
            Rectangle r2 = bomberman.getHitBox();
            if (r1.intersects(r2)) {
                bomberman.setAlive(false);
                startBomb = 1;
                startFlame = 1;
                startSpeed = 1;
            }
                if (!bomberman.isAlive()) {
                    Timer count = new Timer();
                    count.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            entities.remove(bomberman);
                            bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
                            entities.add(bomberman);
                            count.cancel();

                        }

                    }
                    , 500, 1);
                    break;//dead sound
                }
            }
        }
        }

