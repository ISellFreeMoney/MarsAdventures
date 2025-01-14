package world;

import main.GamePanel;
import tile.Tile;
import tile.TileMap;
import tile.TileType;
import utils.OpenSimplex2S;

public class Worldv2 {
    public final GamePanel gp;
    private TileMap worldTilesId;
//    private final Tile[][] dualGrid;

    public Worldv2(GamePanel gp) {
        this.gp = gp;
        generateWorld();
        WorldDualGrid worldDualGrid = new WorldDualGrid(gp.tileM);
//        dualGrid = worldDualGrid.getRenderTiles();
    }

    public void generateWorld(){
        int worldXSize = gp.mapWidth;
        int worldYSize = gp.mapHeight;
        int seed = gp.seed;
        Tile currentTile = new Tile();
        worldTilesId = new TileMap(500);
        for (int i = 0; i < worldXSize; i++) {
            for (int j = 0; j < worldYSize; j++) {
                double index = (OpenSimplex2S.noise3_ImproveXY(seed, i * 0.025, j * 0.025, 0.0));
                if(index > 0){
                    currentTile.type = TileType.DIRT;
                } else {
                    currentTile.type = TileType.GRASS;
                }
                worldTilesId.setTile(new int[]{i,j}, currentTile);
                /*
                if(index < - 0.30){
                    worldTilesId[i][j] = 102;
                } else if(-0.30 < index && index < 0.1){
                    worldTilesId[i][j] = 100;
                } else {
                    worldTilesId[i][j] = 101;
                }
                */
            }
        }
    }

    public TileMap getWorldTilesId() {
        return worldTilesId;
    }

//    public Tile[][] getDualGrid() {
//        return dualGrid;
//    }
}