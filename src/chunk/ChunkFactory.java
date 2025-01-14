package chunk;

public interface ChunkFactory {

    Chunk createChunk(int indexX, int indexY);

    void setChunkConfiguration(ChunkConfiguration configuration);

    ChunkConfiguration getChunkConfiguration();
}
