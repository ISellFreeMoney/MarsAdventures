package fr.MarsAdventure.entity;

import fr.MarsAdventure.core.Position;
import fr.MarsAdventure.core.Size;
import fr.MarsAdventure.game.state.State;

import java.awt.*;

public abstract class GameObject {
    protected Position position;
    protected Size size;

    public GameObject(){
        position = new Position(50,50);
        size = new Size(50,50);
    }

    public abstract void update(State state);
    public abstract Image getSprite();
    public Position getPosition(){
        return position;
    }

    public Size getSize() {
        return this.size;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
