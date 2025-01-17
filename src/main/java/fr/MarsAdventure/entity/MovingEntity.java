package fr.MarsAdventure.entity;

import fr.MarsAdventure.controller.Controller;
import fr.MarsAdventure.core.Direction;
import fr.MarsAdventure.core.Motion;
import fr.MarsAdventure.entity.action.Action;
import fr.MarsAdventure.entity.action.Attack;
import fr.MarsAdventure.entity.effect.Effect;
import fr.MarsAdventure.entity.effect.Poisoned;
import fr.MarsAdventure.game.state.State;
import fr.MarsAdventure.gfx.AnimationManager;
import fr.MarsAdventure.gfx.SpriteLibrary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MovingEntity extends GameObject{

    protected Controller controller;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;
    protected List<Effect> effects;
    protected Optional<Action> action;

    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary){
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        direction = Direction.S;
        animationManager = new AnimationManager(spriteLibrary.getUnit("player"));
        effects = new ArrayList<>();
        action = Optional.empty();
    }

    @Override
    public void update(State state) {
        handleAction(state);
        handleMotion();
        motion.update(controller);
        animationManager.update(direction);
        effects.forEach(effect -> effect.update(state, this));

        manageDirection();
        decideAnimation();

        position.apply(motion);

        cleanUp();
    }

    private void handleMotion() {
        if (!action.isPresent()){
            motion.update(controller);
        } else {
            motion.stop();
        }

    }

    private void handleAction(State state) {
        action.ifPresent(value -> value.update(state, this));
    }

    private void cleanUp() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);
        if(action.isPresent() && action.get().isDone()){
            action = Optional.empty();
        }
    }

    private void decideAnimation() {
        if (action.isPresent()) {
            animationManager.playAnimation(action.get().getAnimationName());
        }else if(motion.isMoving()){
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

    public void perform(Action action) {
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }
}
