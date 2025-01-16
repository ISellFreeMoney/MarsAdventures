package fr.MarsAdventure.map;

import fr.MarsAdventure.core.Position;

import fr.MarsAdventure.display.Camera;
import fr.MarsAdventure.core.Size;
import fr.MarsAdventure.game.Game;
import fr.MarsAdventure.gfx.SpriteLibrary;

import java.util.Arrays;

public class GameMap {

    private final int SAFETY_SPACE = 2;

    private final Tile[][] tiles;

    public GameMap(Size size, SpriteLibrary spriteLibrary){
        tiles = new Tile[size.getWidth()][size.getHeight()];
        initializeTiles(spriteLibrary);
    }

    private void initializeTiles(SpriteLibrary spriteLibrary){
        for(Tile[] row: tiles){
            Arrays.fill(row, new Tile(spriteLibrary));
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return tiles.length * Game.SPRITE_SIZE;
    }

    public int getHeight(){
        return tiles[0].length * Game.SPRITE_SIZE;
    }

    public Position getRandomPosition() {
        double x = Math.random() * tiles.length * Game.SPRITE_SIZE;
        double y = Math.random() * tiles[0].length * Game.SPRITE_SIZE;
        return new Position(x, y);
    }

    public Position getViewableStartingGridPosition(Camera camera) {
        return new Position(
                Math.max(0, camera.getPosition().getX() / Game.SPRITE_SIZE - SAFETY_SPACE),
                Math.max(0, camera.getPosition().getY() / Game.SPRITE_SIZE - SAFETY_SPACE)
        );
    }

    public Position getViewableEndingGridPosition(Camera camera) {
        return new Position(
                Math.min(tiles.length, camera.getPosition().getX() / Game.SPRITE_SIZE + camera.getSize().getWidth() / Game.SPRITE_SIZE + SAFETY_SPACE),
                Math.min(tiles[0].length, camera.getPosition().getY() / Game.SPRITE_SIZE + camera.getSize().getHeight() / Game.SPRITE_SIZE + SAFETY_SPACE)
        );
    }
}
