package fr.MarsAdventure.entity.action;

import fr.MarsAdventure.entity.MovingEntity;
import fr.MarsAdventure.game.GameLoop;
import fr.MarsAdventure.game.state.State;

public class Attack extends Action{
    private int lifeSpanInSeconds;

    public Attack(){
        lifeSpanInSeconds = GameLoop.UPDATES_PER_SECOND;
    }


    @Override
    public void update(State state, MovingEntity entity) {
        lifeSpanInSeconds--;
    }

    @Override
    public boolean isDone() {
        return lifeSpanInSeconds <= 0;
    }

    @Override
    public String getAnimationName() {
        return "ATTACK";
    }
}
