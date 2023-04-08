package gameWithOops.gameComponents;

import gameWithOops.behaviours.FreeFall;

import java.awt.*;

public class Apple extends GameComponent implements FreeFall {
    public Apple(int x, int y, int speed, Image image) {
        super(x, y, speed, image);
    }

    @Override
    public void fallDown() {
        this.setY(this.getY()+this.getSpeed());
    }
}
