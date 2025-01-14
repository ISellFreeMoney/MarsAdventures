package tile;

public class TileMap{
    Tile[][] map;
    int mapSize;

    public TileMap (Tile[][] map, int mapSize){
        this.map = map;
        this.mapSize = mapSize;
    }

    public TileMap(int mapSize){
        this.map = new Tile[mapSize][mapSize];
        this.mapSize = mapSize;
    }

    public void setTile(int[] coords, Tile tile){
        map[coords[0]][coords[1]] = tile;
    }

    public Tile getTile(int[] coords){
        return map[coords[0]][coords[1]];
    }

}
