package gameWithOops.Adapters;

import gameWithOops.gameComponents.Board;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeyAdapter extends KeyAdapter {
    Board board;

    public GameKeyAdapter(Board board) {
        this.board = board;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            board.getUserComponent().moveLeft();
        }

        if (key == KeyEvent.VK_RIGHT) {
            board.getUserComponent().moveRight();
        }
    }
}
