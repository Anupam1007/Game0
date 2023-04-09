package gameWithOops.board;

import gameWithOops.row.Row;
import gameWithOops.gameComponents.GameComponent;
import gameWithOops.gameComponents.GameComponentFactory;
import gameWithOops.gameComponents.GameStatus;
import gameWithOops.userComponent.UserComponent;
import gameWithOops.userComponent.UserComponentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    public int getSIZE_X() {
        return SIZE_X;
    }

    public int getSIZE_Y() {
        return SIZE_Y;
    }

    public int getSpeed() {
        return speed;
    }

    public UserComponentService getUserComponentService() {
        return userComponentService;
    }

    public int getPoints() {
        return points;
    }

    private final int SIZE_X;

    private final int SIZE_Y;

    private final int speed;

    List<Row> rows;

    Random random;

    GameStatus gameStatus;

    public List<Integer> getDistFromLeftMarginList() {
        return distFromLeftMarginList;
    }

    List<Integer> distFromLeftMarginList;
    private final UserComponentService userComponentService;

    /*
    1. this list should enforce a max size - SIZE_Y/100 dist between each being 100 denom
    2. this list should initially be empty
    3. this list should be dynamically populated as game proceeds

     */
    List<GameComponent> gameComponents;

    public void setPoints(int points) {
        this.points = points;
    }

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
    }

    public int getSizeOfComponentToBeFittedX() {
        return (int) (SIZE_X / Math.sqrt(SIZE_X));
    }

    public int getSizeOfComponentToBeFittedY() {
        return (int) (SIZE_Y / Math.sqrt(SIZE_Y));
    }
}
