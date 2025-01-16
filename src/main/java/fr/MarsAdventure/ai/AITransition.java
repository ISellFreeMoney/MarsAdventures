package fr.MarsAdventure.ai;

import fr.MarsAdventure.entity.NPC;
import fr.MarsAdventure.game.state.State;

public class AITransition {
    private final String nextState;
    private final AICondition condition;

    public AITransition(String nextState, AICondition condition){
        this.condition = condition;
        this.nextState = nextState;
    }

    public boolean shouldTransition(State state, NPC currentCharacter){
        return condition.isMet(state, currentCharacter);
    }

    public String getNextState() {
        return nextState;
    }
}
