package entity.Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    private ArrayList<BufferedImage> anim = new ArrayList<>();
    private BufferedImage origin;
    private int tileSize;

    public Animation (BufferedImage origin, int tileSize){
        this.origin = origin;
        this.tileSize = tileSize;
    }

    public void createAnimation(BufferedImage origin) {
        int height = origin.getHeight() / tileSize;
        int width = origin.getWidth() / tileSize;
        int count = width * height;
        int i = 0;
        while (i < count) {
            anim.add(origin.getSubimage(i * tileSize, 0, tileSize, tileSize));
            i++;
        }

    }

    public ArrayList<BufferedImage> getAnim(){
        createAnimation(origin);
        return anim;
    }
}
