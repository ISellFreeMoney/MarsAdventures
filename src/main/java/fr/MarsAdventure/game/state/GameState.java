package fr.MarsAdventure.game.state;

import fr.MarsAdventure.controller.NPCController;
import fr.MarsAdventure.controller.PlayerController;
import fr.MarsAdventure.core.Size;
import fr.MarsAdventure.entity.NPC;
import fr.MarsAdventure.entity.Player;
import fr.MarsAdventure.entity.action.Attack;
import fr.MarsAdventure.entity.effect.Poisoned;
import fr.MarsAdventure.input.Input;
import fr.MarsAdventure.map.GameMap;

public class GameState extends State{


    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(500,500), spriteLibrary);
        initializeCharacter();
        initializeNPCs(1000);
    }

    private void initializeCharacter() {
        Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjects.add(player);
        camera.focusOn(player);
    }

    private void initializeNPCs(int numberOfNPCs){
        for (int i = 0; i < numberOfNPCs; i++) {
            NPC npc = new NPC(new NPCController(), spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());
            npc.addEffect(new Poisoned());
            gameObjects.add(npc);
        }
    }


}
