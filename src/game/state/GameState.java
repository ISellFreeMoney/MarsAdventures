package game.state;

import controller.PlayerController;
import core.Size;
import entity.Player;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;

public class GameState extends State{


    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        spriteLibrary = new SpriteLibrary();
        Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjects.add(player);
        gameMap = new GameMap(new Size(40,40), spriteLibrary);
        camera.focusOn(player);
    }


}
