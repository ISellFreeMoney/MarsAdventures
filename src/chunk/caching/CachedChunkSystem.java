package chunk.caching;

import chunk.ChunkSystem;
import chunk.util.IndexBoundable;

public interface CachedChunkSystem  extends ChunkSystem {
    IndexBoundable getCache();
    IndexBoundable getPreCache();
    double getProcess();
    int getTotalChunkCount();
    int getCurrentChunkCount();
    CachedChunkConfiguration getCachedConfiguration();
}
