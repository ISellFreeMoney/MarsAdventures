package tile;

import main.GamePanel;
import util.UtilityTools;

import javax.imageio.ImageIO;
import java.awt.*;
        import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[][] tiles;

    public TileManager(GamePanel gp){
        this.gp = gp;
        getTileImage();
    }

    public Tile[][] getTileImage(){
        try{
            BufferedImage origin = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/tileset_jess_upscale.png")));
            UtilityTools uTool = new UtilityTools();
            int height = origin.getHeight() / gp.originalTileSize;
            int width = origin.getWidth() / gp.originalTileSize;
            tiles = new Tile[width][height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Tile temp = new Tile();
                    temp.image = origin.getSubimage(j * gp.originalTileSize, i * gp.originalTileSize, gp.originalTileSize, gp.originalTileSize);
                    temp.image = uTool.scaleImage(temp.image, gp.tileSize, gp.tileSize);
                    temp.isVisible = true;
                    tiles[i][j] = temp;
                }
            }
            return tiles;
        }catch (IOException e){
            System.err.println("Failed Loading Images for the Tiles: " + e.getMessage());
        }
        return tiles;
    }

    public void draw(Graphics2D g2){
//        int screenX;
//        int screenY;
//        int worldX;
//        int worldY;
//        Tile[][] world = gp.world.getTiles();
//        for (int i = 0; i < gp.mapWidth; i++) {
//            for (int j = 0; j < gp.mapHeight; j++) {
//                worldX = i*gp.tileSize;
//                worldY = j*gp.tileSize;
//
//                screenX = worldX - gp.player.worldX + gp.player.screenX;
//                screenY = worldY - gp.player.worldY + gp.player.screenY;
//                if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
//                    worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
//                    worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
//                    worldY < gp.player.worldY + gp.player.screenY + gp.tileSize
//            )
//            g2.drawImage(world[i][j].image, screenX, screenY, null);
//            }
//        }





//        Tile[][] worldGrid = gp.world.getDualGrid();
//        int screenX, screenY, worldX, worldY;
//        for (int i = 0; i < gp.mapWidth - 1; i++) {
//            for (int j = 0; j < gp.mapHeight - 1; j++) {
//                worldX = worldGrid[i][j].renderX;
//                worldY = worldGrid[i][j].renderX;
//                screenX = worldX - gp.player.worldX + gp.player.screenX;
//                screenY = worldY - gp.player.worldY + gp.player.screenY;
//                if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
//                        worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
//                        worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
//                        worldY < gp.player.worldY + gp.player.screenY + gp.tileSize
//                )
//                    g2.drawImage(worldGrid[i][j].image, screenX, screenY, null);
//            }
//        }
    }
}
