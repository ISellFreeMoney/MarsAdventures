package main;

import entity.Entity;
import tile.Tile;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        Tile tileNum1, tileNum2;

        switch (entity.colDir){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.world.getTiles()[entityLeftCol][entityTopRow];
                tileNum2 = gp.world.getTiles()[entityRightCol][entityTopRow];
                if (tileNum1.collision || tileNum2.collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.world.getTiles()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.world.getTiles()[entityRightCol][entityBottomRow];
                if (tileNum1.collision || tileNum2.collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.world.getTiles()[entityLeftCol][entityTopRow];
                tileNum2 = gp.world.getTiles()[entityLeftCol][entityBottomRow];
                if (tileNum1.collision || tileNum2.collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.world.getTiles()[entityRightCol][entityTopRow];
                tileNum2 = gp.world.getTiles()[entityRightCol][entityBottomRow];
                if (tileNum1.collision || tileNum2.collision) {
                    entity.collisionOn = true;
                }
                break;
        }

    }
}
