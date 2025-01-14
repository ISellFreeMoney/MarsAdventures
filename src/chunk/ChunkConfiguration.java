package chunk;

import java.io.Serializable;

public interface ChunkConfiguration extends Serializable {

    public static final int DEFAULT_WIDTH = 256;
    public static final int DEFAULT_HEIGHT = 256;

    int getChunkWidth();
    int getChunkHeight();
    void setChunkWidth(int width);
    void setChunkHeight(int height);
    void setChunkSize(int size);
    void setContentProvider(ContentProvider contentProvider);
    ContentProvider getContentProvider();
    void setFocused(ChunkTarget target);
    ChunkTarget getFocused();
}
