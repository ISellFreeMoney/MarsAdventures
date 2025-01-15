package game;

import core.Size;
import game.state.GameState;
import game.state.State;
import input.Input;
import display.Display;

public class Game {

    public static int SPRITE_SIZE = 32;

    private Display display;
    private Input input;
    private State state;
    

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
