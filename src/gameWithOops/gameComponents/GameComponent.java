package gameWithOops.gameComponents;

import java.awt.*;

public abstract class GameComponent {

    private Image image;
    private int x;


    public GameComponent(int x, Image image) {
        this.image = image;
        this.x = x;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


}
