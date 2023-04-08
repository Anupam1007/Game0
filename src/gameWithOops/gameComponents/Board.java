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
    GameComponent userComponent;

    public <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public Board(int SIZE_X, int SIZE_Y, int capacity, int speed) {
        this.SIZE_Y = SIZE_Y;
        this.SIZE_X = SIZE_X;
        this.capacity = capacity;
        this.speed = speed;
        random = new Random();

        rows = new ArrayList<>();


        gameComponents = new ArrayList<>();
        int distBetweenTwoComponent = SIZE_Y / (2 * capacity);
        int leftX = SIZE_X / 4;
        int rightX = SIZE_X * 3 / 4;
        int[] leftAndRight = new int[]{leftX, rightX};
        // r.nextInt(2)


        for (int i = 0; i < capacity; i++) {
            // doesnt ensure only one object in a row
            int id = random.nextInt(2);
            rows.add(new Row(-1 * (i * SIZE_Y / capacity), 2, SIZE_X, this));
        }
        //userComponent = GameComponentFactory.getGameComponent(this, GameComponentType.USER);

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
    }

    //@Override
    public void actionPerformed(ActionEvent e) {
        for (Row row : rows) {
            row.fallDown(speed);

            if (row.getY() >= SIZE_Y) {
                row.rearrange();
                row.setY(0);
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
