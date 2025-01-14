package chunk.util;

import java.util.Collection;

public interface MatrixList<Type> extends Collection<Type> {

    boolean remove(int indexX, int indexY);
    MatrixList<Type> copy();
    boolean contains(int indexX, int indexY);
    Type get(int indexX, int indexY);
    void set(MatrixList<Type> matrixList);
}
