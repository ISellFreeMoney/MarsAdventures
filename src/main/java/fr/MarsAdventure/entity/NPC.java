package fr.MarsAdventure.entity;

import fr.MarsAdventure.ai.AIManager;
import fr.MarsAdventure.controller.Controller;
import fr.MarsAdventure.game.state.State;
import fr.MarsAdventure.gfx.AnimationManager;
import fr.MarsAdventure.gfx.SpriteLibrary;

public class NPC extends MovingEntity{
    private final AIManager aiManager;

    public NPC(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getUnit("player"));
        aiManager = new AIManager();
    }


    @Override
    public void update(State state){
        super.update(state);
        aiManager.update(state, this);
    }

}
