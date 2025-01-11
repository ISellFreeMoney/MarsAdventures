package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
        import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;

    public TileManager(GamePanel gp){
        this.gp = gp;
        getTileImage();
    }

    public Tile[] getTileImage(){
        try{
            BufferedImage origin = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tiles.png"));
            int height = origin.getHeight() / gp.originalTileSize;
            int width = origin.getWidth() / gp.originalTileSize;
            int index = 0;
            tiles = new Tile[height * width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Tile temp = new Tile();
                    temp.image = origin.getSubimage(j * gp.originalTileSize, i * gp.originalTileSize, gp.originalTileSize, gp.originalTileSize);
                    tiles[index] = temp;
                    index++;
                }
            }
            return tiles;
        }catch (IOException e){
            e.printStackTrace();
        }
        return tiles;
    }

    public void draw(Graphics2D g2){
        int screenX = 0;
        int screenY = 0;
        int worldX = 0;
        int worldY = 0;
        Tile[][] world = gp.world.getTiles();
        for (int i = 0; i < gp.mapWidth; i++) {
            for (int j = 0; j < gp.mapHeight; j++) {
                worldX = i*gp.tileSize;
                worldY = j*gp.tileSize;

                screenX = worldX - gp.player.worldX + gp.player.screenX;
                screenY = worldY - gp.player.worldY + gp.player.screenY;
                if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                    worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                    worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                    worldY < gp.player.worldY + gp.player.screenY + gp.tileSize
            )
            g2.drawImage(world[i][j].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }
    }
}
