package chunk.caching;

import chunk.ChunkConfiguration;

public interface CachedChunkConfiguration extends ChunkConfiguration {

    public static final int DEFAULT_SIZE_X = 2;
    public static final int DEFAULT_SIZE_Y = 2;

    int getTotalChunkCount();

    void setCacheSizeX(int sizeX);

    void setCacheSizeY(int sizeY);

    void setCacheSize(int size);

    int getCacheSizeX();

    int getCacheSizeY();

    void setOffset(int offset);

    int getOffset();

}
