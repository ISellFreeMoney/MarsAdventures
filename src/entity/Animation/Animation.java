package entity.Animation;

import utils.UtilityTools;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    private final ArrayList<BufferedImage> anim = new ArrayList<>();
    private final BufferedImage origin;
    private final int tileSize, originalTileSize;

    public Animation (BufferedImage origin, int tileSize, int originalTileSize){
        this.origin = origin;
        this.tileSize = tileSize;
        this.originalTileSize = originalTileSize;
    }

    public void createAnimation(BufferedImage origin) {
        UtilityTools uTool = new UtilityTools();
        int height = origin.getHeight() / originalTileSize;
        int width = origin.getWidth() / originalTileSize;
        int count = width * height;
        int i = 0;
        while (i < count) {
            anim.add(uTool.scaleImage(origin.getSubimage(i * originalTileSize, 0, originalTileSize, originalTileSize), tileSize, tileSize));
            i++;
        }

    }

    public ArrayList<BufferedImage> getAnim(){
        createAnimation(origin);
        return anim;
    }
}
