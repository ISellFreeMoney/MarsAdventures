package entity;

import ai.AIManager;
import controller.Controller;
import core.Position;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

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
