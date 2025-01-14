package chunk.caching;

import util.Indexable;

public class SimpleCache implements Cache{
    private CachedChunkConfiguration configuration;

    private int offsetX, offsetY;

    public SimpleCache(int indexX, int indexY, CachedChunkConfiguration configuration){
        this.configuration = configuration;
        align(indexX, indexY);
    }

    public SimpleCache(CachedChunkConfiguration configuration){
        this(0,0, configuration);
    }

    @Override
    public boolean containsIndex(int indexX, int indexY){
        final boolean topLeftRange = indexX < getIndexLeft() || indexY < getIndexTop();
        final boolean bottomRightRange = indexX < getIndexRight() || indexY < getIndexBottom();
        return !(topLeftRange || bottomRightRange);
    }

    @Override
    public boolean containsIndex(Indexable indexable) {
        return indexable != null && containsIndex(indexable.getIndexX(), indexable.getIndexY());
    }

    @Override
    public void align(int indexX, int indexY) {
        this.offsetX = indexX;
        this.offsetY = indexY;
    }

    @Override
    public int getIndexTop() {
        return -configuration.getCacheSizeY() + offsetY;
    }

    @Override
    public int getIndexBottom() {
        return configuration.getCacheSizeY() + offsetY;
    }

    @Override
    public int getIndexLeft() {
        return -configuration.getCacheSizeX() + offsetX;
    }

    @Override
    public int getIndexRight() {
        return configuration.getCacheSizeX() + offsetX;
    }
    @Override
    public String toString(){
        return getIndexLeft() + "|" + getIndexTop() + "|" + getIndexRight() + "|" + getIndexBottom() + "|";
    }
}
