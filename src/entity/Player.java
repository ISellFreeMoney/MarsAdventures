package entity;

import entity.Animation.Animation;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    BufferedImage load;
    int counter = 0;
    int animation_index = 0;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y= 100;
        speed = 3;
    }
    public void update(){
        action = "idling";
        if(keyH.upPressed){
            action = "walking";
            this.y -= this.speed;
        }
        if(keyH.downPressed){
            action = "walking";
            this.y += this.speed;
        }
        if(keyH.leftPressed){
            action = "walking";
            this.x -= this.speed;
        }
        if(keyH.rightPressed){
            action = "walking";
            this.x += this.speed;
        }

    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch(action) {
            case "walking":
                if (walk.size() <= animation_index) animation_index = 0;
                image = walk.get(animation_index);
                break;
            case "idling":
                if (idle.size() <= animation_index) animation_index = 0;
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

    public void getPlayerImage(){
        try {
            Animation load = new Animation(ImageIO.read(getClass().getResourceAsStream("/resources/Player/IDLE.png")), gp.originalTileSize);
            idle = load.getAnim();
            load = new Animation(ImageIO.read(getClass().getResourceAsStream("/resources/Player//WALK.png")), gp.originalTileSize);
            walk = load.getAnim();
            //run = ImageIO.read(getClass().getResourceAsStream("/resources/Player/RUN.png"));
            //jump = ImageIO.read(getClass().getResourceAsStream("/resources/Player/JUMP.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
