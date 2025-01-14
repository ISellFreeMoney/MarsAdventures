package chunk.moving;

import chunk.ChunkTarget;
import util.Updatable;

public interface MoveableChunkTarget extends ChunkTarget, Updatable {
    MovementDetector getMovementDetector();
    void setMovementDetector(MovementDetector movementDetector);

}
