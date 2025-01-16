package fr.MarsAdventure.entity;

import fr.MarsAdventure.controller.Controller;
import fr.MarsAdventure.entity.effect.Caffeinated;
import fr.MarsAdventure.gfx.SpriteLibrary;

public class Player extends MovingEntity {

    public Player(Controller controller, SpriteLibrary spriteLibrary){
        super(controller, spriteLibrary);
        effects.add(new Caffeinated());
    }

}
