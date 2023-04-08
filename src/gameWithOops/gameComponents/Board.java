package gameWithOops.gameComponents;

import gameWithOops.Adapters.GameKeyAdapter;
import gameWithOops.Row;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends JPanel implements ActionListener {
    private int SIZE_X;

    private int SIZE_Y;

    private int capacity;

    private int speed;

    List<Row> rows;

    Timer timer;

    Random random;

    /*
    1. this list should enforce a max size - SIZE_Y/100 dist between each being 100 denom
    2. this list should initially be empty
    3. this list should be dynamically populated as game proceeds

     */
    List<GameComponent> gameComponents;
    UserComponent userComponent;

    public Board(int SIZE_X, int SIZE_Y, int capacity, int speed) {
        this.SIZE_Y = SIZE_Y;
        this.SIZE_X = SIZE_X;
        this.capacity = capacity;
        this.speed = speed;
        random = new Random();

        rows = new ArrayList<>();

        gameComponents = new ArrayList<>();

        for (int i = 0; i < this.capacity; i++) {
            rows.add(new Row(-1 * (i * SIZE_Y / this.capacity), 2, SIZE_X, this));
        }

        userComponent = GameComponentFactory.getUserComponent(this, SIZE_X / 2);

        addKeyListener(new GameKeyAdapter());
        setFocusable(true);
        this.setPreferredSize(new Dimension(SIZE_X, SIZE_Y));

        timer = new Timer(10, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Row row : rows) {
            for (GameComponent gameComponent : row.getGameComponentList()) {
                g.drawImage(gameComponent.getImage(), gameComponent.getX(), row.getY(), this);
            }
        }
        g.drawImage(userComponent.getImage(), userComponent.getX(), SIZE_Y-getSizeOfComponentToBeFittedY(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Row row : rows) {
            row.fallDown(speed);

            if (row.getY() >= SIZE_Y - 2*userComponent.getSize()) {
                row.rearrange();
                row.setY(- 2*userComponent.getSize());
            }
        }
        repaint();
    }

    public int getSizeOfComponentToBeFittedX() {
        return (int) (SIZE_X / Math.sqrt(SIZE_X));
    }

    public int getSizeOfComponentToBeFittedY() {
        return (int) (SIZE_Y / Math.sqrt(SIZE_Y));
    }

}
