package entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {

    public int worldX,worldY;
    public int speed;
    public ArrayList<BufferedImage> idle, walk, run, jump;
    public String action;
}
