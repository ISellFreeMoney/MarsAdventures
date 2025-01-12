package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {

    public int worldX,worldY;
    public int speed;
    public ArrayList<BufferedImage> idle, idleFlip, walk, walkFlip, run, jump;
    public String action, direction, colDir;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
