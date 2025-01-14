package chunk.caching;

import chunk.util.IndexBoundable;

interface Cache extends IndexBoundable {
    void align(int indexX, int indexY);
}
