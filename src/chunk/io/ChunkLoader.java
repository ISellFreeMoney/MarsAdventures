package chunk.io;

import chunk.Chunk;

import java.io.IOException;

public interface ChunkLoader extends FileConfiguration{

    void setProvider(InputStreamProvider provider);

    boolean isLoading();

    Chunk load(int indexX, int indexY) throws IOException;
}
