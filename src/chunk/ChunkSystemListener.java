package chunk;

public interface ChunkSystemListener {
    void beforeCreateChunk(int indexX, int indexY);
    void afterCreateChunk(Chunk chunk);

    void beforeLoadChunk(int indexX, int indexY);
    void afterLoadChunk(Chunk chunk);

    void beforeSaveChunk(Chunk chunk);
    void afterSaveChunk(Chunk chunk);

    void beforeRemoveChunk(Chunk chunk);
    void afterRemoveChunk(int indexX, int indexY);

    void onEnterChunk (Chunk chunk);
    void onLeaveChunk(Chunk chunk);
}
