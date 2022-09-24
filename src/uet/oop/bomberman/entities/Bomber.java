package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Bomber extends Entity {
    public Bomber(int x, int y, Image img ) {
        super( x, y, img);
    }
    @Override
    public void update(KeyEvent e) {
        int button = e.getKeyCode();
        if(button == KeyEvent.VK_W) y--;
        else if (button == KeyEvent.VK_S) y++;
        else if (button == KeyEvent.VK_D) x++;
        else if (button == KeyEvent.VK_A) x--;

    }

    }


