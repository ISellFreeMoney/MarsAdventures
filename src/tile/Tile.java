package tile;

import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage image;
    public boolean collision = false;
    public boolean isVisible = false;
    public int x, y, renderX, renderY;
    public TileType type;
}


