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
    // Le vrai
//    private int[] underWaterList = {82, 33, 83, 21, 19, 95, 7, 96, 82, 83};
//    private int[] underWaterList = {82, 34, 1, 2, 3, 4, 5, 6, 7, 8};



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
        // Generate basic tiles
        for (int i = 0; i < worldXSize; i++) {
            for (int j = 0; j < worldYSize; j++) {
                double index = (OpenSimplex2S.noise3_ImproveXY(seed, i * 0.025, j * 0.025, 0.0));
                if(index < -0.30){
                    updateWorld(i,j,102);
                } else if ( -0.30 <= index && index < 0.1) {
                    updateWorld(i,j,101);
                } else if (index < 0.14 && index > 0.1){
                    updateWorld(i,j,42);
                }else if (index > 0.14){
                    updateWorld(i,j,100);
                }
            }
        }
        saveWorld();
    }

    public void updateWorld(int x, int y, int newTile){
        worldTiles[x][y] = tiles[newTile];
        saveFile[x][y] = newTile;
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
/*
    World Gen v2
 */
//
//// Generate border tiles
//        for (int i = 1; i < 30; i++) {
//        for (int j = 1; j < 30; j++) {
//Integer[] neighbours = {
//        saveFile[i - 1][j - 1],
//        saveFile[i - 1][j],
//        saveFile[i - 1][j + 1],
//        saveFile[i][j - 1],
//        saveFile[i][j],
//        saveFile[i][j + 1],
//        saveFile[i + 1][j - 1],
//        saveFile[i + 1][j],
//        saveFile[i + 1][j + 1],
//};
//                if (neighbours[4] != 45) continue;
//        if (Arrays.stream(neighbours).allMatch(x -> x == 45)) continue;
//        System.out.println(Arrays.toString(neighbours));
//        for (int k = 0; k < neighbours.length; k++) {
//        if(neighbours[k] == 20){
//        switch (k) {
//        case 0:
//updateWorld(i - 1,j - 1, underWaterList[k]);
//                                break;
//                                        case 1:
//updateWorld(i - 1,j,underWaterList[k]);
//                                break;
//                                        case 2:
//updateWorld(i - 1,j + 1,underWaterList[k]);
//                                break;
//                                        case 3:
//updateWorld(i,j - 1,underWaterList[k]);
//neighbours[k] = underWaterList[k];
//        break;
//        case 5:
//updateWorld(i,j + 1,underWaterList[k]);                                neighbours[k] = underWaterList[k];
//        break;
//        case 6:
//updateWorld(i + 1,j - 1,underWaterList[k]);
//                                break;
//                                        case 7:
//updateWorld(i + 1,j,underWaterList[k]);
//                                break;
//                                        case 8:
//updateWorld(i + 1,j + 1,underWaterList[k]);
//                                break;
//                                        }
//                                        }
//                                        }
//neighbours = new Integer[]{
//saveFile[i - 1][j - 1],
//saveFile[i - 1][j],
//saveFile[i - 1][j + 1],
//saveFile[i][j - 1],
//saveFile[i][j],
//saveFile[i][j + 1],
//saveFile[i + 1][j - 1],
//saveFile[i + 1][j],
//saveFile[i + 1][j + 1],
//        };
//        System.out.println(Arrays.toString(neighbours));
//
//        }
//        }