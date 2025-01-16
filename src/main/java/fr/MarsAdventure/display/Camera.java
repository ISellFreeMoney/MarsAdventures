package fr.MarsAdventure.display;

import fr.MarsAdventure.core.Position;
import fr.MarsAdventure.core.Size;
import fr.MarsAdventure.entity.GameObject;
import fr.MarsAdventure.game.Game;
import fr.MarsAdventure.game.state.State;

import java.awt.*;
import java.util.Optional;

public class Camera {

    private static final int SAFETY_SPACE = 2 * Game.SPRITE_SIZE;

    private final Position position;
    private final Size windowSize;

    private Rectangle viewBounds;

    private Optional<GameObject> objectWithFocus;

    public Camera(Size windowSize){
        this.position = new Position(0, 0);
        this.windowSize = windowSize;
        calculateViewBounds();
    }

    private void calculateViewBounds() {
        viewBounds = new Rectangle(
                position.intX(),
                position.intY(),
                windowSize.getWidth() + SAFETY_SPACE,
                windowSize.getHeight() + SAFETY_SPACE);
    }

    public void focusOn(GameObject object) {
        this.objectWithFocus = Optional.of(object);
    }

    public Position getPosition() {
        return position;
    }

    public void update(State state){
        if(objectWithFocus.isPresent()){
            Position objectPosition = objectWithFocus.get().getPosition();

            this.position.setX(objectPosition.intX() - windowSize.getWidth() / 2);
            this.position.setY(objectPosition.intY() - windowSize.getHeight() / 2);

            clampWithinBounds(state);
            calculateViewBounds();
        }
    }

    private void clampWithinBounds(State state){
        if(position.getX() < 0){
            position.setX(0);
        }

        if(position.getY() < 0){
            position.setY(0);
        }

        if(position.getX() + windowSize.getWidth() > state.getGameMap().getWidth()){
            position.setX(state.getGameMap().getWidth() - windowSize.getWidth());
        }

        if(position.getY() + windowSize.getHeight() > state.getGameMap().getHeight()){
            position.setY(state.getGameMap().getHeight() - windowSize.getHeight());
        }
    }

    public boolean isInView(GameObject gameObject) {
        return viewBounds.intersects(new Rectangle(
                gameObject.getPosition().intX(),
                gameObject.getPosition().intY(),
                gameObject.getSize().getWidth(),
                gameObject.getSize().getHeight()
        ));
    }

    public Size getSize() {
        return windowSize;
    }
}
