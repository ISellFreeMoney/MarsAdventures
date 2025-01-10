package tile;

import main.GamePanel;
import utils.OpenSimplex2S;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class TileManager {
    GamePanel gp;
    ArrayList<Tile> tile;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new ArrayList<>(); //define the number of tiles
        getTileImage();
    }

    public void getTileImage(){
        try{
            BufferedImage origin = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tiles.png"));
            int height = origin.getHeight() / gp.originalTileSize;
            int width = origin.getWidth() / gp.originalTileSize;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Tile temp = new Tile();
                    temp.image = origin.getSubimage(j * gp.originalTileSize, i * gp.originalTileSize, gp.originalTileSize, gp.originalTileSize);
                    tile.add(temp);
                    System.out.println(temp);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        for (int i = 0; i < gp.mapHeight; i++) {
            for (int j = 0; j < gp.mapWidth; j++) {
                g2.drawImage(tile.get((int) (Math.floor((OpenSimplex2S.noise3_ImproveXY(0, i * 0.025, j * 0.025, 0.0)) * 78) + 78)).image, i * gp.tileSize, j * gp.tileSize, gp.tileSize, gp.tileSize, null);
            }
        }
    }
}
