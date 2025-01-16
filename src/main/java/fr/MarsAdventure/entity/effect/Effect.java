package fr.MarsAdventure.entity.effect;

import fr.MarsAdventure.entity.MovingEntity;
import fr.MarsAdventure.game.state.State;

public class Effect {

    private int lifeSpanInUpdates;

    public Effect(int lifeSpanInUpdates) {
        this.lifeSpanInUpdates = lifeSpanInUpdates;
    }

    public void update(State state, MovingEntity entity){
        lifeSpanInUpdates--;
    }

    public boolean shouldDelete(){
        return lifeSpanInUpdates <= 0;
    }
}
