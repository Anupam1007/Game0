package gameWithOops.row;

import gameWithOops.behaviours.FreeFall;
import gameWithOops.board.Board;
import gameWithOops.gameComponents.GameComponent;

import java.util.List;
import java.util.Random;

public class RowService  implements FreeFall {
    private final List<Row> rows;
    Row row;

    public RowService(List<Row> rows){
        this.rows = rows;
    }
    @Override
    public void fallDown(Row row, int speed) {
        row.setY(row.getY() + speed);
    }

    public void rearrange(Row row) {
        // rearrange gameComponentList

        Random r = new Random();
        for (int i = 0; i < row.getGameComponentList().size(); i++) {
            int rand = r.nextInt(row.getGameComponentList().size());
            GameComponent comp1 = row.getGameComponentList().get(i);
            GameComponent comp2 = row.getGameComponentList().get(rand);
            int temp = comp1.getX();
            comp1.setX(comp2.getX());
            comp2.setX(temp);
        }
    }
}
