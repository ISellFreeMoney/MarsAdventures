package fr.MarsAdventure.game.state;

import fr.MarsAdventure.core.Position;
import fr.MarsAdventure.core.Size;
import fr.MarsAdventure.display.Camera;
import fr.MarsAdventure.entity.GameObject;
import fr.MarsAdventure.game.Time;
import fr.MarsAdventure.gfx.SpriteLibrary;
import fr.MarsAdventure.input.Input;
import fr.MarsAdventure.map.GameMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class State {

    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Time time;

    public State(Size windowsize, Input input){
        this.input = input;
        gameObjects = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        camera = new Camera(windowsize);
        time = new Time();
    }

    public void update(){
        sortObjectByPosition();
        gameObjects.forEach(gameObject -> gameObject.update(this));
        camera.update(this);
    }

    private void sortObjectByPosition() {
        gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
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

    public Time getTime() {
        return time;
    }

    public Position getRandomPosition() {
        return gameMap.getRandomPosition();
    }
}
