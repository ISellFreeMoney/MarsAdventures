package entity;

import entity.Animation.Animation;
import main.GamePanel;
import main.KeyHandler;
import utils.UtilityTools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    int counter = 0;
    int animation_index = 0;


    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle( 20,gp.tileSize / 2,gp.tileSize / 3 ,gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 10;
        speed = 4;
    }
    public void update(){
        action = "idling";
        colDir = "";
        direction = "";
        if(keyH.upPressed){
            action = "walking";
            colDir = "up";
        }
        if(keyH.downPressed){
            action = "walking";
            colDir = "down";
        }
        if(keyH.leftPressed){
            action = "walking";
            direction = "left";
            colDir = "left";

        }
        if(keyH.rightPressed){
            action = "walking";
            direction = "right";
            colDir = "right";
        }
        collisionOn = false;
        gp.cChecker.checkTile(this);

        if (!collisionOn){
            switch (colDir){
                case "up": this.worldY -= this.speed; break;
                case "down": this.worldY+= this.speed; break;
                case "left": this.worldX -= this.speed; break;
                case "right": this.worldX += this.speed; break;
            }
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch(action) {
            case "walking":
                if (walk.size() <= animation_index) animation_index = 0;
                if (!direction.isEmpty()){
                    if (direction.equals("left")) {
                        image = walkFlip.get(animation_index);
                        break;
                    }
                }
                image = walk.get(animation_index);
                break;
            case "idling":
                if (idle.size() <= animation_index) animation_index = 0;
                if(!direction.isEmpty())  {
                    if (direction.equals("left")) {
                        image = idleFlip.get(animation_index);
                        break;
                    }
                }
                image = idle.get(animation_index);
                break;
            case "running":
                if (run.size() <= animation_index) animation_index = 0;
                image = run.get(animation_index);
                break;
            case "jumping":
                if (jump.size() <= animation_index) animation_index = 0;
                image = jump.get(animation_index);
                break;
        }
        counter ++;
        if (counter == 7) {
            animation_index++;
            counter  = 0;
        }
        g2.drawImage(image, screenX, screenY, null);
    }

    public void getPlayerImage(){
    UtilityTools utool = new UtilityTools();
        idleFlip = new ArrayList<>();
        walkFlip = new ArrayList<>();
        try {
            Animation load = new Animation(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Player/IDLE.png"))), gp.tileSize, gp.originalTileSize);
            idle = load.getAnim();
            for (BufferedImage bufferedImage : idle) {
                AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                tx.translate(-bufferedImage.getWidth(null), 0);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                idleFlip.add(utool.scaleImage(op.filter(bufferedImage, null), gp.tileSize, gp.tileSize));
            }
            load = new Animation(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Player//WALK.png"))),gp.tileSize, gp.originalTileSize);
            walk = load.getAnim();
            for (BufferedImage bufferedImage : walk) {
                AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                tx.translate(-bufferedImage.getWidth(null), 0);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                walkFlip.add(utool.scaleImage(op.filter(bufferedImage, null), gp.tileSize, gp.tileSize));
            }
            //run = ImageIO.read(getClass().getResourceAsStream("/resources/Player/RUN.png"));
            //jump = ImageIO.read(getClass().getResourceAsStream("/resources/Player/JUMP.png"));
        }catch (IOException e){
            System.err.println("Failed Loading Images for the player: " + e.getMessage());
        }
    }


}
