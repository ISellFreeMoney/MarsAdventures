package fr.MarsAdventure.core;

public class Position {
    private double x;
    private double y;

    public Position(int x, int y){
        this.x = x; this.y = y;
    }

    public Position(double x, double y){
        this.x = x; this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void apply(Motion motion){
        Vector2D vector = motion.getVector();
        x += vector.getX();
        y += vector.getY();
    }

    public boolean isInRangeOf(Position position) {
        return Math.abs(x - position.getX()) < 1 && Math.abs(y - position.getY()) < 1;
    }

    public int intX() {
        return (int)x;
    }

    public int intY(){
        return (int)y;
    }
}
