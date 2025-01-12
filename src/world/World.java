package world;

import main.GamePanel;
import tile.Tile;
import tile.TileManager;
import utils.OpenSimplex2S;

import java.io.FileWriter;
import java.io.IOException;

public class World {

    //Size in Tile numbers

    private final GamePanel gp;
    private final Tile[] tiles;
    private Tile[][] worldTiles;
    private int[][] saveFile;


    public World(GamePanel gp, TileManager tileM){
        this.gp = gp;
        this.tiles = tileM.getTileImage();
    }

    public void generateWorld(){
        int worldXSize = gp.mapWidth;
        int worldYSize = gp.mapHeight;
        int seed = gp.seed;
        worldTiles = new Tile[worldXSize][worldYSize];
        saveFile = new int[worldXSize][worldYSize];
        for (int i = 0; i < worldXSize; i++) {
            for (int j = 0; j < worldYSize; j++) {
                double index = (OpenSimplex2S.noise3_ImproveXY(seed, i * 0.025, j * 0.025, 0.0));
                if(index < -0.30){
                    // Water < 0
                    worldTiles[i][j] = tiles[20];
                    saveFile[i][j] = 20;
                    if (index < -0.50) {
                        worldTiles[i][j] = tiles[45];
                        saveFile[i][j] = 45;
                    }
                } else if ( -0.30 <= index && index < 0.1) {
                    worldTiles[i][j] = tiles[17];
                    saveFile[i][j] = 17;
                } else {
                    // Grass > 0.5
                    worldTiles[i][j] = tiles[14];
                    saveFile[i][j] = 14;
                }
            }
        }
        saveWorld();
    }

    public Tile[][] getTiles() {
        return worldTiles;
    }

    public void saveWorld(){
        int worldXSize = gp.mapWidth;
        int worldYSize = gp.mapHeight;
        try {

            FileWriter save = new FileWriter("save.txt");
            for (int i = 0; i < worldXSize; i++) {
                for (int j = 0; j < worldYSize; j++) {
                    save.write(saveFile[i][j] + " ");
                    System.out.println(saveFile[i][j]);
                }
                save.write(System.lineSeparator());
            }
            save.flush();
            save.close();
        } catch (IOException e){
            System.err.println("Failed saving world: " + e.getMessage());
        }
    }
}
