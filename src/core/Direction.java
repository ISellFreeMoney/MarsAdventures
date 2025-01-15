package core;

public enum Direction {
    N(0),
    NE(1),
    E(2),
    SE(3),
    S(4),
    SW(5),
    W(6),
    NW(7);

    private int animationRow;

    Direction(int animationRow){
        this.animationRow = animationRow;
    }

    public static Direction fromMotion(Motion motion){
        double x = motion.getVector().getX();
        double y = motion.getVector().getY();

        if(x == 0 && y > 0) return S;
        if(x < 0 && y == 0) return W;
        if(x == 0 && y < 0) return N;
        if(x > 0 && y == 0) return E;
        if(x > 0 && y > 0) return SE;
        if(x > 0 && y < 0) return NE;
        if(x < 0 && y > 0) return SW;
        if(x < 0 && y < 0) return NW;

        return S;
    }

    public int getAnimationRow() {
        return animationRow;
    }
}
