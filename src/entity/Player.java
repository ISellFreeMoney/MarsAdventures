package entity;

import controller.Controller;
import core.Position;
import gfx.SpriteLibrary;

public class Player extends MovingEntity {

    private Controller controller;

    public Player(Controller controller, SpriteLibrary spriteLibrary){
        super(controller, spriteLibrary);
    }

    @Override
    public void update() {
        super.update();
    }


    @Override
    public Position getPosition() {
        return position;
    }
}
