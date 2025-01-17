package fr.MarsAdventure.entity.action;

import fr.MarsAdventure.entity.MovingEntity;
import fr.MarsAdventure.game.state.State;

public abstract class Action {
    public abstract void update(State state, MovingEntity entity);
    public abstract boolean isDone();
    public abstract String getAnimationName();
}
