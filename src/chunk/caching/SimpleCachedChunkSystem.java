package chunk.caching;

import chunk.*;
import chunk.io.*;
import chunk.moving.MoveableChunkTarget;
import chunk.moving.MovementDetector;
import util.IndexBoundable;

import java.util.Collection;

public class SimpleCachedChunkSystem extends AbstractChunkSystem implements CachedChunkSystem {
    private static final long serialVersionUID = 1L;
    private CachedChunkConfiguration configuration;

    private Cache cache, preCache;
    private int lastSize;
    private boolean cacheRequest;

    public SimpleCachedChunkSystem(CachedChunkConfiguration configuration) {
        super(configuration);
        this.configuration = configuration;

        OutputStreamProvider outProvider = new SimpleOutputStreamProvider();
        InputStreamProvider inProvider = new SimpleInputStreamProvider();

        setSaver(new SimpleChunkSaver(outProvider));
        setLoader(new SimpleChunkLoader(inProvider));
        setHandler(new CachedChunkHandler(this));
    }

    @Override
    public void update(float delta) {
        Collection<Object> content = configuration.getContentProvider().getContent();
        if (lastSize != content.size()) updateDetectors(content);
        if (isRunning() && (cachingRequested() || cacheRequest)) {
            cacheRequest = false;
            alignCache();
            getHandler().handleChunks(chunks);
        }
        lastSize = content.size();
    }

    @Override
    public IndexBoundable getCache() {
        return cache;
    }

    @Override
    public IndexBoundable getPreCache() {
        return preCache;
    }

    @Override
    public double getProcess() {
        return (double) getCurrentChunkCount() / (double) getTotalChunkCount();
    }

    @Override
    public int getTotalChunkCount() {
        configuration.setOffset(0);
        int count = configuration.getTotalChunkCount();
        configuration.setOffset(0);
        return count;
    }

    @Override
    public CachedChunkConfiguration getCachedConfiguration() {
        return configuration;
    }

    public void setConfiguration(ChunkConfiguration configuration) {
        if (configuration instanceof CachedChunkConfiguration) {
            this.configuration = (CachedChunkConfiguration) configuration;
            super.setConfiguration(configuration);
            initializeCache();
        }
    }

    @Override
    public void start() {
        super.start();
        cacheRequest = true;
        initializeCache();

        getHandler().handleChunks(chunks);
        getHandler().saveChunks(chunks);
    }

    @Override
    public void shutdown() {
        super.shutdown();
        getHandler().saveChunks(chunks);
    }


    private void initializeCache(){
        cache = new SimpleCache(configuration);
        CachedChunkConfiguration preConfig = new SimpleCachedChunkConfiguration(configuration);
        preConfig.setOffset(1);
        preCache = new SimpleCache(preConfig);
        alignCache();
    }

    private void alignCache(){
        ChunkTarget focused = configuration.getFocused();

        if(focused != null){
            final int INDEX_X = positionInterpreter.translateX(focused.getX());
            final int INDEX_Y = positionInterpreter.translateY(focused.getY());

            cache.align(INDEX_X, INDEX_Y);
            preCache.align(INDEX_X, INDEX_Y);
        }
    }

    private boolean cachingRequested(){
        ChunkTarget focused = configuration.getFocused();

        if(focused != null){
            final int INDEX_X = positionInterpreter.translateX(focused.getX());
            final int INDEX_Y = positionInterpreter.translateY(focused.getY());
            return !cache.containsIndex(INDEX_X, INDEX_Y);
        }else{
            return false;
        }
    }

    public void updateDetectors(Collection<Object> content){
        for (Object target : content ){
            if( target instanceof MoveableChunkTarget) {
                MoveableChunkTarget moveable = (MoveableChunkTarget)target;
                MovementDetector detector = moveable.getMovementDetector();
                detector.addListener(getHandler());
            }
        }
    }
}