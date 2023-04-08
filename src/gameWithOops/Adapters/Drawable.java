package gameWithOops.Adapters;

import java.awt.*;
import java.awt.image.ImageObserver;

// use adapter, since we want third party api for drawing
public interface Drawable {
    public void draw(Image image, int x, int y, ImageObserver observer);

    public void setColor(Color color);

    public void setFont(Font font);

    public void drawString(String msg, int x, int y);

    public void drawLine(int x1, int y1, int x2, int y2);
}
