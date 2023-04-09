package gameWithOops.board;

import gameWithOops.Adapters.GameKeyAdapter;
import gameWithOops.row.Row;
import gameWithOops.gameComponents.GameComponent;
import gameWithOops.gameComponents.GameStatus;
import gameWithOops.row.RowService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardService extends JPanel implements ActionListener {

    Board board;
    Timer timer;

    RowService rowService;
    public BoardService(int SIZE_X, int SIZE_Y, int capacity, int speed, int distinctGameComponent) {
        this.board = new Board(SIZE_X, SIZE_Y, capacity, speed, distinctGameComponent);

        addKeyListener(new GameKeyAdapter(board));
        setFocusable(true);
        this.setPreferredSize(new Dimension(SIZE_X, SIZE_Y));

        timer = new Timer(10, this);
        timer.start();

        rowService = new RowService(board.rows);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (Row row : board.rows) {
            for (GameComponent gameComponent : row.getGameComponentList()) {
                graphics.drawImage(gameComponent.getImage(), gameComponent.getX(), row.getY(), this);
            }
        }
        graphics.drawImage(board.getUserComponent().getImage(),
                board.getUserComponent().getX(),
                board.getSIZE_Y() - board.getSizeOfComponentToBeFittedY(),
                this);
        showScore(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Row row : board.rows) {
            rowService.fallDown(row, board.getSpeed());

            if (row.getY() >= board.getSIZE_Y() - 2 * board.getUserComponent().getSize()) {
                // check if cookie collide with basket or not
                if (board.getUserComponentService().isGameOver(row)) {
                    gameOver(getGraphics());
                } else {
                    if (board.getUserComponentService().caughtSuccessfuly(row)) {
                        board.setPoints(board.getPoints() + 1);
                    }
                }

                rowService.rearrange(row);
                row.setY(-2 * board.getUserComponent().getSize());
            }
        }

        if (board.gameStatus.equals(GameStatus.INPROGRESS)) {
            repaint();
        }
    }

    private void showScore(Graphics graphics) {
        String msg = String.valueOf(board.getPoints());
        Font small = new Font("Helvetica", Font.BOLD, 16);

        graphics.setColor(Color.black);
        graphics.setFont(small);
        graphics.drawString(msg, (board.getSIZE_X()) / 2, board.getSizeOfComponentToBeFittedY());
    }

    private void gameOver(Graphics graphics) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        graphics.setColor(Color.BLACK);
        graphics.setFont(small);
        graphics.drawString(msg, (board.getSIZE_X() - metr.stringWidth(msg)) / 2, board.getSIZE_Y() / 2);

        board.gameStatus = GameStatus.OVER;
        timer.stop();
        //System.exit(0);
    }
}
