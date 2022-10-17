package uet.oop.bomberman.entities.Powerups;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Powerups extends Entity {
    public Powerups(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
    }

    @Override
    public void update() {

    }
}
