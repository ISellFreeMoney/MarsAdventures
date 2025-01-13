package world;

import main.GamePanel;
import tile.Tile;
import tile.TileManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WorldDualGrid {
    private GamePanel gp;
    private TileManager tileM;
    private Tile[][] renderTiles;
    private final HashMap<int[], int[]> grass_dirt_map;
    private final Tile[][] worldTiles;
    private int[] i1, i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14,i15,i16;
    private int[][] indexList;

    public WorldDualGrid (Worldv2 world, TileManager tileM) {
        i1 = new int[]{0, 0, 0, 0};
        i2 = new int[]{0, 0, 0, 1};
        i3 = new int[]{0, 0, 1, 0};
        i4 = new int[]{0, 0, 1, 1};
        i5 = new int[]{0, 1, 0, 0};
        i6 = new int[]{0, 1, 0, 1};
        i7 = new int[]{0, 1, 1, 0};
        i8 = new int[]{0, 1, 1, 1};
        i9 = new int[]{1, 0, 0, 0};
        i10 = new int[]{1, 0, 0, 1};
        i11 = new int[]{1, 0, 1, 0};
        i12 = new int[]{1, 0, 1, 1};
        i13 = new int[]{1, 1, 0, 0};
        i14 = new int[]{1, 1, 0, 1};
        i15 = new int[]{1, 1, 1, 0};
        i16 = new int[]{1, 1, 1, 1};
        indexList = new int[][] {
                i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16
            };
        this.tileM = tileM;
        this.worldTiles = world.getWorldTilesId();
        this.gp = world.gp;
        grass_dirt_map = new HashMap<>() {{
            put(i1, new int[]{2, 1});
            put(i2, new int[]{1, 3});
            put(i3, new int[]{0, 0});
            put(i4, new int[]{3, 0});
            put(i5, new int[]{0, 2});
            put(i6, new int[]{1, 0});
            put(i7, new int[]{2, 3});
            put(i8, new int[]{1, 1});
            put(i9, new int[]{3, 3});
            put(i10, new int[]{0, 1});
            put(i11, new int[]{3, 2});
            put(i12, new int[]{2, 0});
            put(i13, new int[]{2, 1});
            put(i14, new int[]{1, 2});
            put(i15, new int[]{3, 1});
            put(i16, new int[]{0, 3});
        }};
        generateDualGrid();
    }

    public void generateDualGrid(){
        Tile currentTile = new Tile();
        Tile[][] tiles = tileM.getTileImage();
        renderTiles = new Tile[gp.mapWidth + 1][gp.mapHeight + 1];
        int[] neighbours;
        for (int i = 0; i < gp.mapWidth - 1; i++) {
            for (int j = 0; j < gp.mapHeight - 1; j++) {
                neighbours = new int[] {
                        worldTiles[i][j].id,
                        worldTiles[i+1][j].id,
                        worldTiles[i][j+1].id,
                        worldTiles[i+1][j+1].id
                };

                for (int k = 0; k < indexList.length; k++) {
                    if(Arrays.equals(neighbours, indexList[k])){
                        neighbours = indexList[k];
                        break;
                    }

                }
                currentTile.renderX = worldTiles[i][j].x - (gp.tileSize / 2);
                currentTile.renderY = worldTiles[i][j].y - (gp.tileSize / 2);
                System.out.println(grass_dirt_map.get(neighbours));
//                System.out.println("Neighbours: "+ Arrays.toString(neighbours) + System.lineSeparator() + "Tile: " +Arrays.toString(grass_dirt_map.get(neighbours)));
                currentTile.image = tiles[grass_dirt_map.get(neighbours)[0]][grass_dirt_map.get(neighbours)[1]].image;
                renderTiles[i][j] = currentTile;
            }
        }
    }

    public Tile[][] getRenderTiles() {
        return renderTiles;
    }
}
