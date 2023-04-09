package gameWithOops.userComponent;

import gameWithOops.Row;
import gameWithOops.gameComponents.GameComponent;
import gameWithOops.gameComponents.GameComponentType;
import gameWithOops.gameComponents.UserComponent;

public class UserComponentService {
    private UserComponent userComponent;
    public UserComponentService(UserComponent userComponent){
        this.userComponent = userComponent;
    }

    public boolean isGameOver(Row row){
        for(GameComponent gameComponent: row.getGameComponentList()){
            if(gameComponent.getType()== GameComponentType.APPLE && isCaught(gameComponent)){
                return true;

            }
        }
        return false;
    }

    private boolean isCaught(GameComponent gameComponent) {
        if(gameComponent.getX() == userComponent.getX())
            return true;
        return false;
    }

    public boolean caughtSuccessfuly(Row row) {
        for(GameComponent gameComponent: row.getGameComponentList()){
            if(gameComponent.getType()==GameComponentType.COOKIE && isCaught(gameComponent)){
                return true;
            }
        }
        return false;
    }


}
