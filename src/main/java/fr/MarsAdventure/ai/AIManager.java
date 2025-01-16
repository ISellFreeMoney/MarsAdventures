package fr.MarsAdventure.ai;

import fr.MarsAdventure.ai.state.AIState;
import fr.MarsAdventure.ai.state.Stand;
import fr.MarsAdventure.ai.state.Wander;
import fr.MarsAdventure.entity.NPC;
import fr.MarsAdventure.game.state.State;

public class AIManager {

    private AIState currentAIState;

    public AIManager(){
        transitionTo("stand");
    }

    public void update(State state, NPC currentCharacter){
        currentAIState.update(state, currentCharacter);
        
        if(currentAIState.shouldTransition(state, currentCharacter)){
            transitionTo(currentAIState.getNextState());
        }
        
    }

    private void transitionTo(String nextState) {
        switch (nextState){
            case "wander":
                currentAIState = new Wander();
                return;
            case "stand":
            default:
                currentAIState = new Stand();
        }
    }
}
