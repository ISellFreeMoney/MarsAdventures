package util;

public interface Boundable {
    float getTop();
    float getBottom();
    float getLeft();
    float getRight();
    boolean contains(float x, float y);
}
