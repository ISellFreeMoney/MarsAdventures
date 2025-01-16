package fr.MarsAdventure.gfx;

import fr.MarsAdventure.core.Direction;
import fr.MarsAdventure.game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationManager {
    private final SpriteSet spriteSet;
    private BufferedImage currentAnimationSheet;
    private final int updatePerFrame;
    private int currentFrameTime;
    private int frameIndex;
    private int directionIndex;

    public AnimationManager(SpriteSet spriteSet){
        this.spriteSet = spriteSet;
        this.updatePerFrame = 10;
        this.frameIndex = 0;
        this.currentFrameTime = 0;
        playAnimation("IDLE");
    }

    public Image getSpriteSet() {
        return currentAnimationSheet.getSubimage(
                frameIndex * Game.SPRITE_SIZE,
                directionIndex  * Game.SPRITE_SIZE,
                Game.SPRITE_SIZE,
                Game.SPRITE_SIZE
        );
    }

    public void update(Direction direction){
        currentFrameTime++;
        directionIndex = direction.getAnimationRow();

        if(currentFrameTime >= updatePerFrame){
            currentFrameTime = 0;
            frameIndex++;

            if(frameIndex >= currentAnimationSheet.getWidth() / Game.SPRITE_SIZE - 1){
                frameIndex = 0;
            }
        }
    }

    public void playAnimation(String name){
        this.currentAnimationSheet = (BufferedImage) spriteSet.get(name);
    }
}
