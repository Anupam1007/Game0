package gameWithOops;

import gameWithOops.behaviours.FreeFall;
import gameWithOops.gameComponents.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Row implements FreeFall {
    int y;
    private List<GameComponent> gameComponentList;
    List<GameComponentType> listOfTypes = new ArrayList<>();

    public Row(int y, int numOfGameComponent, List<Integer> distFromLeftMarginList, Board board) {
        listOfTypes.add(GameComponentType.APPLE);
        listOfTypes.add(GameComponentType.COOKIE);

        this.y = y;
        gameComponentList = new ArrayList<>(numOfGameComponent);
        Random random = new Random();

        for(int dist: distFromLeftMarginList){
            int r = random.nextInt(numOfGameComponent);
            gameComponentList.add(
                    GameComponentFactory.getGameComponent(
                            board, listOfTypes.get(r), dist));
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

    public void rearrange() {
        // rearrange gameComponentList
        Random r = new Random();
        for (int i = 0; i < gameComponentList.size(); i++) {
            int rand = r.nextInt(gameComponentList.size());
            GameComponent comp1 = gameComponentList.get(i);
            GameComponent comp2 = gameComponentList.get(rand);
            int temp = comp1.getX();
            comp1.setX(comp2.getX());
            comp2.setX(temp);
        }
    }

    @Override
    public void fallDown(int speed) {
        this.setY(y+speed);
    }
}
