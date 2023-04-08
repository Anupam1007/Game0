package gameWithOops.gameComponents;

import java.awt.*;

public class UserComponent {

    private int x;
    private Image image;
    private int size;

    public UserComponent(int x, Image image) {
        this.x = x;
        this.image = image;
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

    public void setImage(Image image) {
        this.image = image;
    }

    public int getSize() {
        return size;
    }


    public void setSize(int size) {
        this.size = size;
    }
}
