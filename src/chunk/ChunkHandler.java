package chunk;

import chunk.util.MatrixList;

public interface ChunkHandler extends MovementListener{

    void handleChunks(MatrixList<Chunk> chunks);
    void saveChunks(MatrixList<Chunk> chunks);
    void saveChunk(Chunk chunk);
}
