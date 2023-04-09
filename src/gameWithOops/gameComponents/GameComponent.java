package gameWithOops.gameComponents;

import java.awt.*;

public abstract class GameComponent {

    private Image image;
    private int x;

    private GameComponentType type;

    public GameComponent(int x, Image image, GameComponentType type) {
        this.image = image;
        this.x = x;

        this.type=type;
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

    public GameComponentType getType() {
        return type;
    }
}
