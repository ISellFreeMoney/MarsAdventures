package fr.MarsAdventure;

import fr.MarsAdventure.game.Game;
import fr.MarsAdventure.game.GameLoop;

public class Launcher {
    public static void main(String[] args) {
        new Thread(new GameLoop(new Game(800, 600))).start();
    }
}
