package chunk;

import chunk.io.ChunkLoader;
import chunk.io.ChunkSaver;
import util.Manageable;
import util.Observable;

import java.util.Collection;

public interface ChunkSystem extends Manageable, ConfigurationProvider, Observable<ChunkSystemListener> {
    Chunk getActiveChunk();
    Collection<Chunk> getChunks();
    Chunk getChunk(int indexX, int indexY);
    int getCurrentChunkCount();
    void setHandler(ChunkHandler handler);
    ChunkHandler getHandler();
    void setLoader(ChunkLoader chunkLoader);
    ChunkLoader getLoader();
    void setSaver(ChunkSaver chunkSaver);
    ChunkSaver getSaver();
}
