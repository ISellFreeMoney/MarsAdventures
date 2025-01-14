package chunk.caching;

import chunk.SimpleChunkConfiguration;

public class SimpleCachedChunkConfiguration extends SimpleChunkConfiguration implements CachedChunkConfiguration {

    private static final long serialVersionUID = 1L;

    private int cacheSizeX, cacheSizeY;
    private int offset;

    public SimpleCachedChunkConfiguration(int cacheSizeX, int cacheSizeY, int offset){
        setCacheSizeX(cacheSizeX);
        setCacheSizeY(cacheSizeY);
        setOffset(offset);
    }

    public SimpleCachedChunkConfiguration(int cacheSizeX, int cacheSizeY){
        this(cacheSizeX, cacheSizeY, 0);
    }

    public SimpleCachedChunkConfiguration(){
        this(DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
    }

    public SimpleCachedChunkConfiguration(CachedChunkConfiguration configuration){
        this(configuration.getCacheSizeX(), configuration.getCacheSizeY(), configuration.getOffset());
    }


    @Override
    public int getTotalChunkCount() {
        return (getCacheSizeX() * 2 + 1) * (getCacheSizeY() * 2 + 1);
    }

    @Override
    public void setCacheSizeX(int sizeX) {
        this.cacheSizeX = Math.abs(sizeX);
    }

    @Override
    public void setCacheSizeY(int sizeY) {
        this.cacheSizeY = Math.abs(sizeY);
    }

    @Override
    public void setCacheSize(int size) {
        setCacheSize(size);
        setCacheSizeY(size);
    }

    @Override
    public int getCacheSizeX() {
        return cacheSizeX + offset;
    }

    @Override
    public int getCacheSizeY() {
        return cacheSizeY + offset;
    }

    @Override
    public void setOffset(int offset) {
        this.offset = Math.abs(offset);
    }

    @Override
    public int getOffset() {
        return offset;
    }
}
