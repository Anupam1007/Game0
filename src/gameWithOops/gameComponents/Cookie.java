package gameWithOops.gameComponents;

import gameWithOops.behaviours.FreeFall;

import java.awt.*;

public class Cookie extends GameComponent implements FreeFall {
    public Cookie(int x, int y, int speed, Image image) {
        super(x, y, speed, image);
    }

    @Override
    public void fallDown() {
        this.setY(this.getY()+this.getSpeed());
    }
}
