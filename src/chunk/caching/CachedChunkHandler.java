package chunk.caching;

import chunk.*;
import chunk.io.ChunkSaver;
import chunk.moving.MoveEvent;
import util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CachedChunkHandler implements ChunkHandler {

    private ChunkFactory chunkFactory;
    private PositionInterpreter positionInterpreter;
    private CachedChunkSystem chunkSystem;

    public CachedChunkHandler(CachedChunkSystem chunkSystem){
        this.chunkSystem = chunkSystem;
        chunkFactory = new SimpleChunkFactory(chunkSystem.getConfiguration());
        positionInterpreter = new SimplePositionInterpreter(chunkSystem.getConfiguration());
    }

    @Override
    public void handleChunks(MatrixList<Chunk> chunks) {
        MatrixList<Chunk> removeChunks = chunks.copy();
        IndexBoundable cache = chunkSystem.getPreCache();
        ContentProvider provider = chunkSystem.getConfiguration().getContentProvider();

        for(int indexX = cache.getIndexLeft(); indexX <= cache.getIndexRight(); ++indexX){
            for(int indexY = cache.getIndexTop(); indexY <= cache.getIndexBottom(); ++indexY){
                Chunk chunk = null;

                if(chunks.contains(indexX, indexY)){
                    removeChunks.remove(indexX, indexY);
                    chunk = chunks.get(indexX, indexY);
                } else {
                    chunk = getChunk(indexX, indexY);
                    chunks.add(chunk);
                }

                while(!chunk.isEmpty()){
                    provider.add(chunk.retrieve());
                }
            }
        }
        unloadChunks(removeChunks, chunks);
    }

@Override
public void onMove(MoveEvent event){
        ChunkTarget target = event.getTarget();

        int indexX = event.getNewIndexX();
        int indexY = event.getNewIndexY();
        IndexBoundable preCache = chunkSystem.getPreCache();
        boolean isNotFocused = !target.equals(chunkSystem.getConfiguration().getFocused());
        if(isNotFocused && !preCache.containsIndex(indexX, indexY)){
            PositionableBinder binder = new PositionableBinder(new BoundableAdapter(preCache, chunkSystem.getConfiguration()));
            PositionInterpreter interpreter = new SimplePositionInterpreter(chunkSystem.getConfiguration());

            binder.bind(target, event.getLastX(), event.getLastY());

            indexX = interpreter.translateX(target.getX());
            indexY = interpreter.translateY(target.getY());

            Chunk chunk = chunkSystem.getChunk(indexX, indexY);
            ContentProvider contentProvider = chunkSystem.getConfiguration().getContentProvider();

            if(chunk != null){
                chunk.add(target);
            }
            contentProvider.remove(target);
        }
}

    @Override
    public void saveChunks(MatrixList<Chunk> chunks) {
        for( Chunk chunk : chunks) {
            saveChunk(chunk);
        }
    }

    @Override
    public void saveChunk(Chunk chunk) {
        saveChunk(chunk, false);
    }

    private void saveChunk(Chunk chunk, boolean remove){
        ChunkSaver saver = chunkSystem.getSaver();

        List<Object> targets = getTargetForChunk(chunk);

        for (Object target : targets) {
            if (target instanceof ChunkTarget){
                chunk.add((ChunkTarget) target);
            }
        }

        beforeSaveChunk(chunk);

        try{
            saver.save(chunk);
        } catch (IOException e){
            e.printStackTrace();
        }

        afterSaveChunk(chunk);
        chunk.clear();
    }

    private void unloadChunk(Chunk chunk){
        ChunkConfiguration configuration = chunkSystem.getConfiguration();
        ContentProvider contentProvider = configuration.getContentProvider();

        List<Object> targets = getTargetForChunk(chunk);

        beforeRemoveChunk(chunk);
        saveChunk(chunk, true);

        for(Object target : targets){
            contentProvider.remove(target);
        }
        afterRemoveChunk(chunk.getIndexX(), chunk.getIndexY());
    }

    private void unloadChunks(MatrixList<Chunk> removables, MatrixList<Chunk> chunks){
        for(Chunk chunk : removables){
            unloadChunk(chunk);
            chunks.remove(chunk);
        }
    }

    private Chunk getChunk (int indexX, int indexY) {
        Chunk chunk = null;
        try {
            beforeLoadChunk(indexX, indexY);
            chunk = chunkSystem.getLoader().load(indexX, indexY);
            afterLoadChunk(chunk);
        } catch (IOException e) {
            beforeLoadChunk(indexX, indexY);
            chunk = chunkFactory.createChunk(indexX, indexY);
            afterCreateChunk(chunk);
        }
        return chunk;
    }

    private List<Object> getTargets() {

        ChunkConfiguration configuration = chunkSystem.getConfiguration();
        ContentProvider contentProvider = configuration.getContentProvider();

        return new CopyOnWriteArrayList<Object>(contentProvider.getContent());
    }

    private List<Object> getTargetForChunk(Chunk chunk) {
        List<Object> targets = getTargets();
        List<Object> results = new ArrayList<Object>();

        for (Object target : targets) {
            if(target instanceof ChunkTarget){
                int indexX = positionInterpreter.translateX(((ChunkTarget)target).getX());
                int indexY = positionInterpreter.translateY(((ChunkTarget)target).getY());

                if(chunk.getIndexX() == indexX && chunk.getIndexY() == indexY){
                    results.add(target);
                }
            }
        }
        return results;
    }

    private void beforeCreateChunk(int indexX, int indexY ){
        for (ChunkSystemListener listener : chunkSystem.getListeners()) {
            listener.beforeCreateChunk(indexX, indexY);
        }
    }
    private void afterCreateChunk(Chunk chunk){
        for (ChunkSystemListener listener : chunkSystem.getListeners()) {
            listener.afterCreateChunk(chunk);
        }
    }
    private void beforeLoadChunk(int indexX, int indexY ){
        for (ChunkSystemListener listener : chunkSystem.getListeners()) {
            listener.beforeLoadChunk(indexX, indexY);
        }
    }
    private void afterLoadChunk(Chunk chunk){
        for (ChunkSystemListener listener : chunkSystem.getListeners()) {
            listener.afterLoadChunk(chunk);
        }
    }
    private void beforeRemoveChunk(Chunk chunk){
        for (ChunkSystemListener listener : chunkSystem.getListeners()) {
            listener.beforeRemoveChunk(chunk);
        }
    }
    private void afterRemoveChunk(int indexX, int indexY ){
        for (ChunkSystemListener listener : chunkSystem.getListeners()) {
            listener.afterRemoveChunk(indexX, indexY);
        }
    }
    private void beforeSaveChunk(Chunk chunk){
        for (ChunkSystemListener listener : chunkSystem.getListeners()) {
            listener.beforeSaveChunk(chunk);
        }
    }
    private void afterSaveChunk(Chunk chunk){
        for (ChunkSystemListener listener : chunkSystem.getListeners()) {
            listener.afterSaveChunk(chunk);
        }
    }



}
