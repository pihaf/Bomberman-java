package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
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
import javafx.scene.layout.FlowPane;
public class BombermanGame extends Application  {
    public static Bomber bomberman = new Bomber(1 , 1, Sprite.player_right.getFxImage());

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();



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
        ArrayList<Entity> bombs = bomberman.getBombs();
        entities.add(bomberman);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                render();
                update();
            }
        };
        timer.start();
        // chep cua o kia tu nhien dc :))
        scene.setOnKeyPressed(event -> {
            bomberman.handleKeyPressedEvent(event.getCode());
        });
        scene.setOnKeyReleased(event -> bomberman.handleKeyReleasedEvent(event.getCode()));

    }

    public void createMap() {

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }

    }

    public void update() {
        // animation cho cac entity
        entities.forEach(Entity::update);
        // animation cho bomb (hien tai van chua the su dung dc)
        for(Entity bomb : bomberman.getBombs()) {
            bomb.update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        // cai nay cung chua xu ly duoc
        bomberman.getBombs().forEach(g->g.render(gc));
    }
}
