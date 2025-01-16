package fr.MarsAdventure.ai.state;

import fr.MarsAdventure.ai.AITransition;
import fr.MarsAdventure.entity.NPC;
import fr.MarsAdventure.game.state.State;

public abstract class AIState {

    private final AITransition transition;

    public AIState (){
        this.transition = initializeTransition();
    }

    protected abstract AITransition initializeTransition();
    public abstract void update(State state, NPC currentCharacter);

    public boolean shouldTransition(State state, NPC currentCharacter){
        return transition.shouldTransition(state, currentCharacter);
    }

    public String getNextState(){
        return  transition.getNextState();
    }

}
