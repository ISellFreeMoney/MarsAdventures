package fr.MarsAdventure.game;

import fr.MarsAdventure.core.Size;
import fr.MarsAdventure.game.state.GameState;
import fr.MarsAdventure.game.state.State;
import fr.MarsAdventure.input.Input;
import fr.MarsAdventure.display.Display;

public class Game {

    public static final int SPRITE_SIZE = 32;

    private final Display display;
    private final Input input;
    private final State state;
    

    public Game(int width, int height){
        input = new Input();
        display = new Display(width, height, input);
        state = new GameState(new Size(width, height), input);
    }

    public void update(){
        state.update();
    }

    public void render(){
        display.render(state);
    }
}
