package gameWithOops.gameComponents;

import gameWithOops.Adapters.GameKeyAdapter;
import gameWithOops.Adapters.GraphicsAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Board extends JPanel implements ActionListener {
    private int SIZE_X;

    public int getSIZE_X() {
        return SIZE_X;
    }

    public int getSIZE_Y() {
        return SIZE_Y;
    }

    private int SIZE_Y;

    private int capacity;

    private int speed;

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

        gameComponents = new ArrayList<>();
        int distBetweenTwoComponent = SIZE_Y / (2 * capacity);
        int leftX = SIZE_X / 4;
        int rightX = SIZE_X * 3 / 4;
        int[] leftAndRight = new int[]{leftX, rightX};
        // r.nextInt(2)


        for (int i = 0; i < capacity; i++) {
            // doesnt ensure only one object in a row
            int id = random.nextInt(2);
            GameComponent apple = GameComponentFactory.getGameComponent(
                    this, randomEnum(GameComponentType.class), leftAndRight[id], -1 * (i * SIZE_Y / capacity) - distBetweenTwoComponent);
            GameComponent cookie = GameComponentFactory.getGameComponent(
                    this, GameComponentType.COOKIE, leftAndRight[(1+id)%2], -1 * (i * SIZE_Y / capacity));

//            GameComponent apple = GameComponentFactory.getGameComponent(
//                    this, GameComponentType.APPLE, leftAndRight[random.nextInt(2)], -1 * (i * SIZE_Y / capacity) - distBetweenTwoComponent);
//            GameComponent cookie = GameComponentFactory.getGameComponent(
//                    this, GameComponentType.COOKIE, leftAndRight[random.nextInt(2)], -1 * (i * SIZE_Y / capacity));
//            apple.setY(-1 * (i * SIZE_Y / capacity));
//            cookie.setY(-1 * (i * SIZE_Y / capacity));
            gameComponents.add(apple);
            gameComponents.add(cookie);
        }
        //userComponent = GameComponentFactory.getGameComponent(this, GameComponentType.USER);

        addKeyListener(new GameKeyAdapter());
        setFocusable(true);
        this.setPreferredSize(new Dimension(SIZE_X, SIZE_Y));

        timer = new Timer(10, this);
        timer.start();

    }

//    private void drawBalls(Graphics g) {
//        if (inGame) {
//            for (int i = 0; i < numBalls; i++) {
//                g.drawImage(imgG[i], (m.x0) / 6, (downArr0[i]), this);
//                g.drawImage(imgG1[i], m.x0 / 2 + (m.x0) / 6, (downArr2[i]), this);
//            }
//            g.drawImage(m.bat, batPos, (Map.B_Y) - 15, this);
//            leftDirection = false;
//            rightDirection = false;
//
//            String msg = String.valueOf(points);
//            Font small = new Font("Helvetica", Font.BOLD, 16);
//            FontMetrics metr = getFontMetrics(small);
//
//            g.setColor(Color.black);
//            g.setFont(small);
//            g.drawString(msg, (Map.B_X) / 2 - 8, 16);
//
//            Toolkit.getDefaultToolkit().sync();
//
//        } else {
//            gameOver(g);
//        }
//    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawLine(m.x0, m.y0 + 18, m.x1, m.y1);
        //GraphicsAdapter graphicsAdapter = new GraphicsAdapter(g);
        for (GameComponent gameComponent : gameComponents) {
            //gameComponent.setX(gameComponent.getX()-10);
            //gameComponent.setY(gameComponent.getY()+20);
            //graphicsAdapter.draw(gameComponent.getImage(), gameComponent.getX(), gameComponent.getY(), this);
            g.drawImage(gameComponent.getImage(), gameComponent.getX(), gameComponent.getY(), this);
        }
        //Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (GameComponent gameComponent : gameComponents) {
            //gameComponent.setX(gameComponent.getX()-10);
            gameComponent.setY(gameComponent.getY() + 2);
            //graphicsAdapter.draw(gameComponent.getImage(), gameComponent.getX(), gameComponent.getY(), this);
            //g.drawImage(gameComponent.getImage(), gameComponent.getX(), gameComponent.getY(), this);
            if (gameComponent.getY() >= SIZE_Y) {
                gameComponent.setY(0);
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
