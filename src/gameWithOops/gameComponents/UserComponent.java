package gameWithOops.gameComponents;

import java.awt.*;

public class UserComponent {

    private int x;
    private Image image;
    private int size;

    public UserComponent(int x, Image image, int size) {
        this.x = x;
        this.image = image;
        this.size=size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Image getImage() {
        return image;
    }

    public int getSize() {
        return size;
    }


    public void setSize(int size) {
        this.size = size;
    }

    public void moveRight() {
    }

    public void moveLeft() {
    }
}
