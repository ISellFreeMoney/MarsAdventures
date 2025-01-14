package chunk;

public interface ChunkListener {
    void onAdd(ChunkTarget target, Chunk chunk);
    void onRemove(ChunkTarget target, Chunk chunk);
}
