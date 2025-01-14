package util;

public interface IndexBoundable {
    int getIndexTop();
    int getIndexBottom();
    int getIndexLeft();
    int getIndexRight();
    boolean containsIndex(int indexX, int indexY);
    boolean containsIndex(Indexable indexable);
}
