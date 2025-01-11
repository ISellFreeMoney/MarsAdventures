package world;

import main.GamePanel;
import tile.Tile;
import tile.TileManager;
import utils.OpenSimplex2S;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class World {

    //Size in Tile numbers
    private int worldXSize;
    private int worldYSize;
    private GamePanel gp;
    private Tile[] tiles;
    private int SEED;
    private Tile[][] worldTiles;
    private int[][] saveFile;


    public World(GamePanel gp, TileManager tileM){
        this.SEED = gp.seed;
        this.worldXSize = gp.mapWidth;
        this.worldYSize = gp.mapHeight;
        this.gp = gp;
        this.tiles = tileM.getTileImage();
    }

    public void generateWorld(){
        worldTiles = new Tile[worldXSize][worldYSize];
        saveFile = new int[worldXSize][worldYSize];
        for (int i = 0; i < worldXSize; i++) {
            for (int j = 0; j < worldYSize; j++) {
                double index = (OpenSimplex2S.noise3_ImproveXY(0, i * 0.025, j * 0.025, 0.0));
                if(index < -0.30){
                    // Water < 0
                    worldTiles[i][j] = tiles[45];
                    saveFile[i][j] = 45;
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
            e.printStackTrace();
        }
    }
}
