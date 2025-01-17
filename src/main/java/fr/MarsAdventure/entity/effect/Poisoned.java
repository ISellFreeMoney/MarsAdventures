package fr.MarsAdventure.entity.effect;

import fr.MarsAdventure.entity.MovingEntity;
import fr.MarsAdventure.entity.action.Attack;
import fr.MarsAdventure.game.GameLoop;
import fr.MarsAdventure.game.state.State;

public class Poisoned extends Effect{

    private static final double POISON_RATE = 1d / GameLoop.UPDATES_PER_SECOND / 10;

    public Poisoned(){
        super(1000);
    }

    @Override
    public void update(State state, MovingEntity entity){
        super.update(state, entity);

        if(Math.random() < POISON_RATE){
            entity.perform(new Attack());
        }
    }
}
