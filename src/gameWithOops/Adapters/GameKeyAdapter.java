package gameWithOops.Adapters;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

//        if ((key == KeyEvent.VK_LEFT) && (!rightDirection) && batPos - 100 > 0) {
//            leftDirection = true;
//        }
//
//        if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
//            rightDirection = true;
//
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
