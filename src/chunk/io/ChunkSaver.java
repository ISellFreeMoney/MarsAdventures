package chunk.io;

import chunk.Chunk;

import java.io.IOException;

public interface ChunkSaver extends FileConfiguration{

    void setProvider(OutputStreamProvider provider);

    boolean isSaving();
    void save(Chunk chunk) throws IOException;
}
