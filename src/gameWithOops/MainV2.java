package gameWithOops;

import gameWithOops.gameComponents.Board;

import javax.swing.*;
import java.awt.*;

public class MainV2 extends JFrame {
    public MainV2() {
        Board board = new Board(400,400, 5, 2, 2);
        add(board);
        setResizable(false);
        pack();
        setTitle("CatchV2");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new MainV2();
            ex.setVisible(true);
        });
    }
}