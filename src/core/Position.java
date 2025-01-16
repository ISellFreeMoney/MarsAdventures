package core;

public class Position {
    private double x;
    private double y;

    public Position(int x, int y){
        this.x = x; this.y = y;
    }

    public Position(double x, double y){
        this.x = x; this.y = y;
    }

    public int getX() {
        return (int) Math.floor(x);
    }

    public int getY() {
        return (int) Math.floor(y);
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
        System.out.println("Current: " + getX() + ", " + getY() + System.lineSeparator()
        + "To: " + position.getX() + ", " + position.getY());
        return getX() == position.getX() && getY() == position.getY();
    }
}
