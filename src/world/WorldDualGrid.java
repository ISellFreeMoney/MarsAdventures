package world;

import main.GamePanel;
import tile.Tile;
import tile.TileManager;
import tile.TileMap;
import tile.TileType;

import java.util.*;

public class WorldDualGrid {

    public int[][] NEIGHBOURS = new int[][] {
            new int[]{0,0},
            new int[]{0,1},
            new int[]{1,0},
            new int[]{1,1}
    };
    public Dictionary<TileType[], Tile> neighboursToTile = new Hashtable<>();

    public TileMap placeholderTileMap;
    public TileMap displayTileMap;

    public Tile grassPlaceHolderTile;
    public Tile dirtPlaceHolderTile;

    public Worldv2 world;

    public TileManager tileM;

    public Tile[][] tiles;

    public WorldDualGrid(TileManager tileM, Worldv2 world) {
        this.world = world;
        placeholderTileMap = world.getWorldTilesId();
        displayTileMap = new TileMap(50);

        this.tileM = tileM;
        tiles = tileM.getTileImage();
        neighboursToTile.put(new TileType[] {TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS}, tiles[2][1]);
        neighboursToTile.put(new TileType[] {TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.GRASS}, tiles[1][3]);
        neighboursToTile.put(new TileType[] {TileType.DIRT, TileType.DIRT, TileType.GRASS, TileType.DIRT}, tiles[0][0]);
        neighboursToTile.put(new TileType[] {TileType.DIRT, TileType.GRASS, TileType.DIRT, TileType.DIRT}, tiles[0][2]);
        neighboursToTile.put(new TileType[] {TileType.GRASS, TileType.DIRT, TileType.DIRT, TileType.DIRT}, tiles[3][3]);
        neighboursToTile.put(new TileType[] {TileType.DIRT, TileType.GRASS, TileType.DIRT, TileType.GRASS}, tiles[1][0]);
        neighboursToTile.put(new TileType[] {TileType.GRASS, TileType.DIRT, TileType.GRASS, TileType.DIRT}, tiles[3][2]);
        neighboursToTile.put(new TileType[] {TileType.DIRT, TileType.DIRT, TileType.GRASS, TileType.GRASS}, tiles[3][0]);
        neighboursToTile.put(new TileType[] {TileType.GRASS, TileType.GRASS, TileType.DIRT, TileType.DIRT}, tiles[1][2]);
        neighboursToTile.put(new TileType[] {TileType.DIRT, TileType.GRASS, TileType.GRASS, TileType.GRASS}, tiles[1][1]);
        neighboursToTile.put(new TileType[] {TileType.GRASS, TileType.DIRT, TileType.GRASS, TileType.GRASS}, tiles[2][0]);
        neighboursToTile.put(new TileType[] {TileType.GRASS, TileType.GRASS, TileType.DIRT, TileType.GRASS}, tiles[2][2]);
        neighboursToTile.put(new TileType[] {TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.DIRT}, tiles[3][1]);
        neighboursToTile.put(new TileType[] {TileType.DIRT, TileType.GRASS, TileType.GRASS, TileType.DIRT}, tiles[2][3]);
        neighboursToTile.put(new TileType[] {TileType.GRASS, TileType.DIRT, TileType.DIRT, TileType.GRASS}, tiles[0][1]);
        neighboursToTile.put(new TileType[] {TileType.DIRT, TileType.DIRT, TileType.DIRT, TileType.DIRT}, tiles[0][3]);
        refreshDisplayTileMap();
    };
    

    public void setTile(int[] coords, Tile tile) {
        placeholderTileMap.setTile(coords, tile);
    }

    private TileType getPlaceholderTileTypeAt(int[] coords){
        if(placeholderTileMap.getTile(coords) == grassPlaceHolderTile){
            return TileType.GRASS;
        } else {
            return TileType.DIRT;
        }
    }

    protected Tile calculateDisplayTile(int[] coords) {
        TileType topRight = getPlaceholderTileTypeAt(new int[] {coords[0] - NEIGHBOURS[0][0], coords[1] - NEIGHBOURS[0][1]});
        TileType topLeft = getPlaceholderTileTypeAt(new int[] {coords[0] - NEIGHBOURS[1][0], coords[1] - NEIGHBOURS[1][1]});
        TileType botRight = getPlaceholderTileTypeAt(new int[] {coords[0] - NEIGHBOURS[2][0], coords[1] - NEIGHBOURS[2][1]});
        TileType botLeft = getPlaceholderTileTypeAt(new int[] {coords[0] - NEIGHBOURS[3][0], coords[1] - NEIGHBOURS[3][1]});

        TileType[] neighbourList = new TileType[] {topRight, topLeft, botRight, botLeft};
        for (int i = 0; i < neighboursToTile.size(); i++) {

            String compare = Arrays.toString(neighboursToTile.keys().nextElement());
            System.out.println(compare);
        }
        return neighboursToTile.get(neighbourList);
    }

    protected void setDisplayTile(int[] pos){
        for (int i = 0; i < NEIGHBOURS.length; i++) {
            int[] newPos = new int[]{pos[0] + NEIGHBOURS[i][0], pos[1] + NEIGHBOURS[i][1]};
            displayTileMap.setTile(newPos, calculateDisplayTile(newPos));
        }
    }


    public void refreshDisplayTileMap(){
        for (int i = 1; i < 49; i++){
            for (int j = 1; j < 49; j++) {
                setDisplayTile(new int[]{i, j});
            }
        }
    }
}
