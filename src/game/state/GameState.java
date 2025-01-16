package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Position;
import core.Size;
import entity.NPC;
import entity.Player;
import game.Game;
import input.Input;
import map.GameMap;

import java.util.Arrays;

public class GameState extends State{


    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(30,30), spriteLibrary);
        initializeCharacter();
    }

    private void initializeCharacter() {
        Player player = new Player(new PlayerController(input), spriteLibrary);
        NPC npc = new NPC(new NPCController(), spriteLibrary);
        npc.setPosition(new Position(3 * Game.SPRITE_SIZE, 4 * Game.SPRITE_SIZE));
        gameObjects.addAll(Arrays.asList(player,npc));
        camera.focusOn(player);
    }


}
