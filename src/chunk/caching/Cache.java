package chunk.caching;

import util.IndexBoundable;

interface Cache extends IndexBoundable {
    void align(int indexX, int indexY);
}
