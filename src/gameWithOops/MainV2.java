package gameWithOops;

import gameWithOops.board.Board;
import gameWithOops.board.BoardService;

import javax.swing.*;
import java.awt.*;

public class MainV2 extends JFrame {
    public MainV2() {
        BoardService boardService = new BoardService(400,400, 5, 4, 2);
        add(boardService);
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