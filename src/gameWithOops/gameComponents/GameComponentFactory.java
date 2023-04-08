package gameWithOops.gameComponents;

import gameWithOops.constants.Constant;

import javax.swing.*;
import java.awt.*;

public class GameComponentFactory {
    public static Image getImageCustom(Board board, String path) {
        ImageIcon imageIcon = new ImageIcon(Board.class.getResource(path));
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(board.getSizeOfComponentToBeFittedX(),
                board.getSizeOfComponentToBeFittedY(),
                java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        return imageIcon.getImage();
    }

    public static GameComponent getGameComponent(Board board, GameComponentType gameComponentType, int x) {
        if (gameComponentType.equals(GameComponentType.APPLE)) {
            return new Apple(x, getImageCustom(board, Constant.applePath));
        } else if (gameComponentType.equals(GameComponentType.COOKIE)) {
            return new Cookie(x, getImageCustom(board, Constant.cookiePath));
        } else throw new RuntimeException("invalid Type");
    }

    public static UserComponent getUserComponent(Board board, int x) {
        return new UserComponent(x, getImageCustom(board,
                Constant.basketPath),
                board.getSizeOfComponentToBeFittedY());
    }
}
