package core;

public enum Direction {
    S(0),
//    NE(1),
    N(1),
//    SE(3),
    E(2),
//    SW(5),
    W(3);
//    NW(7);

    private final int animationRow;

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
        if(x > 0 && y > 0) return S;
        if(x > 0 && y < 0) return N;
        if(x < 0 && y > 0) return S;
        if(x < 0 && y < 0) return N;

        return S;
    }

    public int getAnimationRow() {
        return animationRow;
    }
}
