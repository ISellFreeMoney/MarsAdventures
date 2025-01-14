package chunk.util;

public interface PositionInterpreter {
    float translateIndexX(int indexX);
    float translateIndexY(int indexY);
    int translateX(float x);
    int translateY(float y);
}
