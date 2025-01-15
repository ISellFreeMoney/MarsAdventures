package core;

public class Position {
    private double x;
    private double y;

    public Position(int x, int y){
        this.x = x; this.y = y;
    }

    public int getX() {
        return (int) Math.round(x);
    }

    public int getY() {
        return (int) Math.round(y);
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
}
