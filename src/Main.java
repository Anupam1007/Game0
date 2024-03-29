import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main extends JFrame {
    public Main() {
        add(new Game());
        setResizable(false);
        pack();
        setTitle("Catch");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}
