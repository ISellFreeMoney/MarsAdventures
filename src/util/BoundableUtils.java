package util;

public final class BoundableUtils {
    public static boolean contains(Boundable boundable, float x, float y){
        final boolean topLeftRange = x < boundable.getLeft() || y < boundable.getTop();
        final boolean bottomRightRange = x > boundable.getRight() || y > boundable.getBottom();
        return !(topLeftRange || bottomRightRange);
    }
}
