package fr.MarsAdventure.ai;

import fr.MarsAdventure.entity.NPC;
import fr.MarsAdventure.game.state.State;

public interface AICondition {
    boolean isMet(State state, NPC currentCharacter);
}
