package fr.MarsAdventure.ai.state;

import fr.MarsAdventure.ai.AITransition;
import fr.MarsAdventure.entity.NPC;
import fr.MarsAdventure.game.state.State;

public class Stand extends AIState{
    private int updatesAlive;

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("wander", (((state, currentCharacter) -> updatesAlive >= state.getTime().getUpdatesFromSeconds(3))));
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        updatesAlive++;
    }
}
