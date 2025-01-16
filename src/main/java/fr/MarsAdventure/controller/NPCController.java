package fr.MarsAdventure.controller;

import fr.MarsAdventure.core.Position;

public class NPCController implements Controller{

    private boolean up;
    private boolean right;
    private boolean left;
    private boolean down;

    @Override
    public boolean isRequestingUp() {
        return up;
    }

    @Override
    public boolean isRequestingDown() {
        return down;
    }

    @Override
    public boolean isRequestingLeft() {
        return left;
    }

    @Override
    public boolean isRequestingRight() {
        return right;
    }

    public void moveToTarget(Position target, Position current) {
        double deltaX = target.getX() - current.getX();
        double deltaY = target.getY() - current.getY();
        
        up = deltaY < 0;
        right = deltaX > 0;
        down = deltaY > 0;
        left = deltaX < 0;
    }

    public void stop() {
        up = false;
        right = false;
        left = false;
        down = false;
    }
}
