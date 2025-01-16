package entity;

import controller.Controller;
import core.Position;
import gfx.SpriteLibrary;

public class Player extends MovingEntity {


    public Player(Controller controller, SpriteLibrary spriteLibrary){
        super(controller, spriteLibrary);
    }

//    @Override
//    public void update(State state) {
//        super.update(state);
//    }


    @Override
    public Position getPosition() {
        return position;
    }
}
