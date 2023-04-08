package gameWithOops.gameComponents;

import java.awt.*;

public abstract class GameComponent {

    private Image image;
    private int x;


    private int y;
    private int speed;


    public GameComponent(int x, int y, int speed, Image image) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Image getImage() {
        return image;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


}
