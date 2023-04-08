package gameWithOops.gameComponents;

import javax.swing.*;
import java.awt.*;

public class GameComponentFactory {
    public static Image getImageCustom(Board board, String path) {
        ImageIcon imageIcon = new ImageIcon(Board.class.getResource(path));
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(board.getSizeOfComponentToBeFittedX(), board.getSizeOfComponentToBeFittedY(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        return imageIcon.getImage();
    }

    public static GameComponent getGameComponent(Board board, GameComponentType gameComponentType, int x, int y) {
        if (gameComponentType.equals(GameComponentType.APPLE)) {
            return new Apple(x, y, 2, getImageCustom(board, "../ImagesFolder/apple.png"));
        } else if (gameComponentType.equals(GameComponentType.COOKIE)) {
            return new Cookie(x, y, 2, getImageCustom(board, "../ImagesFolder/cookie.png"));
        } else if (gameComponentType.equals(GameComponentType.USER)) {
            return new Cookie(x, y, 2, getImageCustom(board, "../ImagesFolder/basket.png"));
        } else throw new RuntimeException("invalid Type");
    }

//    public static GameComponent getGameComponent(Board board, GameComponentType gameComponentType) {
//        if (gameComponentType.equals(GameComponentType.APPLE)) {
//            return new Apple(board.getSIZE_X() / 4, 0, 2, getImageCustom(board, "../ImagesFolder/apple.png"));
//        } else if (gameComponentType.equals(GameComponentType.COOKIE)) {
//            return new Cookie(board.getSIZE_X() * 3 / 4, 0, 2, getImageCustom(board, "../ImagesFolder/cookie.png"));
//        } else if (gameComponentType.equals(GameComponentType.USER)) {
//            return new Cookie(board.getSIZE_X() * 3 / 4, 0, 2, getImageCustom(board, "../ImagesFolder/basket.png"));
//        } else throw new RuntimeException("invalid Type");
//    }
}
