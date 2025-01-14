package chunk.moving;

import chunk.ChunkTarget;

public interface MoveEvent {

    ChunkTarget getTarget();
    int getLastIndexX();
    int getLastIndexY();
    float getLastX();
    float getLastY();
    int getNewIndexX();
    int getNewIndexY();
    float getNewX();
    float getNewY();
}
