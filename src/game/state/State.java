package game.state;

import core.Size;
import display.Camera;
import entity.GameObject;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;

import java.util.ArrayList;
import java.util.List;

public abstract class State {

    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;

    public State(Size windowsize, Input input){
        this.input = input;
        gameObjects = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        camera = new Camera(windowsize);
    }

    public void update(){
        gameObjects.forEach(GameObject::update);
        camera.update(this);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Camera getCamera() {
        return this.camera;
    }
}
