package uet.oop.bomberman;

//test for menu
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Powerups.BombItem;
import uet.oop.bomberman.entities.Powerups.FlameItem;
import uet.oop.bomberman.entities.Powerups.SpeedItem;
import uet.oop.bomberman.entities.enemies.Balloon;
import uet.oop.bomberman.entities.enemies.Doll;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.enemies.Oneal;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.List;

public class BombermanGame extends Application  {
    public static int WIDTH = 31;

    public static int HEIGHT = 13;
    private int xStart = 1;
    private int yStart = 1;
    public static int level = 1;
    private GraphicsContext gc;
    private Canvas canvas;
    private Scanner scanner;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static final List<Enemy> enemies = new ArrayList<>();
    public static final List<Flame> flame = new ArrayList<>();
    public static int[][] map = new int[HEIGHT][WIDTH];
    //start flame radius, neu co powerups thi tang len
    public int flameRadius = 1;
    public int startBomb = 1;
    public int startSpeed = 2;
    public int startFlame = 1;
    public static Bomber bomberman = new Bomber(1 , 1, Sprite.player_right.getFxImage());
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

        //menu scene test
        Scene menu;
        Label label = new Label("Menu");
        Button b1 = new Button("Start");
        Button b2 = new Button("Exit");
        b1.setOnAction(e -> stage.setScene(scene));
        b2.setOnAction(e -> System.exit(0));
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label, b1, b2);
        menu = new Scene(layout1, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

        // Them scene vao stage
        stage.setTitle("Bomberman");
        stage.setScene(menu);
        stage.show();
        load(level);
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
    //public void createMap() {
        //for (int i = 0; i < WIDTH; i++) {
           // for (int j = 0; j < HEIGHT; j++) {
             //   Entity object;
               // Entity brick = null;
                //if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                  //  object = new Wall(i, j, Sprite.wall.getFxImage());
                //} else {
                  //  object = new Grass(i, j, Sprite.grass.getFxImage());
                    //if (i % 2 == 0 && j % 2 == 0) {
                      //  object = new Wall(i, j, Sprite.wall.getFxImage());
                    //}
                    //if (i == 9 && j == 9) {
                      //  enemies.add(new Balloon(i, j, Sprite.balloom_left1.getFxImage()));
                    //}
                    //if (i == 5 && j == 5) {
                      //  enemies.add(new Doll(i, j, Sprite.doll_left1.getFxImage()));
                    //}
                    //if (i == 7 && j == 7) {
                      //  enemies.add(new Oneal(i, j, Sprite.oneal_left1.getFxImage()));
                    //}
                //}
                //stillObjects.add(object);
                //if (brick != null) stillObjects.add(brick);
            //}
        //}
    //}

    public void load() {
        try {
            scanner = new Scanner(new FileReader("res/levels/level" + level + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.nextInt();
        HEIGHT = scanner.nextInt();
        WIDTH = scanner.nextInt();
        scanner.nextLine();
        createMap();
    }

    public void load(int _level) {
        try {
            scanner = new Scanner(new FileReader("res/levels/level" + _level + ".txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        scanner.nextInt();
        HEIGHT = scanner.nextInt();
        WIDTH = scanner.nextInt();
        enemies.removeAll(enemies);
        stillObjects.removeAll(stillObjects);
        flame.removeAll(flame);
        scanner.nextLine();

        createMap();
    }

    public void createMap() {
        createMatrixCoordinates();
        for (int i = 0; i < HEIGHT; i++) {
            String r = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                if (r.charAt(j) == '#') {
                    stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                    map[i][j] = 1;
                } else {
                    stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    if (r.charAt(j) == '*') {
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map[i][j] = 1;
                    }
                    if (r.charAt(j) == 'x') {
                        stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map[i][j] = 1;
                    }
                    if (r.charAt(j) == '1') {
                        enemies.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                        //map[i][j] = 0;
                    }
                    if (r.charAt(j) == '2') {
//                        enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage(), myBomber));
                        enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                        //map[i][j] = 0;
                    }
                    //if (r.charAt(j) == '3') {
                        //enemies.add(new Minvo(j, i, Sprite.minvo_left1.getFxImage()));
                        //map[i][j] = 0;
                    //}
                    //if (r.charAt(j) == '4') {
                        //enemies.add(new Kondoria(j, i, Sprite.kondoria_left1.getFxImage()));
                        //map[i][j] = 0;
                    //}
                    if (r.charAt(j) == '5') {
                        enemies.add(new Doll(j, i, Sprite.doll_left1.getFxImage()));
                        //map[i][j] = 0;
                    }
                    if (r.charAt(j) == 'b') {
                        stillObjects.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map[i][j] = 1;
                    }
                    if (r.charAt(j) == 'f') {
                        stillObjects.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map[i][j] = 1;
                    }
                    if (r.charAt(j) == 's') {
                        stillObjects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        map[i][j] = 1;
                    }
                    if (r.charAt(j) == 'p') {
                        bomberman = new Bomber(j, i, Sprite.player_right.getFxImage());
                        xStart = j;
                        yStart = i;
                        map[i][j] = 0;
                    }
//                    if(r.charAt(j) == ' ') {
//                        map[i][j] = 0;
//                    }
                }
            }
        }
        //stillObjects.sort(new Layer());
    }

    public void createMatrixCoordinates() {
        for(int i = 0; i < HEIGHT; i ++) {
            for(int j = 0; j < WIDTH; j ++) {
                map[i][j] = 0;
            }
        }
    }

    public void update() {
        bomberman.move();
        // animation cho cac entity
        entities.forEach(Entity::update);
        for (int i = 0; i < flame.size(); i ++) {
            flame.get(i).update();
        }
        for (int i = 0; i < enemies.size(); i ++) {
            enemies.get(i).move();
            enemies.get(i).update();
        }
        //bomberman.update();
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
        enemies.forEach(g -> g.render(gc));
        // cai nay cung chua xu ly duoc
        ArrayList<Bomb> bombs = bomberman.getBombs();
        for (Bomb bomb : bombs) {
            bomb.render(gc);
        }
        flame.forEach(g -> g.render(gc));
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

        //Bomber vs Enemies
        for (Enemy enemy : enemies) {
            Rectangle r2 = enemy.getHitBox();
            if (bomber.intersects(r2)) {
                bomberman.stay();
                bomberman.setAlive(false);
                startBomb = 1;
                startFlame = 1;
                startSpeed = 1;
                if(!bomberman.isAlive()) {
                    Timer count = new Timer();
                    count.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            entities.remove(bomberman);
                            bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
                            entities.add(bomberman);
                            count.cancel();
                        }
                    }, 500,1);
                    //sound
                }
            }
        }
        //Enemies vs Bombs
        for (Enemy enemy : enemies) {
            Rectangle r2 = enemy.getHitBox();
            for (Bomb bomb : bomberman.getBombs()) {
                Rectangle r3 = bomb.getHitBox();
                //!bomb.isAllowedToPassThrough(enemy)
                if (r2.intersects(r3)) {
                    enemy.stay();
                    break;
                }
            }
        }
        //Enemies vs StillObjects
        for (Enemy enemy : enemies) {
            Rectangle r2 = enemy.getHitBox();
            for (Entity stillObject : stillObjects) {
                Rectangle r3 = stillObject.getHitBox();
                if (r2.intersects(r3)) {
                    if (stillObject instanceof Wall|| stillObject instanceof Brick) {
                        enemy.stay();
                    } else {
                        enemy.move();
                    }

                }
            }
        }
    }

    /**
     * flame collision.
     *
     */
    public void checkCollisionFlame() {
        //flame vs stillobjects
        for (Flame flame : flame) {
            Rectangle r1 = flame.getHitBox();
            for (Entity stillObject : stillObjects) {
                Rectangle r2 = stillObject.getHitBox();
                //!stillObject instanceof Item
                if (r1.intersects(r2)) {
                    stillObject.setAlive(false);
                }
            }
            //flame vs enemies
            for (Enemy enemy : enemies) {
                Rectangle r2 = enemy.getHitBox();
                if (r1.intersects(r2)) {
                    enemy.setAlive(false);
                }
            }
            //flame vs bomberman
            Rectangle r2 = bomberman.getHitBox();
            if (r1.intersects(r2)) {

                bomberman.setAlive(false);
                startBomb = 1;
                startFlame = 1;
                startSpeed = 1;
            }
                if (!bomberman.isAlive()) {
                    bomberman.stay();
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

