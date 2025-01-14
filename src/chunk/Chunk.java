package chunk;

import util.Boundable;
import util.Indexable;
import util.Observable;

import java.io.Serializable;

public interface Chunk extends Indexable, Serializable, Iterable<ChunkTarget>, Observable<ChunkListener>, Boundable {
    float getX();
    float getY();
    float getWidth();
    float getHeight();
    ChunkTarget retrieve();
    void add(ChunkTarget target);
    boolean contains(ChunkTarget target);
    int size();
    boolean isEmpty();
    void clear();
}
