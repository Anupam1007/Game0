package gameWithOops.Adapters;

import java.awt.*;
import java.awt.image.ImageObserver;

public class GraphicsAdapter implements Drawable {
    private Graphics graphics;

    public GraphicsAdapter(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void draw(Image image, int x, int y, ImageObserver imageObserver) {
        graphics.drawImage(image, x, y, imageObserver);

    }


    @Override
    public void setColor(Color color) {
        graphics.setColor(color);
    }

    @Override
    public void setFont(Font font) {
        graphics.setFont(font);
    }

    @Override
    public void drawString(String msg, int x, int y) {
        graphics.drawString(msg, x, y);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1, y1, x2, y2);
    }


}
