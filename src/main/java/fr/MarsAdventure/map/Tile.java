package fr.MarsAdventure.map;

import fr.MarsAdventure.gfx.SpriteLibrary;

import java.awt.*;

public class Tile {

    private final Image sprite;

    public Tile(SpriteLibrary spriteLibrary){
        this.sprite = spriteLibrary.getTile("ground1");
    }

    public Image getSprite() {
        return sprite;
    }
}
