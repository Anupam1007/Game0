package gameWithOops.row;

import gameWithOops.board.Board;
import gameWithOops.gameComponents.GameComponent;
import gameWithOops.gameComponents.GameComponentFactory;
import gameWithOops.gameComponents.GameComponentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Row {
    int y;
    private final List<GameComponent> gameComponentList;
    List<GameComponentType> listOfTypes = new ArrayList<>();

    public Row(int y, int numOfGameComponent, List<Integer> distFromLeftMarginList, Board board) {
        listOfTypes.add(GameComponentType.APPLE);
        listOfTypes.add(GameComponentType.COOKIE);

        this.y = y;
        gameComponentList = new ArrayList<>(numOfGameComponent);
        Random random = new Random();

        /*
        this ensures that two consecutive items in a row aren't same
         */
        int prev=-1;
        for (int dist : distFromLeftMarginList) {
            int r = random.nextInt(numOfGameComponent);

            if(prev==r){
                gameComponentList.add(
                        GameComponentFactory.getGameComponent(
                                board, listOfTypes.get((r+1)%listOfTypes.size()), dist));
            } else {
                gameComponentList.add(
                    GameComponentFactory.getGameComponent(
                            board, listOfTypes.get(r), dist));
            }

            prev=r;
        }
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<GameComponent> getGameComponentList() {
        return this.gameComponentList;
    }

}
