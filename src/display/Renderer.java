package display;

import game.Game;
import game.state.State;
import map.Tile;

import java.awt.*;

public class Renderer {
    public void render(State state, Graphics graphics){
        renderMap(state,graphics);
        Camera camera = state.getCamera();
        state.getGameObjects().forEach(gameObject -> graphics.drawImage(
                gameObject.getSprite(),
                gameObject.getPosition().getX() - camera.getPosition().getX() - gameObject.getSize().getWidth() / 2,
                gameObject.getPosition().getY() - camera.getPosition().getY() - gameObject.getSize().getHeight() / 2,
                null
        ));
    }

    private void renderMap(State state, Graphics graphics){
       Tile[][] tiles = state.getGameMap().getTiles();
        Camera camera = state.getCamera();
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                graphics.drawImage(
                        tiles[x][y].getSprite(),
                        x * Game.SPRITE_SIZE - camera.getPosition().getX(),
                        y * Game.SPRITE_SIZE - camera.getPosition().getY(),
                        null
                );
            }
        }
    }
}
