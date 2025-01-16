package fr.MarsAdventure.entity;

import fr.MarsAdventure.controller.Controller;
import fr.MarsAdventure.core.Direction;
import fr.MarsAdventure.core.Motion;
import fr.MarsAdventure.entity.effect.Effect;
import fr.MarsAdventure.game.state.State;
import fr.MarsAdventure.gfx.AnimationManager;
import fr.MarsAdventure.gfx.SpriteLibrary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MovingEntity extends GameObject{

    protected Controller controller;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;
    protected List<Effect> effects;

    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary){
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        direction = Direction.S;
        animationManager = new AnimationManager(spriteLibrary.getUnit("player"));
        effects = new ArrayList<>();
    }

    @Override
    public void update(State state) {
        motion.update(controller);
        animationManager.update(direction);
        effects.forEach(effect -> effect.update(state, this));

        manageDirection();
        decideAnimation();

        position.apply(motion);

        cleanUp();
    }

    private void cleanUp() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);
    }

    private void decideAnimation() {
        if(motion.isMoving()){
            animationManager.playAnimation("WALK");
        } else {
            animationManager.playAnimation("IDLE");
        }
    }

    @Override
    public Image getSprite() {
        return animationManager.getSpriteSet();
    }

    private void manageDirection(){
        if(motion.isMoving()) {
            this.direction = Direction.fromMotion(motion);
        }
    }

    public Controller getController() {
        return controller;
    }

    public void multiplySpeed(double speedMultiplier) {
        motion.multiply(speedMultiplier);
    }
}
