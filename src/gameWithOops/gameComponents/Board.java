package gameWithOops.gameComponents;

import gameWithOops.Adapters.GameKeyAdapter;
import gameWithOops.Row;
import gameWithOops.userComponent.UserComponentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends JPanel implements ActionListener {
    private final int SIZE_X;

    private final int SIZE_Y;

    private final int speed;

    List<Row> rows;

    Timer timer;

    Random random;

    GameStatus gameStatus;

    List<Integer> distFromLeftMarginList;
    private final UserComponentService userComponentService;

    /*
    1. this list should enforce a max size - SIZE_Y/100 dist between each being 100 denom
    2. this list should initially be empty
    3. this list should be dynamically populated as game proceeds

     */
    List<GameComponent> gameComponents;
    private int points;

    public UserComponent getUserComponent() {
        return userComponent;
    }

    private final UserComponent userComponent;

    public Board(int SIZE_X, int SIZE_Y, int capacity, int speed, int distinctGameComponent) {
        this.SIZE_Y = SIZE_Y;
        this.SIZE_X = SIZE_X;
        this.speed = speed;
        random = new Random();


        rows = new ArrayList<>();

        gameComponents = new ArrayList<>();
        distFromLeftMarginList = new ArrayList<>();
        gameStatus = GameStatus.INPROGRESS;

        for (int j = 0; j < distinctGameComponent; j++) {
            int distFromLeftMargin = (SIZE_X / (distinctGameComponent + 1)) * (j + 1);
            distFromLeftMarginList.add(distFromLeftMargin);
        }

        for (int i = 0; i < capacity; i++) {
            rows.add(new Row(-1 * (i * SIZE_Y / capacity), distinctGameComponent, distFromLeftMarginList, this));
        }

        userComponent = GameComponentFactory.getUserComponent(this, SIZE_X / 2);
        userComponentService = new UserComponentService(userComponent);

        addKeyListener(new GameKeyAdapter(this));
        setFocusable(true);
        this.setPreferredSize(new Dimension(SIZE_X, SIZE_Y));

        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (Row row : rows) {
            for (GameComponent gameComponent : row.getGameComponentList()) {
                graphics.drawImage(gameComponent.getImage(), gameComponent.getX(), row.getY(), this);
            }
        }
        graphics.drawImage(userComponent.getImage(), userComponent.getX(), SIZE_Y - getSizeOfComponentToBeFittedY(), this);
        showScore(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Row row : rows) {
            row.fallDown(speed);

            if (row.getY() >= SIZE_Y - 2 * userComponent.getSize()) {
                // check if cookie collide with basket or not
                if (userComponentService.isGameOver(row)) {
                    gameOver(getGraphics());
                } else {
                    if (userComponentService.caughtSuccessfuly(row)) {
                        points++;
                    }
                }

                row.rearrange();
                row.setY(-2 * userComponent.getSize());
            }
        }

        if (gameStatus.equals(GameStatus.INPROGRESS)) {
            repaint();
        }
    }

    private void showScore(Graphics graphics) {
        String msg = String.valueOf(points);
        Font small = new Font("Helvetica", Font.BOLD, 16);

        graphics.setColor(Color.black);
        graphics.setFont(small);
        graphics.drawString(msg, (SIZE_X) / 2, getSizeOfComponentToBeFittedY());
    }

    private void gameOver(Graphics graphics) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        graphics.setColor(Color.BLACK);
        graphics.setFont(small);
        graphics.drawString(msg, (SIZE_X - metr.stringWidth(msg)) / 2, SIZE_Y / 2);

        gameStatus = GameStatus.OVER;
        timer.stop();
        //System.exit(0);
    }

    public int getSizeOfComponentToBeFittedX() {
        return (int) (SIZE_X / Math.sqrt(SIZE_X));
    }

    public int getSizeOfComponentToBeFittedY() {
        return (int) (SIZE_Y / Math.sqrt(SIZE_Y));
    }
}
