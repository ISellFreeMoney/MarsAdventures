package chunk;

import chunk.moving.MoveEvent;
import chunk.moving.MovementListener;
import util.MatrixList;

public interface ChunkHandler extends MovementListener {

    void handleChunks(MatrixList<Chunk> chunks);

    void onMove(MoveEvent event);

    void saveChunks(MatrixList<Chunk> chunks);
    void saveChunk(Chunk chunk);
}
